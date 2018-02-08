/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.subsystems;

import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.claw.claw_DoNothing;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Claw extends Subsystem {
	public static Solenoid left = RobotMap.claw_left;
	public static Solenoid right = RobotMap.claw_right; 

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new claw_DoNothing());
	}

	public void open() {
		left.set(true);
		right.set(true);
	}

	public void close() {
		left.set(false);
		right.set(false);
	}
}
