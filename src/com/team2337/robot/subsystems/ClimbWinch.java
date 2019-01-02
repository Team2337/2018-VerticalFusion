/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.climbWinch.climbWinch_doNothing;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Winches the hook rope up to pull the robot off the ground, when the hook is in position 
 * 
 * @category CLIMBER
 * @author Brendan F. 
 */
public class ClimbWinch extends Subsystem {

	private VictorSPX climbMotor = RobotMap.climb_motor;
			
	public ClimbWinch() {
	
	}
	public void initDefaultCommand() {
		setDefaultCommand(new climbWinch_doNothing());
	}

	/**
	 * Drives the winch in the forward direction
	 * @param power Percent of the motors power (Value of 0-1)
	 */
	public void driveVertical(double power) {
		climbMotor.set(ControlMode.PercentOutput, power);
	}

	/**
	 * Stops the winch
	 */
	public void stop() {
		climbMotor.neutralOutput();
	}
	
}
