package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Claw: CLOSE - Closes the claw
 * @category CLAW
 * @author Brendan
 */
public class auto_claw60 extends Command {
	public auto_claw60() {
		requires(Robot.claw);
	}
	@Override
	protected void initialize() {
		Robot.claw.give60Hugs();;
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
