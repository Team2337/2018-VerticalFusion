package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class auto_driveToAngleWithTime extends Command {

	double speed, turn, Pgain, Dgain, MaxCorrectionRatio, targetAngle;
	
	public auto_driveToAngleWithTime() {
		Pgain = 0.04; /* percent throttle per degree of error */
		Dgain = 0.0004; /* percent throttle per angular velocity dps */
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		targetAngle = 0;
	    speed = 0.5;

		
	}
	
    protected void initialize() {
    	setTimeout(2);
    }
    
    protected void execute() {
    	
    	double currentAngle = Pigeon.pidgey.getFusedHeading();
    	double currentAngularRate = Robot.gyro.getAngularRate();
    	double forward = speed; 	
    	
    	turn = (targetAngle - currentAngle) * Pgain - (currentAngularRate) * Dgain;
    	
    	RobotMap.drive.arcadeDrive(forward, turn, false);
    	
    }
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
    protected void end() {
    	
    }
    
    protected void interrupted() {
    	
    }
    
    /** @return 10% deadband */
	double Db(double axisVal) {
		if (axisVal < -0.10)
			return axisVal;
		if (axisVal > +0.10)
			return axisVal;
		return 0;
	}
    
	/** @param value to cap.
	 * @param peak positive double representing the maximum (peak) value.
	 * @return a capped value.
	 */
	double Cap(double value, double peak) {
		if (value < -peak)
			return -peak;
		if (value > +peak)
			return +peak;
		return value;
	}
	/**
	 * Given the robot forward throttle and ratio, return the max
	 * corrective turning throttle to adjust for heading.  This is
	 * a simple method of avoiding using different gains for
	 * low speed, high speed, and no-speed (zero turns).
	 */
	double MaxCorrection(double forwardThrot, double scalor) {
		/* make it positive */
		if(forwardThrot < 0) {forwardThrot = -forwardThrot;}
		/* max correction is the current forward throttle scaled down */
		forwardThrot *= scalor;
		/* ensure caller is allowed at least 10% throttle,
		 * regardless of forward throttle */
		if(forwardThrot < 0.10)
			return 0.10;
		return forwardThrot;
	}

}
