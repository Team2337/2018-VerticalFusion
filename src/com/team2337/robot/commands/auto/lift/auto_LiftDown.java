package com.team2337.robot.commands.auto.lift;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class auto_LiftDown extends Command{
	
	double lift, liftEnd;
	boolean liftDone = false;
	/**
	 * 
	 * @param col
	 * @param row 
	 * Row, grabs the position in the array
	 * Col, grabs the type of points in the array 
	 */
	public auto_LiftDown(double lift) {
		this.lift = lift;
		liftDone = true;
	}
	
	public auto_LiftDown(double lift, double liftEnd) {
		this.lift = lift;
		this.liftEnd = liftEnd;
	}

	protected void initialize() {
		Robot.lift.setSetpoint(lift);
	}

	protected void execute() {
		if(Robot.lift.getPosition() < liftEnd) {
			liftDone = true;
		}
	}

	protected boolean isFinished() {
		return liftDone;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
