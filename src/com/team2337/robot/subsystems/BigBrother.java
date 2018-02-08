/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.subsystems;

import com.team2337.robot.commands.bigBrother.setPointsChecking;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class BigBrother extends Subsystem {
	public int points[][] = new int[21][11];
	/*
	 * 0: lift SP1
	 * 1: lift SP2
	 * 2: lift SP3
	 * 3: trolley SP
	 * 4: trolley max
	 * 5: trolley min
	 * 6: arm SP
	 * 7: arm Forward SL
	 * 8: arm Reverse SL
	 * 9: arm max
	 *10: arm min
	 */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new setPointsChecking());
	}

	public void points(int x, int y, int value) {
		points[y][x] = value;
	}

}