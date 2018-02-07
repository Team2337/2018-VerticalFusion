package com.team2337.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Claw: OPEN - Opens the claw
 * @category CLAW
 * @author Brendan
 */
public class claw_open extends Command {
	public claw_open() {
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
		Robot.claw.give30Hugs();
	}
	@Override
	protected void interrupted() {
		this.end();
	}
}
