package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class auto_driveToAngleWithTime extends Command {

	double speed, turn, Pgain, Dgain, MaxCorrectionRatio, targetAngle, timeout;
	
	public auto_driveToAngleWithTime(double speed, double timeout, double angle) {
		requires(Robot.chassis);
		Pgain = 0.04; /* percent throttle per degree of error */
		Dgain = 0.0004; /* percent throttle per angular velocity dps */
		MaxCorrectionRatio = 0.20; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
		
	}
	public auto_driveToAngleWithTime(double timeout) {
		this.timeout = timeout;
		speed = .7;
		turn  = .7;
	}
	
    protected void initialize() {
    	setTimeout(timeout);
    	//Robot.gyro.resetPidgey();
    	RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, 0);
    }
    
    protected void execute() {
    	
    	double currentAngle = Pigeon.pidgey.getFusedHeading();
    	double currentAngularRate = Robot.gyro.getAngularRate();
    	double forward = speed; 	
    	
    	//turn = (targetAngle - currentAngle) * Pgain - (currentAngularRate) * Dgain;
    	
    	RobotMap.drive.arcadeDrive(forward, -turn, false);
    	
    }
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
    protected void end() {
    	RobotMap.drive.arcadeDrive(0,0,true);
    }
    
    protected void interrupted() {
    	this.end();
    }
    

}
