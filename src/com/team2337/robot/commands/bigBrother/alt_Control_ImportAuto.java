package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Control the Lift-Trolley-Arm based in Autonomous
 */
public class alt_Control_ImportAuto extends Command {

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
	int autolevelOfLift;
	
	int liftSetPointColumn;
	
	//double throttleToggle;
	double trolleyStick; 
	double throttleValue;
	
	int calcThrottlePos;
	double armAdj;
	
	public alt_Control_ImportAuto(int calcThrottlePos, double armAdj, int autolevelOfLift) {
		requires(Robot.bigBrother);
		calcThrottlePos = this.calcThrottlePos;
		armAdj = this.armAdj;
		autolevelOfLift = this.autolevelOfLift;
	}

	protected void initialize() {
		
		Robot.lifter.liftLeveler(autolevelOfLift);
		
		
	}

	protected void execute() {

		throttleValue = calcThrottlePos;
		
		trolleySetPoint = ((double) points[(int) throttleValue][4]);  ////////////////twice?????????   update from regcommand hcanges???
		armAdjPos = ((double) points[(int) throttleValue][11]);
		armAdjNeg = ((double) points[(int) throttleValue][12]);
		
		armEncoder =  RobotMap.arm_right.getSelectedSensorPosition(0);
		liftPot = RobotMap.lift_right.getSelectedSensorPosition(0);
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
		
		//Trolley set point logic
		
		trolleySetPoint = ((double) points[(int) throttleValue][0]);  ////////////////twice?????????
		
		if(!Robot.arm.sameSide(armEncoder, trolleySetPoint)) {
			trolleySetPoint = (double) points[10][0];
		}
		
		//Arm set point logic
		
		armSetPoint = ((double) points[(int) throttleValue][4]);
		
		if((armAdj) > 0.1) {
			armSetPoint = armSetPoint + (armAdj * armAdjPos); 
			System.out.println("1");
		}
		else if(armAdj < -0.1) {
			armSetPoint = armSetPoint + (armAdj * armAdjNeg);
		}
		
		//Set Points and Soft Points
		
		Robot.trolley.setPosition(trolleySetPoint);
		Robot.arm.setPosition(armSetPoint);
		Robot.lifter.setPosition((double) points[(int) throttleValue][autolevelOfLift]); 
		
		Robot.arm.setSoftLimits((int)(points[(int) throttleValue][7]), (int)(points[(int) throttleValue][8]));
			
		//Lifter.setSoftLimits(-60, -530);
		
		SmartDashboard.putBoolean("sameSide", Robot.arm.sameSide(armEncoder, trolleySetPoint));
		SmartDashboard.putNumber("trolleyStick", trolleyStick);
		SmartDashboard.putNumber("LIFTLocationPID", Robot.trolley.getSetpoint());
		SmartDashboard.putNumber("selectedSensorPositionLIFTER", RobotMap.trolley_right.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("throttleValue", throttleValue);
		SmartDashboard.putNumber("throttleArrayValue", points[(int) throttleValue][0]);
		SmartDashboard.putNumber("throttleSetPoints", points[(int) throttleValue][0]);
		SmartDashboard.putNumber("test", trolleySetPoint);
		
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
