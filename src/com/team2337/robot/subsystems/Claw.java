/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.subsystems;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.claw.claw_DoNothing;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The CLAW! 
 * Runs the claw pressure and open and close of it as well
 * 
 * @category CLAW
 * @author Team2337 - EngiNERDs
 */
public class Claw extends Subsystem {
	public static Solenoid hugger = RobotMap.claw_hugger;
	public static Solenoid claw = RobotMap.claw_claw;
	public Claw() {
		//this.close();
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new claw_DoNothing());
		
	}
	public void give60Hugs() {
		hugger.set(false);
	}
	public void give30Hugs() {
		hugger.set(true);
	}
	
	public void open() {
		claw.set(true);
	}

	public void close() {
		claw.set(false);
	}
	
	public void periodic() {
//		SmartDashboard.putString("Claw Command", Robot.claw.getCurrentCommandName());
	}
}
