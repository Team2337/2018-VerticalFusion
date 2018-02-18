package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class auto_driveForwardWithTime extends Command {

	double speed, turn, Pgain, Dgain, MaxCorrectionRatio, targetAngle, timeout;
	
	public auto_driveForwardWithTime(double speed, double timeout) {
		requires(Robot.chassis);

		this.speed = speed;
	    this.timeout = timeout;
		
	}
	
    protected void initialize() {
    	setTimeout(timeout);
    }
    
    protected void execute() {
    	
   	
    	RobotMap.drive.arcadeDrive(speed, turn, false);
    	
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
