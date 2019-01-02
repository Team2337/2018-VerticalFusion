package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Claw: OPEN - Opens the claw
 * @category AUTO-CLAW
 * @author Brendan F.
 */
public class auto_clawOpen extends Command {
	public auto_clawOpen() {
		requires(Robot.claw);
	}
	@Override
	protected void initialize() {
		Robot.claw.open();
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
