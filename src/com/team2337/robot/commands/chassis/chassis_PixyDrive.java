package com.team2337.robot.commands.chassis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Chassis: Drive using the Pixycam to target a power cube and steer.
 * Left Y joystick is use to move forward/backward.
 * 
 * @category CHASSIS
 * @author Team2337 - EngiNERDs
 */
public class chassis_PixyDrive extends Command {

	private Joystick driverJoystick = Robot.oi.driverJoystick;
	private static final double turnP = 0.01;
	private double moveSpeed, turnSpeed;

	public chassis_PixyDrive() {
		requires(Robot.chassis);
	}

	protected void initialize() {
	}

	protected void execute() {
		moveSpeed = -driverJoystick.getRawAxis(1); // Left Y
		if (Robot.pixy.xx > 0) {
			turnSpeed = (Robot.pixy.xx - Robot.pixy.centerXX) * turnP;
		} else {
			turnSpeed = 0;
		}
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