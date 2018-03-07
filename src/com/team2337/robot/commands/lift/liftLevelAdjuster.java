package com.team2337.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * called by throttle side buttons to set motion array of lift based on scale
 * position.
 */
public class liftLevelAdjuster extends Command {
	int liftLevel;
	//boolean climb = Robot.lift.climb;

	public liftLevelAdjuster(int liftLevel) {
		this.liftLevel = liftLevel;
		requires(Robot.lift);
	}

	@Override
	protected void initialize() {
		Robot.lift.liftLeveler(liftLevel);
		//if (liftLevel == 9)
		//	climb = true;
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
