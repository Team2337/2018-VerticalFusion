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
 * arm: SETPID - Moves the arm based of a PID set
 * 
 * @category arm
 * @author - Bryce
 */
public class arm_setPID extends Command {
	private double pos = 0;

	public arm_setPID(double pos) {
		requires(Robot.arm);

		this.pos = pos;
	}

	protected void initialize() {
		Robot.arm.enable(); // Sets the position of the arm PID (variable grabbed from OI)
		Robot.arm.setPosition(this.pos);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return (Robot.arm.onTarget());
	}

	protected void end() {
		Robot.arm.enable();
		Robot.arm.setPosition(Robot.arm.getPosition());
		// When the command ends or is interrupted it will keep the arm from dropping
		// back to a bad position
	}

	protected void interrupted() {
		this.end();
	}
}
