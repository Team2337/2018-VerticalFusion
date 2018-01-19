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
 * Lifter: SETPID - Moves the lifter based of a PID set
 * 
 * @category LIFTER
 * @author - Bryce
 */
public class lifter_setPID extends Command {
	private double pos = 0;

	public lifter_setPID(double pos) {
		requires(Robot.lifter);

		this.pos = pos;
	}

	protected void initialize() {
		Robot.lifter.enable(); // Sets the position of the lifter PID (variable grabbed from OI)
		Robot.lifter.setPosition(this.pos);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return (Robot.lifter.onTarget());
	}

	protected void end() {
		Robot.lifter.enable();
		Robot.lifter.setPosition(Robot.lifter.getPosition());
		// When the command ends or is interrupted it will keep the lifter from dropping
		// back to a bad position
	}

	protected void interrupted() {
		this.end();
	}
}
