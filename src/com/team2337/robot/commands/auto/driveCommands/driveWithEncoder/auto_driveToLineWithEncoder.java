package com.team2337.robot.commands.auto.driveCommands.driveWithEncoder;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2337.fusion.gyro.Pigeon;
import com.team2337.fusion.wrappers.auto.AutoCommand;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class auto_driveToLineWithEncoder extends AutoCommand {
	double speed, degree, timeout, Pgain, turn;
	
	public auto_driveToLineWithEncoder(double speed,double degree, double timeout) {
		this.timeout = timeout;
		this.speed = speed;
		this.degree = degree;
		Pgain = .000001;
	}

	protected void init() {
		setTimeout(timeout);
		 Robot.chassis.setBrakeMode(NeutralMode.Coast);
	}


	protected void execute() {
			
	//	double currentAngle = Pigeon.pidgey.getFusedHeading();
    	double forward = speed; 	
    	
    	//turn = -((degree - currentAngle) * Pgain );
    	
		//RobotMap.drive.arcadeDrive(forward, 0, false);
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
