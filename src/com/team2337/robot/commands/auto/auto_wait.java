package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Waits a given amount of time until the next command is run
 * 
 * @category AUTO
 * @author Team2337 - EngiNERDs
 */
public class auto_wait extends Command {
	double timeout;
	
	/**
	 * @param timeout
	 * Amount of time the command runs for until it is forced to terminate, in milliseconds
	 */
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
