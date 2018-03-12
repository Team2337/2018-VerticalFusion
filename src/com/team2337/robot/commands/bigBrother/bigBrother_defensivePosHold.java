package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class bigBrother_defensivePosHold extends Command {

	public bigBrother_defensivePosHold() {
		requires(Robot.bigBrother);
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
