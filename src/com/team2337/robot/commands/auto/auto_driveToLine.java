package com.team2337.robot.commands.auto;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2337.fusion.gyro.Pigeon;
import com.team2337.fusion.wrappers.auto.AutoCommand;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives forward until it crosses a line
 * Finished when the line sensor reads a line
 * 
 * @category AUTO-DRIVE
 * @author Bryce G., Sean L.
 */
public class auto_driveToLine extends AutoCommand {
	double speed, timeout, Pgain, turn;
	
	/**
	 * This command drives the chassis motors, using a PID, towards a given setpoint
	 * @param speed
	 * Percent of power output on the motor
	 * @param timeout
	 * The amount of time the command is allowed to run until it is forced to terminate
	 */
	public auto_driveToLine(double speed, double timeout) {
		this.timeout = timeout;
		this.speed = speed;
		Pgain = .000001;
	}

	protected void init() {
		setTimeout(timeout);
		 Robot.chassis.setBrakeMode(NeutralMode.Coast);
	}


	protected void execute() {
    	double forward = speed; 	
		RobotMap.drive.tankDrive(speed, speed);
	}


	protected boolean isFinished() {
		return isTimedOut() || RobotMap.lineReader.get();
	}

	protected void stop() {
		
		 Robot.chassis.setBrakeMode(NeutralMode.Brake);
		 RobotMap.chassis_leftFront.setSelectedSensorPosition(0,0,0);
		 RobotMap.chassis_rightFront.setSelectedSensorPosition(0,0,0);
	}


	protected void interrupted() {
		this.end();
	}
	
}
