package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Control the Lift-Trolley-Arm based on throttle/joystick input
 */
public class alt_Control_Import extends Command {

	double points[][] = Robot.bigBrother.points;

	double trolleySetPoint;
	double armSetPoint;
	double liftSetPoint;
	double armAdjPos;
	double armAdjNeg;
	double armClimbAdjLimit;
	double trolleyAdj = 2; // TODO determne rightamount
	double armCenterAdjLimit = 300;
	double armEncoder;
	double armPos;

	double throttleToggle;
	double trolleyStick;
	double throttleStick;
	double throttleValue;

	/*
	 * Columns 0: trolley set points 1: lift set points A 2: lift set points B
	 * 3: lift set points C 4: arm set points 5: arm forward soft limits 6: arm
	 * reverse soft limits 7: arm positive adjustment 8: arm negative adjustment
	 */

	//Array Points
	int trolleySetPoints = 0;	
	int liftSetPointsA = 1;
	int liftSetPointsB = 2;
	int liftSetPointsC = 3;
	int armSetPoints = 4;
	int armForwardSoftLimits = 5;
	int armReverseSoftLimits = 6;
	int armPositiveAdj = 7;
	int armNegativeAdj = 8;
	int liftSetPointClimb = 9;
	int armClimbAdjustment = 10;
	int armHookClimberSetPoints = 11;
	int armClimbMode = 12;
	int trolleyClimbMode = 13;
	
	//Comp rest Points	
	int compTrolleyRestPoint = 61;
	int practiceTrolleyRestPoint = 61; //111 practice
	
	//Lift rest points
	int compLiftRestPoint = Robot.lift.compLiftRestPoint;
	int practiceLiftRestPoint = Robot.lift.practiceLiftRestPoint;
	
	//double values
	double armCenterPosition = Robot.arm.centerPosition;
	double climberAdjLimit = Robot.arm.climberAdjLimit;  //Hook adjustment 
	double armHookAdjLimit = 0;

	//used to check if the arm set point is on teh same side as the actual arm position
	boolean sameSide = true; 

	public alt_Control_Import() {
		requires(Robot.bigBrother);
	}

	protected void initialize() {

	}

	protected void execute() {

		// liftPot = RobotMap.lift_right.getSelectedSensorPosition(0);

		// Read Joystick and Throttle Input
		trolleyStick = OI.operatorJoystick.getRawAxis(1);
		throttleStick = -OI.operatorJoystick.getRawAxis(2);
		throttleValue = (throttleStick * 10) + 10;

		throttleToggle = OI.operatorJoystick.getRawAxis(4);

		// Read values from array based on Throttle input
		trolleySetPoint = (points[(int) throttleValue][trolleySetPoints]);
		armSetPoint = ((double) points[(int) throttleValue][armSetPoints]);
		armAdjPos = (points[(int) throttleValue][armPositiveAdj]);
		armAdjNeg = (points[(int) throttleValue][armNegativeAdj]);
		armClimbAdjLimit = (points[(int) throttleValue][armClimbAdjustment]);  //hook adj array points
		liftSetPoint = points[(int) throttleValue][Robot.lift.levelOfLift];
		
		// Arm Safety, check for bad value
		
		armPos = Robot.arm.getPosition();
		
		if(armPos > 4096) {
		armPos = armPos%4096;
		RobotMap.arm_right.setSelectedSensorPosition((int)armPos,0,0);
		System.out.println("***********ARM BROKE greater than 4096************");
		RobotMap.brokenArmPos = true;
		RobotMap.brokenArm = true;
		} else if(armPos < 0) {
		armPos = 4096 - (Math.abs(armPos)%4096);
		RobotMap.arm_right.setSelectedSensorPosition((int)armPos,0,0);
		System.out.println("***********ARM BROKE less than zero************");
		RobotMap.brokenArmNeg = true;
		RobotMap.brokenArm = true;
		}

		
		
		
		
		// Trolley adjustment by joystick

		// Adjust trolley setpoint based on joystick input
		if (Math.abs(trolleyStick) > 0.1) {
			trolleySetPoint = trolleySetPoint + (trolleyStick * trolleyAdj);
		}

		// Arm adjustment by toggle

		// Adjust arm set point based on throttle toggle input. Different
		// adjustments
		// for positive and negative toggle positions.
		if ((throttleToggle) > 0.1) {
			armSetPoint = armSetPoint + (throttleToggle * armAdjPos);
		} else if (throttleToggle < -0.1) {
			armSetPoint = armSetPoint + (throttleToggle * armAdjNeg);
		}
		
		//NEW SET POINTS for Climb 
		if(Robot.lift.levelOfLift == armHookClimberSetPoints) {  					//grab hook is enabled
			liftSetPoint = (points[(int) throttleValue][liftSetPointsA]);
			armSetPoint = (points[(int) throttleValue][armHookClimberSetPoints]);	//arm set to hook grabbing pos
			trolleySetPoint = (points[(int) throttleValue][trolleyClimbMode]);		//trolley set to help arm grab hook
			if ((throttleToggle) < -0.1) {											//if the adjustment analog is greater than 0.1
				if(RobotMap.brokenArm) {
					if(Robot.oi.operatorControls.getRawButton(Robot.oi.blueButton)) {
						armHookAdjLimit = 50;
					}
					armSetPoint = armSetPoint + (throttleToggle * (armClimbAdjLimit + armHookAdjLimit + 50));
				} else {
				armSetPoint = armSetPoint + (throttleToggle * armClimbAdjLimit);			//set arm setpoint to climbAdj 
				}
			} 
		} 

		// Arm and Trolley set point override:

		// If arm needs to change sides:
		// Override trolley set point to the top position, and
		// override arm position to stay on same side until trolley gets to top.
		armEncoder = RobotMap.arm_right.getSelectedSensorPosition(0);

		if ((!Robot.arm.sameSide(armEncoder, armSetPoint))) {
			trolleySetPoint = points[10][trolleySetPoints];

			if (Robot.trolley.getPosition() < Robot.trolley.trolleyPassover) {
				if (armEncoder >= Robot.arm.centerPosition) {
					armSetPoint = points[10][armSetPoints];
				} 
				else if (armEncoder <= Robot.arm.centerPosition) {
					armSetPoint = points[11][armSetPoints];
				}
				sameSide = false;
			}
		} else
			sameSide = true;

		if(Robot.lift.levelOfLift == armHookClimberSetPoints) { 
			if(Robot.arm.getPosition() < 850) {	//1200 prac
				trolleySetPoint = Robot.trolley.trolleyHookPos;
			}
		}
		
		//Lift & Trolley Override
		
		/* put lift down before trolley
		 * trolley goes up before lift
		 * So we don't crush the snakes
		 */
		
		/*if(liftSetPoint > practiceLiftRestPoint && Robot.trolley.getPosition() < Robot.trolley.trolleyPassover) {
			liftSetPoint = practiceLiftRestPoint;
		}*/
		/*
		if(Robot.lift.getPosition() > practiceLiftRestPoint) {
			trolleySetPoint = points[10][trolleySetPoints];
		}
		*/
		// SET ALL SETPOINTS

		// Disabled after Autonomous until throttleToggle activated.
		if (RobotMap.disabledAtEndOfAuto) {
			SmartDashboard.putBoolean("Disabled: endOfAuto - checking throttle toggle", RobotMap.disabledAtEndOfAuto);
			if (Math.abs(throttleToggle) > 0.9) {
				RobotMap.disabledAtEndOfAuto = false;
				//SmartDashboard.putBoolean("Disabled: endOfAuto - checking throttle toggle", RobotMap.disabledAtEndOfAuto);
			}
		}
				
				    //CLIMB MODE POINTS
					if(Robot.lift.levelOfLift == armClimbMode) {	
						if(Robot.lift.getPosition() > 100) {
							trolleySetPoint = Robot.trolley.trolleyTop;
						} else {
						trolleySetPoint = (points[(int) throttleValue][trolleySetPoints]);
						}
						
						if(Robot.trolley.getPosition() < Robot.trolley.trolleyPassover) {
//							armSetPoint = Robot.arm.getPosition();
							liftSetPoint = (points[0][liftSetPointClimb]);
						} else {
							liftSetPoint = (points[(int) throttleValue][liftSetPointClimb]);	//climb mode active
							
							if(throttleValue > 10) {
								armSetPoint = armCenterPosition;									//arm goes to center pos
								if ((throttleToggle) < -0.1 && throttleValue >= 15) {							
									armSetPoint = armSetPoint + (throttleToggle * armCenterAdjLimit);		//set the arm setpoint to adj pos
								}
								
							} else if(throttleValue <= 10) {
							armSetPoint = (points[(int) throttleValue][armSetPoints]);				//set the arm, lift, trolley to points normal
							}
						}
					}
					
					if(Robot.arm.getPosition() < 950 && Robot.lift.levelOfLift == 11 && throttleToggle < -0.5) {
						Robot.climber.hookerEject();
					}


		if (!RobotMap.disabledAtEndOfAuto) { // Disable until throttleToggle activated (see above)									

			// Set Set points, suspend PID if at pickup position
			if (((throttleToggle > 0.9) && (throttleStick < -0.9) && (Robot.arm.armIsLevel())) || (Robot.arm.armBadPos())) {
				Robot.arm.stop();
			} else {
				if(OI.operatorControls.getRawButton(Robot.oi.yellowSwitch)) {
					Robot.arm.stop();
					Robot.trolley.stop();
					Robot.lift.stop();
				} else {
					Robot.arm.setSetpoint(armSetPoint);
				}
			}
			// Set trolley set point, suspend PID if at bottom position.
			if (RobotMap.trolley_right.getSelectedSensorPosition(0) < (practiceTrolleyRestPoint-10) && trolleySetPoint < practiceTrolleyRestPoint) {
				//Robot.trolley.stop();
			} else {
				if(OI.operatorControls.getRawButton(Robot.oi.blackSwitch)) {
					Robot.trolley.stop();
					Robot.lift.stop();
				} else {
					Robot.trolley.setSetpoint(trolleySetPoint);
				}
			}
			
			if (RobotMap.lift_right.getSelectedSensorPosition(0) < (Robot.lift.practiceLiftRestPoint-10) && liftSetPoint <= Robot.lift.practiceLiftRestPoint) {
				Robot.lift.stop();
			} else {
//				if(OI.operatorControls.getRawButton(Robot.oi.blueSwitch)) {
//					Robot.lift.stop();
//				} else {
				Robot.lift.setSetpoint(liftSetPoint);
//				}
			}
		}

		
		// Print Debug info to SmartDashboard if turned on in RobotMap.
		if (RobotMap.alt_ControlDebug) {
			SmartDashboard.putBoolean("endOFAuto - debug", RobotMap.disabledAtEndOfAuto);
			//SmartDashboard.putBoolean("sameSide", Robot.arm.sameSide(armEncoder, armSetPoint));
			SmartDashboard.putNumber("trolleyStick", trolleyStick);
			SmartDashboard.putNumber("TrolleySetPoint", Robot.trolley.getSetpoint());
			SmartDashboard.putBoolean("XXXsameSideXXX", sameSide);
			SmartDashboard.putNumber("throttleValue", throttleValue);
			SmartDashboard.putNumber("ArmSetPoint", armSetPoint);
			// SmartDashboard.putNumber("trolleyOutputPercent",
			// RobotMap.trolley_right.getMotorOutputPercent());
			SmartDashboard.putNumber("trolleyOutputXXXXXXXX", RobotMap.trolley_right.getMotorOutputPercent());
			
			SmartDashboard.putNumber("LIFTSetPointXXXXXX", Robot.lift.getSetpoint());
			SmartDashboard.putNumber("liftArray SetPoint", points[(int) throttleValue][Robot.lift.levelOfLift]);
			SmartDashboard.putNumber("throttleStick", throttleStick);

			// SmartDashboard.putNumber("TrolleyArrayValue", points[(int)
			// throttleValue][trolleySetPoints]);
			// this may be setting the array to the throttle, then getting to
			// the logic
			// above
		}
		if(RobotMap.climbDebug) {
			SmartDashboard.putBoolean("ClimbDebug", RobotMap.climbDebug);
			SmartDashboard.putNumber("armHookSetPoints", points[(int) throttleValue][armHookClimberSetPoints]);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}

}
