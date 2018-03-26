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
	private static final double turnP = 0.025;
	private double moveSpeed, turnSpeed, cubeX, turnAngle;
	private double pixlesPerDegree = 3.86;

	public chassis_PixyDrive() {
		requires(Robot.chassis);
	}

	protected void initialize() {
		cubeX = Robot.pixy.xx;
		turnAngle = (cubeX - Robot.pixy.centerXX) / pixlesPerDegree;
	}

	protected void execute() {
		double currentAngle = Robot.gyro.getYaw();
		
		if((turnAngle - currentAngle) > 75) {
			turnSpeed = -((turnAngle - currentAngle) * turnP);
    	} else {
    		turnSpeed = -((turnAngle - currentAngle) * turnP);
    	}
		
		moveSpeed = -driverJoystick.getRawAxis(1); // Left Y
		
		RobotMap.drive.arcadeDrive(moveSpeed, turnSpeed, true);
		System.out.println(turnAngle + "   " + currentAngle + "   " + turnSpeed);
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