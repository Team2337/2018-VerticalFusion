package com.team2337.robot.commands.led;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class blinkinControllerRedStrobe extends Command {
	
	public blinkinControllerRedStrobe() {
		requires(Robot.led);
	}

    protected void initialize() {
    	setTimeout(.5);
    }
    
    protected void execute() {
    	
    	//RobotMap.blinkin.set(Robot.oi.getDriverJoystick().getRawAxis(5));
    	RobotMap.blinkin.set(-.17);
    }
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
    protected void end() {
    	
    }
    
    protected void interrupted() {
    	
    }

}
