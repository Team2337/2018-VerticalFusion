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
 * Arm: STOPPID - Stops the PID of the lift
 * 
 * @category ARM
 * @author Bryce 
 */
public class arm_stopPID extends Command {
	public arm_stopPID() {
		requires(Robot.arm);
	}

	protected void initialize() {
		Robot.arm.stopPID();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
