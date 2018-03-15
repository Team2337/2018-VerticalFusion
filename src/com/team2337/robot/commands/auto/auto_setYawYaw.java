package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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
