/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.lifter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class main extends Command {
	private Joystick operatorJoystick = Robot.oi.getOperatorJoystick();
	public main() {
		requires(Robot.lifter);
	}

	protected void initialize() {
		
	}

	protected void execute() {
		double power = operatorJoystick.getRawAxis(1);
		
		Robot.lifter.verticleMovement(power);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
