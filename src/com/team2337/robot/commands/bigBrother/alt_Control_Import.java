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
	
	boolean setPointSet = false;
	
	double trolleySetPoint;
	double armSetPoint;
	//double fineArm;
	double armAdjPos;
	double armAdjNeg;
	double trolleyAdj = 2;                                                   //TODO determne rightamount
	double armEncoder;
	//double liftPot;
	double trolleyPot;
	int reverse, forward;
	
	//int liftSetPointColumn;
	
	double throttleToggle;
	double trolleyStick; 
	double throttleValue;
	
	/* 
	 * Columns
	 * 0: trolley set points
	 * 1: lift set points A
	 * 2: lift set points B
	 * 3: lift set points C
	 * 4: arm set points
	 * 5: arm forward soft limits
	 * 6: arm reverse soft limits
	 * 7: arm positive adjustment 
	 * 8: arm negative adjustment
	 */
	
	int trolleySetPoints = 0;
	int liftSetPointsA = 1;
	int liftSetPointsB = 2;
	int liftSetPointsC = 3;
	int armSetPoints = 4;
	int armForwardSoftLimits = 5;
	int armReverseSoftLimits = 6;
	int armPositiveAdj = 7;
	int armNegativeAdj = 8;
	
	public alt_Control_Import() {
		requires(Robot.bigBrother);
	}

	protected void initialize() {
		
	}

	protected void execute() {
		SmartDashboard.putBoolean("endOfAuto - before", RobotMap.endOfAuto);
		//Disable after Autonomous util......//TODO
		/*if(RobotMap.endOfAuto) {
			Robot.bigBrother.stopAltControl();
				SmartDashboard.putBoolean("endOfAuto - middle", RobotMap.endOfAuto);
			if(OI.operatorThrottleJoystick.getRawButton(8)) {
				RobotMap.endOfAuto = false;
				SmartDashboard.putBoolean("endOfAuto - after", RobotMap.endOfAuto);
			}
		}*/
		
		//liftPot = RobotMap.lift_right.getSelectedSensorPosition(0);
		
		//Read Joystick and Throttle Input
		trolleyStick = OI.operatorThrottleJoystick.getRawAxis(1);
		throttleValue = -OI.operatorThrottleJoystick.getRawAxis(2);
		throttleValue = (throttleValue*10)+10;
		
		throttleToggle = OI.operatorThrottleJoystick.getRawAxis(4);
			
		//Read values from array based on Throttle input
		trolleySetPoint = ( points[(int) throttleValue][trolleySetPoints]); 
		armSetPoint = ((double) points[(int) throttleValue][armSetPoints]);
		armAdjPos = (points[(int) throttleValue][armPositiveAdj]);
		armAdjNeg = (points[(int) throttleValue][armNegativeAdj]);
		
		//Trolley set point logic
				
		//Adjust trolley setpoint based on joystick input
		if(Math.abs(trolleyStick) > 0.1) {
			trolleySetPoint = trolleySetPoint + (trolleyStick * trolleyAdj); 
		}
		
		//Override trolley setpoint to the top position if arm needs to change sides
		armEncoder =  RobotMap.arm_right.getSelectedSensorPosition(0);
		
		if(!Robot.arm.sameSide(armEncoder, armSetPoint)) {							
			trolleySetPoint = (double) points[10][trolleySetPoints];                      //Assuming position 10 is at the top
			System.out.println("Not On The Same Side");
		}
		else {
			System.out.println("On The Same Side");
		}
		
		//Arm set point logic
		
		//Adjust arm set point based on throttle toggle input.  Different adjustments for positive and negative toggle positions.
		if((throttleToggle) > 0.1) {
			armSetPoint = armSetPoint + (throttleToggle * armAdjPos); 
		}
		else if(throttleToggle < -0.1) {
			armSetPoint = armSetPoint + (throttleToggle * armAdjNeg);
		}
		
		
		//Soft Limits for ARM ,  set py position, not array....???
				
		if (Robot.trolley.isAtTop()) {
			forward = Robot.arm.forwardSoftLimit;
			reverse = Robot.arm.reverseSoftLimit;
			
		} else if (Robot.trolley.isAboveMid()) {
			if (Robot.arm.armIsReverse()) {
				forward =  Robot.arm.reverseTopSL;
				reverse = Robot.arm.reverseSoftLimit;
			} else if (Robot.arm.armIsForward()) {
				forward = Robot.arm.forwardSoftLimit;
				reverse = Robot.arm.forwardTopSL;
			}
		}else {
			if (Robot.arm.armIsReverse()) {
				forward = Robot.arm.reverseTopSL;
				reverse = Robot.arm.reverseLevel;
			} else if (Robot.arm.armIsForward()) {
				forward = Robot.arm.forwardLevel;
				reverse = Robot.arm.forwardTopSL;
			}
		}
		//Robot.arm.setSoftLimits((int)(points[(int) throttleValue][armForwardSoftLimits]), (int)(points[(int) throttleValue][armReverseSoftLimits]));
		
		Robot.arm.setSoftLimits(forward, reverse);
	
		
		//Set Set points, suspend PID if at pickup position
		//TODO add check if endOfAuto??? to disable/skip sets????
		
		if ( (throttleToggle > 0.9) & (trolleyStick < -0.9) & (Robot.arm.armIsDown()) ) {
			//TODO add .... if (lift is down) & (trolley is down)[i.e. pick up position]
				Robot.arm.stop();
				Robot.lift.stop();
				Robot.trolley.stop();
			}else {
				Robot.trolley.setSetpoint(trolleySetPoint);
				Robot.arm.setSetpoint(armSetPoint);
				Robot.lift.setSetpoint((double) points[(int) throttleValue][Robot.lift.levelOfLift]); 
		}
		

		if(RobotMap.alt_ControlDebug) {
		SmartDashboard.putBoolean("sameSide", Robot.arm.sameSide(armEncoder, armSetPoint));
		SmartDashboard.putNumber("trolleyStick", trolleyStick);
		SmartDashboard.putNumber("TrolleySetPoint", Robot.trolley.getSetpoint());
		SmartDashboard.putNumber("TrolleyPosition", RobotMap.trolley_right.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("throttleValue", throttleValue);
		SmartDashboard.putNumber("TrolleyArrayValue", points[(int) throttleValue][trolleySetPoints]);
		SmartDashboard.putNumber("ArmSetPosition", armSetPoint);
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
