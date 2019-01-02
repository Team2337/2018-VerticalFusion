package com.team2337.robot.commands.chassis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.RobotMap.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.fusion.drive.*;

/**
 * Chassis: DRIVE - The drive for the chassis thats runs arcade drive
 * @category CHASSIS
 * @author Team2337 - EngiNERDs
 */
public class chassis_drive extends Command {
	private Joystick driverJoystick = Robot.oi.driverJoystick;
	public chassis_drive() {
		requires(Robot.chassis);
	}

	protected void initialize() {
		
	}
	protected void execute() {
		double moveSpeed = -driverJoystick.getRawAxis(1); //Left Y
		double turnSpeed = driverJoystick.getRawAxis(4); //Right X
		
		/*RobotMap.chassis_rightFront.set(ControlMode.PercentOutput, moveSpeed);
		RobotMap.chassis_leftFront.set(ControlMode.PercentOutput, moveSpeed);*/
		RobotMap.drive.arcadeDrive(moveSpeed, turnSpeed, true);
		
	}

	protected boolean isFinished() {
		return false;
	}
	protected void end() {
		
	}
	protected void interrupted() {
		this.end();
	}
	
}
