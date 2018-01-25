/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

import com.team2337.robot.Robot;

/**
 * Arm: STARTPID - Starts the PID of the lift
 * 
 * @category ARM
 * @author - Bryce
 */
public class arm_startPID extends Command {
	public static boolean set = false; 
	public arm_startPID() {
		requires(Robot.arm);
	}

	protected void initialize() {
		Robot.arm.startPID();
		if(!arm_startPID.set) {
			Robot.arm.setPosition(Robot.arm.getPosition());
			arm_startPID.set = true;
		}
		
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
