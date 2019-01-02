package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives forward for a given time, reseting the encoders afterwards
 * @category AUTO
 * @author Bryce G., Sean L.
 */
public class auto_driveForwardWithTimeWithReset extends Command {

	double speed, turn, Pgain, Dgain, MaxCorrectionRatio, targetAngle, timeout;
	
	/**
	 * @param speed Percent Power of the motors (Values of -1 -> 1)
	 * @param timeout The amount of time in milliseconds the command runs for
	 */
	public auto_driveForwardWithTimeWithReset(double speed, double timeout) {
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
