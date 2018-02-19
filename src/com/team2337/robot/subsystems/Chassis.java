package com.team2337.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.Robot;
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
		SmartDashboard.putNumber("drive Joystick", Robot.oi.driverJoystick.getRawAxis(1));
		SmartDashboard.putNumber("right Chassis POWER", RobotMap.chassis_rightFront.getMotorOutputPercent());
		SmartDashboard.putNumber("left Chassis POWER", RobotMap.chassis_leftFront.getMotorOutputPercent());
		}
	}
	
}
