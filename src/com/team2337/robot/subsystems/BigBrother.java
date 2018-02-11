/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.subsystems;

import com.team2337.robot.commands.bigBrother.setPoints;
import com.team2337.robot.commands.bigBrother.setPointsChecking;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class BigBrother extends Subsystem {
	/* 
	 * Columns
	 * 0: trolley set points
	 * 1: lift set points A
	 * 2: lift set points B
	 * 3: lift set points C
	 * 4: arm set points
	 * 5: trolley forward soft limits 
	 * 6: trolley reverse soft limits
	 * 7: arm forward soft limits
	 * 8: arm reverse soft limits
	 * 9: trolley positive adjustment
	 *10: trolley negative adjustment
	 *11: arm positive adjustment 
	 *12: arm negative adjustment
	 */
	
	int totalColumns = 13;
	int totalRows = 21;
	
	public double points[][] = new double[totalRows][totalColumns];
	
	public BigBrother() {
		startFilling();
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new setPointsChecking());
	}

	public void points(int x, int y, int value) {
		points[y][x] = value;
	}
	void startFilling() {
		startFilling(setPoints.points);
	}
	void startFilling(double[][] pointsIn) {
		for(int column=0; column<totalColumns; column++) {
			for(int row=0; row<totalRows; row++) {
				points[row][column] = pointsIn[row][column];
			}
		}
	}

}