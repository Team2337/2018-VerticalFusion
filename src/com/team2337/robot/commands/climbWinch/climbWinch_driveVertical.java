package com.team2337.robot.commands.climbWinch;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class climbWinch_driveVertical extends Command{

	double power;
	public climbWinch_driveVertical(double power) {
		requires(Robot.climbWinch);
		this.power = power;
	}

	protected void initialize() {

	}

	protected void execute() {
		Robot.climbWinch.driveVertical(power);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.climbWinch.stop();
	}

	protected void interrupted() {
		this.end();
	}
}
