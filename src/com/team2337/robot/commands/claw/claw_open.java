package com.team2337.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Opens the claw
 * 
 * @category CLAW
 * @author Brendan F.
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
		
	}
	@Override
	protected void interrupted() {
		this.end();
	}
}
