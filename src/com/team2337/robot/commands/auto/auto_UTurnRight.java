package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class auto_UTurnRight extends Command {

	double speedLeft, speedRight, turn, timeout;
	
	public auto_UTurnRight(double speedLeft, double speedRight, double timeout) {
		requires(Robot.chassis);
		this.speedLeft = speedLeft;
		this.speedRight = speedRight;
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
    	RobotMap.drive.arcadeDrive(0,0,true);
    }
    
    protected void interrupted() {
    	this.end();
    }
    

}
