package com.team2337.robot.subsystems;


import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.chassis.chassis_drive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public void periodic() {
		if (RobotMap.chassisDebug) {
		SmartDashboard.putNumber("leftFront", RobotMap.chassis_leftFront.getMotorOutputPercent());
		}
	}
	
}
