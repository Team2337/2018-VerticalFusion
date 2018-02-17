package com.team2337.fusion.wrappers.command.auto;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Wait extends AutoCommand {
	private double time = 10;
    public Wait(double time) {
    	requires(Robot.climber);
    	this.time = time;
    }
    protected void init() {
    	setTimeout(this.time);
    }
    protected void execute() {
    	
    }
    protected boolean isFinished() { 
    	return isTimedOut();
    }
    protected void stop() {
 
    }
    protected void interrupted() {this.end();}
}
