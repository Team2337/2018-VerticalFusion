package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Control the Lift-Trolley-Arm based on throttle/joystick input
 */
public class setPointsCheckingAuto extends Command {

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
	
	public setPointsCheckingAuto(int calcThrottlePos, double armAdj, int autolevelOfLift) {
		requires(Robot.bigBrother);
		calcThrottlePos = this.calcThrottlePos;
		armAdj = this.armAdj;
		autolevelOfLift = this.autolevelOfLift;
	}

	protected void initialize() {
		
		Robot.lifter.liftLeveler(autolevelOfLift);
		
		//trolley set points
		points(0, 0, -225); // trolley set point at -98
		points(0, 1,-225); // trolley set point at -60
		points(0, 2, -225);// trolley set point at -147
		points(0, 3, -225);// trolley set point at -196
		points(0, 4, -245);// trolley set point at 
		points(0, 5, -294);
		points(0, 6, -343);
		points(0, 7, -392);
		points(0, 8, -441);
		points(0, 9, -490);
		points(0, 10, -530);
		points(0, 11, -530);
		points(0, 12, -530);
		points(0, 13, -530);
		points(0, 14, -530);
		points(0, 15, -530);
		points(0, 16, -530);
		points(0, 17, -530);
		points(0, 18, -530);
		points(0, 19, -530);
		points(0, 20, -530);
		//lift set points A
		points(1, 0, 0);
		points(1, 1, 0);
		points(1, 2, 0);
		points(1, 3, 0);
		points(1, 4, 0);
		points(1, 5, 0);
		points(1, 6, 0);
		points(1, 7, 0);
		points(1, 8, 0);
		points(1, 9, 0);
		points(1, 10, 0);
		points(1, 11, 0);
		points(1, 12, 0);
		points(1, 13, 0);
		points(1, 14, 0);
		points(1, 15, 0);
		points(1, 16, 0);
		points(1, 17, 0);
		points(1, 18, 0);
		points(1, 19, 0);
		points(1, 20, 0);
		//lift set points B
		points(2, 0, 0);
		points(2, 1, 0);
		points(2, 2, 0);
		points(2, 3, 0);
		points(2, 4, 0);
		points(2, 5, 0);
		points(2, 6, 0);
		points(2, 7, 0);
		points(2, 8, 0);
		points(2, 9, 0);
		points(2, 10, 0);
		points(2, 11, 0);
		points(2, 12, 0);
		points(2, 13, 0);
		points(2, 14, 0);
		points(2, 15, 0);
		points(2, 16, 0);
		points(2, 17, 0);
		points(2, 18, 0);
		points(2, 19, 0);
		points(2, 20, 0);
		//lift set points C
		points(3, 0, 0);
		points(3, 1, 0);
		points(3, 2, 0);
		points(3, 3, 0);
		points(3, 4, 0);
		points(3, 5, 0);
		points(3, 6, 0);
		points(3, 7, 0);
		points(3, 8, 0);
		points(3, 9, 0);
		points(3, 10, 0);
		points(3, 11, 0);
		points(3, 12, 0);
		points(3, 13, 0);
		points(3, 14, 0);
		points(3, 15, 0);
		points(3, 16, 0);
		points(3, 17, 0);
		points(3, 18, 0);
		points(3, 19, 0);
		points(3, 20, 0);
		//arm set points
		points(4, 0, 0);
		points(4, 1, 0);
		points(4, 2, 968);
		points(4, 3, 968);
		points(4, 4, 968);
		points(4, 5, 968);
		points(4, 6, 968);
		points(4, 7, 968);
		points(4, 8, 968);
		points(4, 9, 968);
		points(4, 10, 968);
		points(4, 11, 1080);
		points(4, 12, 1195);
		points(4, 13, 1320);
		points(4, 14, 1320);
		points(4, 15, 1320);
		points(4, 16, 1320);
		points(4, 17, 1320);
		points(4, 18, 1365);
		points(4, 19, 1365);
		points(4, 20, 1365);
		//trolley forward soft limits
		points(5, 0, -530);
		points(5, 1, -530);
		points(5, 2, -530);
		points(5, 3, -530);
		points(5, 4, -530);
		points(5, 5, -530);
		points(5, 6, -530);
		points(5, 7, -530);
		points(5, 8, -530);
		points(5, 9, -530);
		points(5, 10, -530);
		points(5, 11, -530);
		points(5, 12, -530);
		points(5, 13, -530);
		points(5, 14, -530);
		points(5, 15, -530);
		points(5, 16, -530);
		points(5, 17, -530);
		points(5, 18, -530);
		points(5, 19, -530);
		points(5, 20, -530);
		//trolley reverse soft limits
		points(6, 0, -100);
		points(6, 1, -100);
		points(6, 2, -100);
		points(6, 3, -100);
		points(6, 4, -100);
		points(6, 5, -100);
		points(6, 6, -100);
		points(6, 7, -100);
		points(6, 8, -100);
		points(6, 9, -100);
		points(6, 10, -100);
		points(6, 11, -100);
		points(6, 12, -100);
		points(6, 13, -100);
		points(6, 14, -100);
		points(6, 15, -100);
		points(6, 16, -100);
		points(6, 17, -100);
		points(6, 18, -100);
		points(6, 19, -100);
		points(6, 20, -100);
		//arm forward soft limits
		points(7, 0, 968);
		points(7, 1, 968);
		points(7, 2, 968);
		points(7, 3, 968);
		points(7, 4, 968);
		points(7, 5, 968);
		points(7, 6, 968);
		points(7, 7, 968);
		points(7, 8, 968);
		points(7, 9, 968);
		points(7, 10, 968);
		points(7, 11, 2503);
		points(7, 12, 2503);
		points(7, 13, 2503);
		points(7, 14, 2503);
		points(7, 15, 2503);
		points(7, 16, 2503);
		points(7, 17, 2503);
		points(7, 18, 2503);
		points(7, 19, 2503);
		points(7, 20, 2503);
		//arm reverse soft limits
		points(8, 0, 0);
		points(8, 1, 0);
		points(8, 2, 0);
		points(8, 3, 0);
		points(8, 4, 0);
		points(8, 5, 0);
		points(8, 6, 0);
		points(8, 7, 0);
		points(8, 8, 0);
		points(8, 9, 0);
		points(8, 10, 0);
		points(8, 11, 1080);
		points(8, 12, 1080);
		points(8, 13, 1080);
		points(8, 14, 1080);
		points(8, 15, 1080);
		points(8, 16, 1080);
		points(8, 17, 1080);
		points(8, 18, 1080);
		points(8, 19, 1080);
		points(8, 20, 1080);
		//trolley positive adjustment
		points(9, 0, 0);
		points(9, 1, 0);
		points(9, 2, 0);
		points(9, 3, 0);
		points(9, 4, 0);
		points(9, 5, 0);
		points(9, 6, 0);
		points(9, 7, 0);
		points(9, 8, 0);
		points(9, 9, 0);
		points(9, 10, 0);
		points(9, 11, 0);
		points(9, 12, 0);
		points(9, 13, 0);
		points(9, 14, 0);
		points(9, 15, 0);
		points(9, 16, 0);
		points(9, 17, 0);
		points(9, 18, 0);
		points(9, 19, 0);
		points(9, 20, 0);
		//trolley negative adjustment
		points(10, 0, 0);
		points(10, 1, 0);
		points(10, 2, 0);
		points(10, 3, 0);
		points(10, 4, 0);
		points(10, 5, 0);
		points(10, 6, 0);
		points(10, 7, 0);
		points(10, 8, 0);
		points(10, 9, 0);
		points(10, 10, 0);
		points(10, 11, 0);
		points(10, 12, 0);
		points(10, 13, 0);
		points(10, 14, 0);
		points(10, 15, 0);
		points(10, 16, 0);
		points(10, 17, 0);
		points(10, 18, 0);
		points(10, 19, 0);
		points(10, 20, 0);
		//arm positive adjustment
		points(11, 0, 0);
		points(11, 1, 0);
		points(11, 2, 0);
		points(11, 3, 0);
		points(11, 4, 0);
		points(11, 5, 0);
		points(11, 6, 0);
		points(11, 7, 0);
		points(11, 8, 0);
		points(11, 9, 0);
		points(11, 10, 0);
		points(11, 11, 0);
		points(11, 12, 0);
		points(11, 13, 0);
		points(11, 14, 0);
		points(11, 15, 0);
		points(11, 16, 0);
		points(11, 17, 0);
		points(11, 18, 0);
		points(11, 19, 0);
		points(11, 20, 0);
		//arm negative adjustment 
		points(12, 0, 0);
		points(12, 1, 0);
		points(12, 2, 0);
		points(12, 3, 0);
		points(12, 4, 0);
		points(12, 5, 0);
		points(12, 6, 0);
		points(12, 7, 0);
		points(12, 8, 0);
		points(12, 9, 0);
		points(12, 10, 0);
		points(12, 11, 0);
		points(12, 12, 0);
		points(12, 13, 0);
		points(12, 14, 0);
		points(12, 15, 0);
		points(12, 16, 0);
		points(12, 17, 0);
		points(12, 18, 0);
		points(12, 19, 0);
		points(12, 20, 0);
	}

	protected void execute() {
		double liftPot = RobotMap.lift_right.getSelectedSensorPosition(0);
		
		//trolleyStick = OI.operatorThrottleJoystick.getRawAxis(1);
		
		throttleValue = calcThrottlePos;
		
		trolleySetPoint = ((double) points[(int) throttleValue][4]);
		armAdjPos = ((double) points[(int) throttleValue][11]);
		armAdjNeg = ((double) points[(int) throttleValue][12]);
		
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
		
		if(!Robot.arm.sameSide(armEncoder, trolleySetPoint)) {
			trolleySetPoint = (double) points[10][0];
		//	System.out.println("Not On The Same Side");
		}
		else {
			//System.out.println("On The Same Side");
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
	
	public void points(int x, int y, int value) {
		Robot.bigBrother.points(x, y, value);
	}
}
