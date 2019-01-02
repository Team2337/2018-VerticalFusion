package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets the yaw on the gyro to the given degree 
 * Primarly used to set the yaw to zero 
 * 
 * @category AUTO-DRIVE
 * @author Bryce G., Sean L. 
 */
public class auto_setYawYaw extends Command{

	double yaw;
	public auto_setYawYaw(double yaw) {
		this.yaw = yaw;
	}

	protected void initialize() {
		Robot.gyro.manualSetYaw(yaw);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
