/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.ejector;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.subsystems.Ejector;
/**
 * An example command.  You can replace me with your own command.
 */
public class extend extends Command {
	public double pos;
	public extend(double pos) {
		this.pos = pos;
		// Use requires() here to declare subsystem dependencies
		requires(Robot.ejector);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//use this.pos here
		Robot.ejector.extend();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//runs 20 times a second
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
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
