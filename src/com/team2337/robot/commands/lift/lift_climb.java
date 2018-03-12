package com.team2337.robot.commands.lift;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class lift_climb extends Command {

	public lift_climb() {
		requires(Robot.lift);
	}

	protected void initialize() {
		
	}

	protected void execute() {
		Robot.lift.move(1);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.lift.move(0);
	}

	protected void interrupted() {
		this.end();
	}
}
