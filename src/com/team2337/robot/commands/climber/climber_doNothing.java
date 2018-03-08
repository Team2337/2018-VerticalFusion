package com.team2337.robot.commands.climber;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class climber_doNothing extends Command{

	public climber_doNothing() {
		requires(Robot.climber);
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
