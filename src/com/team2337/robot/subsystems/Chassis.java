package com.team2337.robot.subsystems;

import com.team2337.robot.commands.chassis.chassis_drive;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The main chasiss runtime
 * 
 * @category CHASSIS
 * @author Team2337 - EngiNERDs
 */
public class Chassis extends Subsystem {

	public void initDefaultCommand() {
		setDefaultCommand(new chassis_drive());
	}
}
