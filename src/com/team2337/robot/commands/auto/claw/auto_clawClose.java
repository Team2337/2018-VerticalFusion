package com.team2337.robot.commands.auto.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Claw: CLOSE - Closes the claw
 * @category CLAW
 * @author Brendan
 */
public class auto_clawClose extends Command {
	public auto_clawClose() {
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
		return true;
	}
	@Override
	protected void end() {
	}
	@Override
	protected void interrupted() {
		this.end();
	}
}
