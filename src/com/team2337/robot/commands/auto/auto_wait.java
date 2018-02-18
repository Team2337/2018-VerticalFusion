package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

public class auto_wait extends Command {
	double timeout;
	
	public auto_wait(double timeout) {
		this.timeout = timeout;
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
