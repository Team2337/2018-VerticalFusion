package com.team2337.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Opens the claw to release pressure and then close at a different pressure
 * 
 * @category CLAW
 * @author Brendan F.
 */
public class claw_openClose extends Command {
	public claw_openClose() {
		requires(Robot.claw);
	}
	@Override
	protected void initialize() {
		
		Robot.claw.open();
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
		Robot.claw.close();
	}
	@Override
	protected void interrupted() {
		this.end();
	}
}
