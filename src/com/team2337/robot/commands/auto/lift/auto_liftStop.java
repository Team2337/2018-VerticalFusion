package com.team2337.robot.commands.auto.lift;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class auto_liftStop extends Command{

	public auto_liftStop() {
		requires(Robot.lift);
	}

	protected void initialize() {
		Robot.lift.stop();
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
