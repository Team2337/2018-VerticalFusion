/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.trolley;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Lifter: SETPID - Moves the lifter based of a PID set
 * 
 * @category LIFTER
 * @author - Bryce
 */
public class trolley_setPID extends Command {
	private double pos = 0;

	public trolley_setPID(double pos) {
		requires(Robot.trolley);

		this.pos = pos;
	}

	protected void initialize() {
		Robot.trolley.enable(); // Sets the position of the lifter PID (variable grabbed from OI)
		Robot.trolley.setPosition(this.pos);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return (Robot.trolley.onTarget());
	}

	protected void end() {
		Robot.trolley.enable();
		Robot.trolley.setPosition(Robot.trolley.getPosition());
		// When the command ends or is interrupted it will keep the lifter from dropping
		// back to a bad position
	}

	protected void interrupted() {
		this.end();
	}
}
