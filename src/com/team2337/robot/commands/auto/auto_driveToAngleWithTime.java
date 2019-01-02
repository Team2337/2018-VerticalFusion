package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives the chassis to a desired angle with the gyro, for a given amount of time 
 * 
 * @category AUTO-DRIVE
 * @author Bryce G., Sean L.
 */
public class auto_driveToAngleWithTime extends Command {

	double speed, turn, Pgain, Dgain, MaxCorrectionRatio, targetAngle, timeout;
	double num = 0;

	/**
	 * This command drives the chassis motors, using a PID, towards a given setpoint for a set amount of time 
	 * @param speed
	 * Percent of power output on the motor
	 * @param timeout
	 * The amount of time the command is allowed to run until it is forced to terminate
	 * @param angle
	 * The desired angle the robot should be at, at the end of the command
	 */
	public auto_driveToAngleWithTime(double speed, double timeout, double angle) {
		requires(Robot.chassis);
		Pgain = 0.08; /* percent throttle per degree of error */
		Dgain = 0.0004; /* percent throttle per angular velocity dps */
		MaxCorrectionRatio = 0.20; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
		
	}
	
    protected void initialize() {
    	setTimeout(timeout);
    	//Robot.gyro.resetPidgey();
    	//RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, 0);
    	//RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, 0);
    }
    
    protected void execute() {
    	//System.out.println(Robot.gyro.getYaw() + " exec "  +  num);
    	num++;
    	double currentAngle = Robot.gyro.getYaw();
    	//double currentAngularRate = Robot.gyro.getAngularRate();
    	double forward = speed; 	
    	
    	turn = (-targetAngle + currentAngle) * Pgain; // - (currentAngularRate) * Dgain;
    	
    	RobotMap.drive.arcadeDrive(forward, turn, false);
    	
    }
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
    protected void end() {
    	//System.out.println(Robot.gyro.getYaw());
    	RobotMap.drive.arcadeDrive(0,0,true);
    }
    
    protected void interrupted() {
    	this.end();
    }
    

}
