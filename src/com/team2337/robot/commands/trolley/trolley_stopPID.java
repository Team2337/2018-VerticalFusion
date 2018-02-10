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
 * Lifter: STOPPID - Stops the PID of the lift
 * 
 * @category LIFTER
 * @author - Bryce
 */
public class trolley_stopPID extends Command {
	public trolley_stopPID() {
		requires(Robot.trolley);
	}

	protected void initialize() {
	//	Robot.trolley.stopPID();   //  NOT NEEDED FOR TALONS
		
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
