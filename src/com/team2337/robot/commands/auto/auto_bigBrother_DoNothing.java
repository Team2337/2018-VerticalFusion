package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class auto_bigBrother_DoNothing extends Command {
	double timeout;
	public auto_bigBrother_DoNothing() {
		requires(Robot.bigBrother);
		timeout = 100;
	}

	protected void initialize() {
		setTimeout(timeout);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
