package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class auto_UTurnLeft extends Command {

	double speedLeft, speedRight, turn, timeout;
	double track = 21.5; //inches

	public auto_UTurnLeft(double speed, double timeout, double radius) {
		requires(Robot.chassis);
		this.speedLeft = speed;
		this.speedRight = -(speed / (radius / (radius + track)));
		this.timeout = timeout;
	}

	protected void initialize() {
		setTimeout(timeout);
	}

	protected void execute() {
		RobotMap.drive.tankDrive(speedRight, -0.1);

		SmartDashboard.putNumber("right output CHASSIS", RobotMap.chassis_rightFront.getMotorOutputPercent());
		SmartDashboard.putNumber("left output CHASSIS", RobotMap.chassis_leftFront.getMotorOutputPercent());
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut() || Robot.gyro.getYaw() < -175;
	}

	protected void end() {
		RobotMap.drive.arcadeDrive(0, 0, true);
		
	}

	protected void interrupted() {
		this.end();
	}

}
