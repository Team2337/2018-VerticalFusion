package com.team2337.robot.commands.chassis;

import com.team2337.fusion.wrappers.command.auto.AutoCommand;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * MoveForward - Move the robot forward for specified time
 * @category CHASSSIS AUTON
 * @author Jennah
 */
public class chassis_moveForward extends AutoCommand {
	private double time = 10;
    public chassis_moveForward(double time) {
    	requires(Robot.chassis);
    	this.time = time;
    }
    
    protected void init() {
    	setTimeout(this.time);
    	RobotMap.drive.arcadeDrive(1, 0, true);
    }
    protected void execute() {
    	RobotMap.drive.arcadeDrive(1, 0, true);
    }
    protected boolean isFinished() { 
    	return isTimedOut();
    }
    protected void stop() {
    	RobotMap.drive.arcadeDrive(0, 0, true);
    }
}
