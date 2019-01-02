package com.team2337.robot.subsystems;

import com.team2337.robot.commands.bigBrother.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Used to control the arm lift and trolley subsystems simotaneously
 * 
 * @category BIGBROTHER
 * @author Team2337 - EngiNERDs
 */
public class BigBrother extends Subsystem {

	int totalRows = 20;
	int totalColumns = 13;

	//Arm, Lift, and Trolley set points array
	public double points[][] = new double[totalRows][totalColumns];

	public BigBrother() {
		startFilling();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new alt_Control_Import());
		
	}

	/**
	 * Loads the arm, lift, and trolley setpoints array
	 */
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

	/**
	 * Sets the Percent Output (speed in percent) of the arm, lift, and trolley to zero 
	 */
	public void stopAltControl() {
		RobotMap.trolley_right.set(ControlMode.PercentOutput, 0);
		RobotMap.lift_right.set(ControlMode.PercentOutput, 0);
		RobotMap.arm_right.set(ControlMode.PercentOutput, 0);
	}

	/**
	 * Holds the current position of the arm, lift, and trolley 
	 * (Sets the positoin to the current position by getting the encoder & string pot position of each subsystem)
	 */
	public void holdAltControl() {
		RobotMap.trolley_right.set(ControlMode.Position, Robot.trolley.getPosition());
		RobotMap.lift_right.set(ControlMode.Position, Robot.lift.getPosition());
		RobotMap.arm_right.set(ControlMode.Position, Robot.arm.getPosition());

	}

}
