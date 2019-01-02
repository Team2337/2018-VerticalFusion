package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Holds the position of the arm and trolley
 * 
 * @category AUTO-BIGBROTHER
 * @author Bryce G.
 */
public class auto_holdUpperPosition extends Command {
	double timeout;
	
	public auto_holdUpperPosition(double timeout) {
		requires(Robot.arm);
		this.timeout = timeout;
	}
	@Override
	protected void initialize() {
		setTimeout(timeout);
		Robot.arm.holdPosition();
		Robot.trolley.holdPosition();
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
