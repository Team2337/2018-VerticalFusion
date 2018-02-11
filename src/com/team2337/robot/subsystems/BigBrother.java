package com.team2337.robot.subsystems;

import com.team2337.robot.commands.bigBrother.setPoints;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.bigBrother.alt_Control_Import;
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
		setDefaultCommand(new alt_Control_Import());
	}

	void startFilling() {
		points = setPoints.points;
	}
	public void stopAltControl() {
		RobotMap.trolley_right.set(ControlMode.PercentOutput, 0);
		RobotMap.lift_right.set(ControlMode.PercentOutput, 0);
		RobotMap.arm_right.set(ControlMode.PercentOutput, 0);
	}

}
