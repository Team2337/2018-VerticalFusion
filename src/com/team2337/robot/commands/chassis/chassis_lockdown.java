package com.team2337.robot.commands.chassis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.RobotMap.*;
import com.team2337.fusion.drive.*;
/**
 * Chassis: LOCKDOWN - Locks down the chassis so it can't move
 * @category CHASSIS
 * @author Team2337 - EngiNERDs
 */
public class chassis_lockdown extends Command {
	private Joystick driverJoystick = Robot.oi.driverJoystick;
	public chassis_lockdown() {
		requires(Robot.chassis);
	}

	protected void initialize() {
		RobotMap.chassis_leftFront.getSensorCollection().setQuadraturePosition(0, 10);
		RobotMap.chassis_rightRear.getSensorCollection().setQuadraturePosition(0, 10);
		RobotMap.chassis_leftFront.config_kD(0, 0, 0);
		RobotMap.chassis_rightRear.config_kD(0, 0, 0);
		
    	
	}

	protected void execute() {
		
		
	}

	protected boolean isFinished() {return false;}
	protected void end() {}
	protected void interrupted() {
		this.end();
	}
}
