package com.team2337.robot.commands.lifter;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class liftLevelAdjuster extends Command {
	int liftLevel;

	public liftLevelAdjuster(int liftLevel) {
		this.liftLevel = liftLevel;
		requires(Robot.lifter);
	}

	@Override
	protected void initialize() {
		Robot.lifter.liftLeveler(liftLevel);
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
