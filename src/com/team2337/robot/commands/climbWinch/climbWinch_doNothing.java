package com.team2337.robot.commands.climbWinch;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class climbWinch_doNothing extends Command{

	public climbWinch_doNothing() {
		requires(Robot.climbWinch);
	}

	protected void initialize() {

	}

	protected void execute() {
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
