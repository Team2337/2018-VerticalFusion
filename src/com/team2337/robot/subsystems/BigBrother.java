package com.team2337.robot.subsystems;

import com.team2337.robot.commands.bigBrother.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * BIGBROTHER IS ALWAYS WATCHING
 * 
 * @category BIGBROTHER
 * @author Team2337 - EngiNERDs
 */
public class BigBrother extends Subsystem {

	int totalRows = 20;
	int totalColumns = 13;

	public double points[][] = new double[totalRows][totalColumns];

	public BigBrother() {
		startFilling();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new alt_Control_Import());
		
	}

	void startFilling() {
		//points = setPointsTestPickUpOnly.points;
		//points = setPoints.points;
		//points = setPointsPracticeBot.points;
		
		if (Robot.isComp) {
			points = setPointsCompBot.points;
			System.out.println("Loaded Comp Bot Array");
		} else {
			points = setPointsPracticeBot.points;
			System.out.println("Loaded Practice Bot Array");
		}

	}
	public void stopAltControl() {
		RobotMap.trolley_right.set(ControlMode.PercentOutput, 0);
		RobotMap.lift_right.set(ControlMode.PercentOutput, 0);
		RobotMap.arm_right.set(ControlMode.PercentOutput, 0);
	}
	public void holdAltControl() {
		RobotMap.trolley_right.set(ControlMode.Position, Robot.trolley.getPosition());
		RobotMap.lift_right.set(ControlMode.Position, Robot.lift.getPosition());
		RobotMap.arm_right.set(ControlMode.Position, Robot.arm.getPosition());

	}

}
