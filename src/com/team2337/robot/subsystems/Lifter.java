/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.chassis.drive;
import com.team2337.robot.commands.lifter.main;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Lifter extends Subsystem {
	
	private TalonSRX left = RobotMap.liftLeftTop; 
	private TalonSRX right = RobotMap.liftRightTop;
	
	private double turnSensitivtiy = 1;
	private double deadband = 0.2;
	
	public void verticleMovement(double power)
	{
		power /= 2;
		
		this.left.set(ControlMode.PercentOutput, power);
		this.left.set(ControlMode.PercentOutput, power);
		
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new main());
	}

}
