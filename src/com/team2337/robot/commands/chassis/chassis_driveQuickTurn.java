package com.team2337.robot.commands.chassis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.RobotMap.*;
import com.team2337.fusion.drive.*;
/**
 * Chassis: DRIVE - The drive for the chassis thats runs arcadeD
 * @category CHASSIS
 * @author Team2337 - EngiNERDs
 */
public class chassis_driveQuickTurn extends Command {
	private Joystick driverJoystick = Robot.oi.driverJoystick;
	public chassis_driveQuickTurn() {
		requires(Robot.chassis);
	}

	protected void initialize() {}

	protected void execute() {
		double moveSpeed = driverJoystick.getRawAxis(1); //Left Y
		double turnSpeed = driverJoystick.getRawAxis(4); //Right X
		if (Math.abs(moveSpeed) > 0.2 || Math.abs(turnSpeed) > 0.3) {
			if (Math.abs(turnSpeed) < 0.3) { 
				turnSpeed = 0;
			}
			//RobotMap.drive.curvatureDrive(moveSpeed, turnSpeed, true);	
		} else {
			//RobotMap.drive.arcadeDrive(0, 0, true);
		}
		
		
	}

	protected boolean isFinished() {return false;}
	protected void end() {}
	protected void interrupted() {
		this.end();
	}
}
