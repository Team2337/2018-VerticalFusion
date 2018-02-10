/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.trolley;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

import com.team2337.robot.Robot;

/**
 * Lifter: STARTPID - Starts the PID of the lift
 * 
 * @category LIFTER
 * @author - Bryce
 */
public class trolley_startPID extends Command {
	public static boolean set = false; 
	public trolley_startPID() {
		requires(Robot.trolley);
	}
//  NOT NEEDED for TALONS
	protected void initialize() {
		//Robot.trolley.startPID();
		if(!trolley_startPID.set) {
		Robot.trolley.setPosition(Robot.trolley.getPosition());
		trolley_startPID.set = true;
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
