/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Using the encoder, it moves the arm to a given set point
 * 
 * @category arm
 * @author Bryce G.
 */
public class arm_setPID extends Command {
	private double pos = 0;

	/**
	 * Sets the set point position of the arm 
	 * @param pos Set point position of the arm ranging from -1000 - 1500 (0-4096 = full rotation)
	 */
	public arm_setPID(double pos) {
		requires(Robot.arm);

		this.pos = pos;
	}

	protected void initialize() {
		Robot.arm.setSetpoint(this.pos);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return true;   //TODO how to determine if on target ??(Robot.arm.onTarget());

	}

	protected void end() {
		Robot.arm.setSetpoint(Robot.arm.getPosition());
		// When the command ends or is interrupted it will keep the arm from dropping
		// back to a bad position
	}

	protected void interrupted() {
		this.end();
	}
}
