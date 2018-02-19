package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class auto_UTurnLeftRobin extends Command {

	double speed, turn, radius, timeout, currentAngle;
	double targetAngle = -180;
	double kPt = 0.1;
	double track = 21.5; //inches

	public auto_UTurnLeftRobin(double speed, double timeout, double radius) {
		requires(Robot.chassis);
		this.speed = -speed;
		this.timeout = timeout;
		this.radius = radius;
	}

	protected void initialize() {
		setTimeout(timeout);
		currentAngle = Robot.gyro.getYaw();
		turn = (targetAngle - currentAngle) * kPt;
	}

	protected void execute() {
		RobotMap.drive.arcadeDrive(speed, turn, false);

		SmartDashboard.putNumber("right output CHASSIS", RobotMap.chassis_rightFront.getMotorOutputPercent());
		SmartDashboard.putNumber("left output CHASSIS", RobotMap.chassis_leftFront.getMotorOutputPercent());
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();// || Robot.gyro.getYaw() < -175;
	}

	protected void end() {
		RobotMap.drive.arcadeDrive(0, 0, true);
		
	}

	protected void interrupted() {
		this.end();
	}

}
