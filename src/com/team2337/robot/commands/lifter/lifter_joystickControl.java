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
 * Lifter: JOYSTICKCONTROL - Moves the lifter based joystick
 * @category LIFTER
 * @author -
 */
public class lifter_joystickControl extends Command {
	public lifter_joystickControl() {
		requires(Robot.lifter);
	}
	
	protected void initialize() {}
	protected void execute() {}
	protected boolean isFinished() {return false;}
	protected void end() {}
	protected void interrupted() {this.end();}
}
