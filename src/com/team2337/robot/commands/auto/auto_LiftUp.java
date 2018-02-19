package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class auto_LiftUp extends Command{
	
	int col, row;
	/**
	 * 
	 * @param col
	 * @param row 
	 * Row, grabs the position in the array
	 * Col, grabs the type of points in the array 
	 */
	public auto_LiftUp(int col, int row) {
		this.col = col;
		this.row = row;
	}
	

	protected void initialize() {
		Robot.lift.setSetpoint(Robot.bigBrother.points[col][row]);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
