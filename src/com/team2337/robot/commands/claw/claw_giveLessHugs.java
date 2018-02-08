/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
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
