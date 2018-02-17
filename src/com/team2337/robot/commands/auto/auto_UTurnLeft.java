package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class auto_UTurnLeft extends Command {

	double speedLeft, speedRight, turn, timeout;
	double track = 21.5;

	public auto_UTurnLeft(double speed, double timeout, double radius) {
		requires(Robot.chassis);
		this.speedLeft = speed / (radius / (radius + track));
		this.speedRight = speed;
		this.timeout = timeout;
	}

	protected void initialize() {
		setTimeout(timeout);
	}

	protected void execute() {
		RobotMap.drive.tankDrive(speedLeft, speedRight);

	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		RobotMap.drive.arcadeDrive(0, 0, true);
	}

	protected void interrupted() {
		this.end();
	}

}
