package com.team2337.robot.commands.auto;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class auto_driveToAngleWithEncoder extends Command {

	double speed, turn, Pgain, Dgain, MaxCorrectionRatio, targetAngle, timeout, isFinishedPos;
	int encoderLeft, encoderRight,encoderEnd;
	boolean isFinishedSide = false;
	boolean change = false;
	
	public auto_driveToAngleWithEncoder(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain) {
		requires(Robot.chassis);
		Dgain = 0.0004; /* percent throttle per angular velocity dps */
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
	    this.encoderLeft = encoderTargetLeft;
	    this.encoderRight = encoderTargetRight;
		this.Pgain = Pgain;
	}
	
	public auto_driveToAngleWithEncoder(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain, boolean change) {
		requires(Robot.chassis);
		Dgain = 0.0004; /* percent throttle per angular velocity dps */
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
	    this.encoderLeft = encoderTargetLeft;
	    this.encoderRight = encoderTargetRight;
		this.Pgain = Pgain;
		this.change = change;
	}
	public auto_driveToAngleWithEncoder(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain, boolean change, double Dgain) {
		requires(Robot.chassis);
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
	    this.encoderLeft = encoderTargetLeft;
	    this.encoderRight = encoderTargetRight;
		this.Pgain = Pgain;
		this.change = change;
		this.Dgain = Dgain;
	}
	
    protected void initialize() {
    	if (Robot.ourswitch.equals("R") || Robot.ourswitch.equals("r") ) {
    		isFinishedSide = true;
   		}
   		setTimeout(timeout);
   		
   		if(change) {
   			isFinishedSide = !isFinishedSide;
   		}
    }
    
    
    
    protected void execute() {
    	double currentAngle = Robot.gyro.getYaw();
//    	System.out.println(isFinishedPos + "  " + encoderRight + "  " + encoderLeft + "  " + currentAngle);
    	double currentAngularRate = Robot.gyro.getAngularRate();
    	double forward = speed; 	
    	if((targetAngle - currentAngle) > 75) {
    		turn = -((targetAngle - currentAngle) * Pgain) - (currentAngularRate) * Dgain;
    	} else {
    		turn = -((targetAngle - currentAngle) * Pgain);
    	}

    	RobotMap.drive.arcadeDrive(forward, turn, false);
    	if(isFinishedSide) {
    		//Read left side encoder
    		isFinishedPos = Math.abs(RobotMap.chassis_leftFront.getSelectedSensorPosition(0));
    		encoderEnd = Math.abs(encoderLeft);
    	} else {
    		//Read right side encoder
    		isFinishedPos = Math.abs(RobotMap.chassis_rightFront.getSelectedSensorPosition(0));
    		encoderEnd = Math.abs(encoderRight);
    		
    	}
//    	System.out.println(isFinishedPos);

    }
	
	@Override
	protected boolean isFinished() {
		return (isTimedOut() || isFinishedPos > Math.abs(encoderEnd));
		}
	
    protected void end() {
    	RobotMap.drive.arcadeDrive(0,0,true);
//    	System.out.println(Robot.gyro.pidgey.getFusedHeading());
    }
    
    protected void interrupted() {
    	this.end();
    }
}
