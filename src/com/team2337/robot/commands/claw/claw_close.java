package com.team2337.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Claw: CLOSE - Closes the claw
 * @category CLAW
 * @author Brendan
 */
public class claw_close extends Command {
	public claw_close() {
		requires(Robot.claw);
	}
	@Override
	protected void initialize() {
		Robot.claw.close();
	}
	@Override
	protected void execute() {
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	@Override
	protected void end() {
	}
	@Override
	protected void interrupted() {
		this.end();
	}
}
