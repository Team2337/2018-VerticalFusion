/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.subsystems;

import com.team2337.robot.commands.bigBrother.setPoints;
import com.team2337.robot.commands.bigBrother.alt_Control_Import;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Importing important points since 2018
 */
public class BigBrother extends Subsystem {
	
	int totalRows = 20;
	int totalColumns = 12;
	
	public double points[][] = new double[totalRows][totalColumns];
	
	public BigBrother() {
		startFilling();
	}
	public void initDefaultCommand() {
		setDefaultCommand(new alt_Control_Import());
	}
	void startFilling() {
		points = setPoints.points;
	}
}
