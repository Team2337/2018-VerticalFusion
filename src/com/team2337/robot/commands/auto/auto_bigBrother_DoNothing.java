package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turns off BigBrother to allow for manual movements of the arm lift and trolley
 * Keeps the arm lift and trolley from going their tele op set points
 * @category AUTO-BIGBROTHER
 * @author Bryce G., Sean L
 */
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
