package com.team2337.robot.commands.led;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class blinkinControllerBlue extends Command {
	
	double bottom = .57;
	double top  = .97;
	double value = .57;
	int direction = 1;
	int num;
	
	public blinkinControllerBlue() {
		requires(Robot.led);
		bottom = .57;
		top  = .97;
		value = .57;
		num = 1;
	}

    protected void initialize() {
    	//setTimeout(.5);
    }
    
    protected void execute() {
    	
    	//RobotMap.blinkin.set(Robot.oi.getDriverJoystick().getRawAxis(5));
    	if (direction == 1) {
    		if (num > 5) {
    			RobotMap.blinkin.set(value);
    			value = value + .02;
    			num = 1;
    		}
    		num ++;
    	    if (value > .97 ) {
    	    	direction = 2;
    	    }
    	} else {
    		if (num > 5) {
    			RobotMap.blinkin.set(value);
    			value = value - .02;
    			num = 1;
    		}
    		num ++;
    	    if (value < .57 ) {
    	    	direction = 1;
    	    }
    	}
    	//System.out.println(value);
    	
    }
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
    protected void end() {
    	
    }
    
    protected void interrupted() {
    	
    }

}
