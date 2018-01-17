package com.team2337.robot.commands.chassis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.RobotMap.*;
import com.team2337.fusion.drive.*;
import com.team2337.robot.subsystems.Pixy.*;

/**
 * Chassis: DRIVE - The drive for the chassis thats runs arcadeD
 * @category CHASSIS
 * @author Team2337 - EngiNERDs
 */
public class chassis_drivePixy extends Command {
	private Joystick driverJoystick = Robot.oi.driverJoystick;
	public chassis_drivePixy() {
		requires(Robot.chassis);
	}

	protected void initialize() {}

	protected void execute() {
		
		double moveSpeed = -driverJoystick.getRawAxis(1); //Left Y
		double turnSpeed = driverJoystick.getRawAxis(4); //Right X
		int xx;
		if (OI.driverJoystick.getRawButton(1)) {  // set moveSpeed and turnSpeed based on PIXY
			
			
			try {
				xx = Robot.vision.pixy1.readPackets();
			} catch (PixyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		RobotMap.drive.arcadeDrive(moveSpeed, turnSpeed);
		
	}

	protected boolean isFinished() {return false;}
	protected void end() {}
	protected void interrupted() {
		this.end();
	}
}
