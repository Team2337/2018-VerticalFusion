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
 * Lifter: MAIN - Lift code for testing up and down
 * @author Brendan, Bryce
 */
//TODO: Working on real code for the lift (using string prop)
public class lifter_main extends Command {
	private Joystick operatorJoystick = Robot.oi.operatorJoystick;
	public lifter_main() {
		requires(Robot.lifter);
	}
	protected void initialize() {}
	/*
	 * When the contoller on the 'operator' joystick is ran, it move the lift up and down
	 */
	protected void execute() {
		double power = (operatorJoystick.getRawAxis(1))/2;
		if (Math.abs(power) > 0.1) {
			Robot.lifter.verticalMovement(power);	
		} else {
			Robot.lifter.verticalMovement(0);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {}

	protected void interrupted() {
		this.end();
	}
}
