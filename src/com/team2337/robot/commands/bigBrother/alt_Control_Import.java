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
	double fineArm;
	double armAdjPos, armAdjNeg;
	double trolleyAdj = 2;   //TODO determne rightamount
	double armEncoder;
	double liftPot;
	double trolleyPot;
	double armSetPoint;
	
	int liftSetPointColumn;
	
	double throttleToggle;
	double trolleyStick; 
	double throttleValue;
	
	public alt_Control_Import() {
		requires(Robot.bigBrother);
	}

	protected void initialize() {
		
	}

	protected void execute() {
		double liftPot = RobotMap.lift_right.getSelectedSensorPosition(0);
		
		trolleyStick = OI.operatorThrottleJoystick.getRawAxis(1);
		throttleValue = -OI.operatorThrottleJoystick.getRawAxis(2);
		throttleValue = (throttleValue*10)+10;
		
		throttleToggle = OI.operatorThrottleJoystick.getRawAxis(4);
		
		trolleySetPoint = ( points[(int) throttleValue][0]);
		armAdjPos = (points[(int) throttleValue][11]);
		armAdjNeg = (points[(int) throttleValue][12]);
		
		armEncoder =  RobotMap.arm_right.getSelectedSensorPosition(0);
		/* 
		 * Points for X
		 * 0: trolley set points
		 * 1: lift set points A
		 * 2: lift set points B
		 * 3: lift set points C
		 * 4: arm set points
		 * 5: trolley forward soft limits 
		 * 6: trolley reverse soft limits
		 * 7: arm forward soft limits
		 * 8: arm reverse soft limits
		 * 9: trolley positive adjustment
		 *10: trolley negative adjustment
		 *11: arm positive adjustment 
		 *12: arm negative adjustment
		 */

		//System.out.println("Beofre Logic");
		
		//Trolley set point logic
		
		trolleySetPoint = ((double) points[(int) throttleValue][0]);
		
		if(Math.abs(trolleyStick) > 0.1) {
			trolleySetPoint = trolleySetPoint + (trolleyStick * trolleyAdj); 
			System.out.println("1");
		}

		if(!Robot.arm.sameSide(armEncoder, trolleySetPoint)) {
			trolleySetPoint = (double) points[10][0];
			System.out.println("Not On The Same Side");
		}
		else {
			System.out.println("On The Same Side");
		}
		
		//Arm set point logic
		
		armSetPoint = ((double) points[(int) throttleValue][4]);
		
		if((throttleToggle) > 0.1) {
			armSetPoint = armSetPoint + (throttleToggle * armAdjPos); 
			System.out.println("1");
		}
		else if(throttleToggle < -0.1) {
			armSetPoint = armSetPoint + (throttleToggle * armAdjNeg);
		}
		
		//Set Points and Soft Points
		
		Robot.trolley.setPosition(trolleySetPoint);
		Robot.arm.setPosition(armSetPoint);
		Robot.lifter.setPosition((double) points[(int) throttleValue][Robot.lifter.levelOfLift]); 
		
		Robot.arm.setSoftLimits((int)(points[(int) throttleValue][7]), (int)(points[(int) throttleValue][8]));
			
		//Lifter.setSoftLimits(-60, -530);
		
		SmartDashboard.putBoolean("sameSide", Robot.arm.sameSide(armEncoder, trolleySetPoint));
		SmartDashboard.putNumber("trolleyStick", trolleyStick);
		SmartDashboard.putNumber("TrolleySetPoint", Robot.trolley.getSetpoint());
		SmartDashboard.putNumber("TrolleyPosition", RobotMap.trolley_right.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("throttleValue", throttleValue);
		SmartDashboard.putNumber("TrolleyArrayValue", points[(int) throttleValue][0]);
		SmartDashboard.putNumber("ArmSetPosition", armSetPoint);
		
		//System.out.println("The point is " + points[0][0] + "and " + points[(int)throttleValue][0]);
		//System.out.println(throttleValue);
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
