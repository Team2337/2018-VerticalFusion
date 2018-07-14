package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class auto_armMoveWithFinish extends Command{
	
	double arm, armEnd;
	boolean armDone = false;
	/**
	 * 
	 * @param col
	 * @param row 
	 * Row, grabs the position in the array
	 * Col, grabs the type of points in the array 
	 */
	public auto_armMoveWithFinish(double arm) {
		this.arm = arm;
		armDone = true;
	}
	
	/**
	 * This command sets the setpoint on the arm, and does NOT end the command until a certain point has been reached
	 * @param arm
	 * Desired Setpoint of the arm
	 * @param armEnd
	 * Position in which the arm should be at before ending the command 
	 */
	public auto_armMoveWithFinish(double arm, double armEnd) {
		this.arm = arm;
		this.armEnd = armEnd;
	}

	protected void initialize() {
		Robot.arm.setSetpoint(arm);
	}

	protected void execute() {
		if(Robot.arm.getPosition() > armEnd) {
			armDone = true;
		}
	}

	protected boolean isFinished() {
		return armDone;
	}

	protected void end() {
		System.out.println("arm ended");
	}

	protected void interrupted() {
		this.end();
	}
}
