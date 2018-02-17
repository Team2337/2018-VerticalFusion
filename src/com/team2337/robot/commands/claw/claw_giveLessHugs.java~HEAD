package com.team2337.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;


/**
 * Claw: LESSHUGS - Reduces the pressure to the lower limit (30psi)
 * @category CLAW
 * @author Brendan
 */
public class claw_giveLessHugs extends Command {
	public claw_giveLessHugs() {
		requires(Robot.claw);
	}
	@Override
	protected void initialize() {
		Robot.claw.give30Hugs();
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
