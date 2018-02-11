package com.team2337.robot.commands.bigBrother;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.trolley.trolley_setPID;
import com.team2337.robot.subsystems.Trolley;

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
	//double trolleyPot;
	
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
		if(RobotMap.endOfAuto) {
			Robot.bigBrother.stopAltControl();
			if(OI.operatorThrottleJoystick.getRawButton(8)) {
				RobotMap.endOfAuto = false;
			}
		}
		
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
		
		//Set Set points
		Robot.trolley.setPosition(trolleySetPoint);
		Robot.arm.setPosition(armSetPoint);
		Robot.lifter.setPosition((double) points[(int) throttleValue][Robot.lifter.levelOfLift]); 
		
		//Soft Limits
		Robot.arm.setSoftLimits((int)(points[(int) throttleValue][armForwardSoftLimits]), (int)(points[(int) throttleValue][armReverseSoftLimits]));

		if(RobotMap.alt_ControlDebug) {
		SmartDashboard.putBoolean("sameSide", Robot.arm.sameSide(armEncoder, trolleySetPoint));
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
