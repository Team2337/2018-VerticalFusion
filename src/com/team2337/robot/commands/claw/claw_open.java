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
