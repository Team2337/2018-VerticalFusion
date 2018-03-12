package com.team2337.robot.commands.PTO;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PTO_DoNothing extends Command{

	public PTO_DoNothing() {
		requires(Robot.pto);
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
