package com.team2337.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Claw: MOREHUGS - Sets the pressure to the upper limit (60psi)
 * @category CLAW
 * @author Brendan
 */
public class claw_giveMoreHugs extends Command {
	public claw_giveMoreHugs() {
		requires(Robot.claw);
	}
	@Override
	protected void initialize() {
		Robot.claw.give60Hugs();
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
