package com.team2337.robot.subsystems;

import com.team2337.robot.commands.bigBrother.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Importing important points since 2018
 */
public class BigBrother extends Subsystem {

	int totalRows = 20;
	int totalColumns = 8;

	public double points[][] = new double[totalRows][totalColumns];

	public BigBrother() {
		startFilling();
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new alt_Control_Import());
	}

	void startFilling() {
		//points = setPointsTestPickUpOnly.points;
		points = setPoints.points;
		//points = setPointsPracticeBot.points;
		//points = setPointsCompBot.points;
	}
	public void stopAltControl() {
		RobotMap.trolley_right.set(ControlMode.PercentOutput, 0);
		RobotMap.lift_right.set(ControlMode.PercentOutput, 0);
		RobotMap.arm_right.set(ControlMode.PercentOutput, 0);
	}
	public void startAltControl(double pos) {

	}

}
