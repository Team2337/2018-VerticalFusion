package com.team2337.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class DoNothing extends Command {
	String ourSwitch, scale;

	public DoNothing(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;

	}
	
	public DoNothing() {

	}

	protected void initialize() {
		//System.out.println(ourSwitch);
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
