/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.lifter;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class liftLevelAdjuster extends Command {
	int liftLevel;
	public liftLevelAdjuster(int liftLevel) {
		this.liftLevel = liftLevel;
		requires(Robot.lifter);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.lifter.liftLeveler(liftLevel);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//runs 20 times a second
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		this.end();
	}
}
