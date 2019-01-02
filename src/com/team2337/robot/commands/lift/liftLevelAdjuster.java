package com.team2337.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Called by throttle side buttons to set motion array of lift based on scale position.
 * Ex: Level 1: lift low, Level 2: lift mid, Level 3: lift high
 * 
 * @category lift
 * @author Bryce G.
 */
public class liftLevelAdjuster extends Command {
	int liftLevel;
	//boolean climb = Robot.lift.climb;

	/**
	 * Determines the lift points based off the given vaue from the side buttons on the flight stick
	 * @param liftLevel - Column of the array for the lift level (Columns 1, 2, & 3)
	 */
	public liftLevelAdjuster(int liftLevel) {
		this.liftLevel = liftLevel;
		requires(Robot.lift);
	}

	@Override
	protected void initialize() {
		Robot.lift.liftLeveler(liftLevel);
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
