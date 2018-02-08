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
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class setPointsChecking extends Command {

	int points[][] = Robot.bigBrother.points;
	
	boolean setPointSet = false;
	
	double fineValue;
	double fineArm;
	double armAdj;
	double trolleyAdj;
	
	double armRight;
	
	public double trolleyStick; 
	public static double throttleValue;
	
	public setPointsChecking() {
		requires(Robot.bigBrother);
	}

	protected void initialize() {
		Robot.trolley.enable();
		setPointSet = false;
		fineValue = ((double) points[(int) throttleValue][0]);
		
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
		//lift set points
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
		//arm set points
		points(2, 0, 0);
		points(2, 1, 0);
		points(2, 2, 968);
		points(2, 3, 968);
		points(2, 4, 968);
		points(2, 5, 968);
		points(2, 6, 968);
		points(2, 7, 968);
		points(2, 8, 968);
		points(2, 9, 968);
		points(2, 10, 1080);
		points(2, 11, 1195);
		points(2, 12, 1320);
		points(2, 13, 0);
		points(2, 14, 0);
		points(2, 15, 0);
		points(2, 16, 0);
		points(2, 17, 0);
		points(2, 18, 0);
		points(2, 19, 1365);
		points(2, 20, 0);
		//lift forward soft limits
		points(3, 0, -530);
		points(3, 1, -530);
		points(3, 2, -530);
		points(3, 3, -530);
		points(3, 4, -530);
		points(3, 5, -530);
		points(3, 6, -530);
		points(3, 7, -530);
		points(3, 8, -530);
		points(3, 9, -530);
		points(3, 10, -530);
		points(3, 11, -530);
		points(3, 12, -530);
		points(3, 13, -530);
		points(3, 14, -530);
		points(3, 15, -530);
		points(3, 16, -530);
		points(3, 17, -530);
		points(3, 18, -530);
		points(3, 19, -530);
		points(3, 20, -530);
		//lift reverse soft limits
		points(4, 0, 0);
		points(4, 1, 0);
		points(4, 2, 0);
		points(4, 3, 0);
		points(4, 4, 0);
		points(4, 5, 0);
		points(4, 6, 0);
		points(4, 7, 0);
		points(4, 8, 0);
		points(4, 9, 0);
		points(4, 10, 0);
		points(4, 11, 0);
		points(4, 12, 0);
		points(4, 13, 0);
		points(4, 14, 0);
		points(4, 15, 0);
		points(4, 16, 0);
		points(4, 17, 0);
		points(4, 18, 0);
		points(4, 19, 0);
		points(4, 20, 0);
		//arm forward soft limits
		points(5, 0, 0);
		points(5, 1, 0);
		points(5, 2, 0);
		points(5, 3, 0);
		points(5, 4, 0);
		points(5, 5, 0);
		points(5, 6, 0);
		points(5, 7, 0);
		points(5, 8, 0);
		points(5, 9, 0);
		points(5, 10, 0);
		points(5, 11, 0);
		points(5, 12, 0);
		points(5, 13, 0);
		points(5, 14, 0);
		points(5, 15, 0);
		points(5, 16, 0);
		points(5, 17, 0);
		points(5, 18, 0);
		points(5, 19, 0);
		points(5, 20, 0);
		//arm reverse soft limits
		points(6, 0, 0);
		points(6, 1, 0);
		points(6, 2, 0);
		points(6, 3, 0);
		points(6, 4, 0);
		points(6, 5, 0);
		points(6, 6, 0);
		points(6, 7, 0);
		points(6, 8, 0);
		points(6, 9, 0);
		points(6, 10, 0);
		points(6, 11, 0);
		points(6, 12, 0);
		points(6, 13, 0);
		points(6, 14, 0);
		points(6, 15, 0);
		points(6, 16, 0);
		points(6, 17, 0);
		points(6, 18, 0);
		points(6, 19, 0);
		points(6, 20, 0);
		//trolley positive adjustment
		points(7, 0, 0);
		points(7, 1, 0);
		points(7, 2, 0);
		points(7, 3, 0);
		points(7, 4, 0);
		points(7, 5, 0);
		points(7, 6, 0);
		points(7, 7, 0);
		points(7, 8, 0);
		points(7, 9, 0);
		points(7, 10, 0);
		points(7, 11, 0);
		points(7, 12, 0);
		points(7, 13, 0);
		points(7, 14, 0);
		points(7, 15, 0);
		points(7, 16, 0);
		points(7, 17, 0);
		points(7, 18, 0);
		points(7, 19, 0);
		points(7, 20, 0);
		//trolley negative adjustment
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
		points(8, 11, 0);
		points(8, 12, 0);
		points(8, 13, 0);
		points(8, 14, 0);
		points(8, 15, 0);
		points(8, 16, 0);
		points(8, 17, 0);
		points(8, 18, 0);
		points(8, 19, 0);
		points(8, 20, 0);
		//arm positive adjustment
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
		//arm negative adjustment 
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
	}

	protected void execute() {
		double sensorPosition = RobotMap.lift_rightFront.getSelectedSensorPosition(0);
		
		trolleyStick = OI.throttleJoystick.getRawAxis(1);
		throttleValue = -OI.throttleJoystick.getRawAxis(2);
		throttleValue = (int) (throttleValue*10)+10;;
		
		armRight =  RobotMap.arm_right.getSelectedSensorPosition(0);
		/* 
		 * Points for X
		 * 0: trolley set points
		 * 1: lift set points
		 * 2: arm set points
		 * 3: trolley forward soft limits 
		 * 4: trolley reverse soft limits
		 * 5: arm forward soft limits
		 * 6: arm reverse soft limits
		 * 7: trolley positive adjustment
		 * 8: trolley negative adjustment
		 * 9: arm positive adjustment 
		 *10: arm negative adjustment
		 */
		
		trolleyAdj = 2;
		if(trolleyStick > 0.1) {
			fineValue += trolleyStick; //(trolleyAdj * trolleyStick);
			Robot.trolley.setPosition(fineValue);
		}
		else if(trolleyStick < -0.1) {
			fineValue += trolleyStick; // (trolleyAdj * trolleyStick);
			Robot.trolley.setPosition(fineValue);
		}
		else {
		fineValue = ((double) points[(int) throttleValue][0]);
		trolleyAdj = 2;
		if((double) points[(int) throttleValue][1] >= armRight || ((double) points[(int) throttleValue][1] <= armRight)) {
		Robot.trolley.setSetpoint((double) points[10][0]);
		}
		else {
		Robot.trolley.setSetpoint((double) points[(int) throttleValue][0]);
		}
		}
			
		//Lifter.setSoftLimits(-60, -530);
		
		SmartDashboard.putNumber("trolleyStick", trolleyStick);
		SmartDashboard.putNumber("LIFTLocationPID", Robot.trolley.getSetpoint());
		SmartDashboard.putNumber("selectedSensorPositionLIFTER", RobotMap.lift_rightFront.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("throttleValue", throttleValue);
		SmartDashboard.putNumber("throttleArrayValue", points[(int) throttleValue][0]);
		SmartDashboard.putNumber("throttleSetPoints", points[(int) throttleValue][0]);
		SmartDashboard.putNumber("test", fineValue);
		
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
