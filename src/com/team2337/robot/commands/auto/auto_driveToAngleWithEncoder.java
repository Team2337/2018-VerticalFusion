package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class auto_driveToAngleWithEncoder extends Command {

	double speed, turn, Pgain, Dgain, MaxCorrectionRatio, targetAngle, timeout;
	int encoderLeft, encoderRight;
	
	public auto_driveToAngleWithEncoder(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain) {
		requires(Robot.chassis);
		//Pgain = 0.04; /* percent throttle per degree of error */
		Dgain = 0.0004; /* percent throttle per angular velocity dps */
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
	    this.encoderLeft = encoderTargetLeft;
	    this.encoderRight = encoderTargetRight;
		this.Pgain = Pgain;
	}
	
    protected void initialize() {
    	setTimeout(timeout);
    	//Robot.gyro.resetPidgey();
//    	RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, 0);
//    	RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, 0);
    }
    
    protected void execute() {
    	
    	double currentAngle = Pigeon.pidgey.getFusedHeading();
    	double currentAngularRate = Robot.gyro.getAngularRate();
    	double forward = speed; 	
    	
    	turn = (targetAngle - currentAngle) * Pgain ;//- (currentAngularRate) * Dgain;
    	//if(turn > .3) turn  = 0.3;
    	//if(turn < -.3) turn = -0.3;
    	RobotMap.drive.arcadeDrive(forward, -turn, false);
    	SmartDashboard.putNumber("right Chassis Output Percent", RobotMap.chassis_rightFront.getMotorOutputPercent());
    	SmartDashboard.putNumber("left Chassis Output Percent", RobotMap.chassis_leftFront.getMotorOutputPercent());
    }
	
	@Override
	protected boolean isFinished() {
		return (isTimedOut() || Math.abs(RobotMap.chassis_rightFront.getSelectedSensorPosition(0)) > Math.abs(encoderRight));
		}
	
    protected void end() {
//    	RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, 0);
//    	RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.drive.arcadeDrive(0,0,true);
    }
    
    protected void interrupted() {
    	this.end();
    }
}
