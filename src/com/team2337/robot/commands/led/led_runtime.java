package com.team2337.robot.commands.led;


import com.team2337.fusion.led.Color;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class led_runtime extends Command {
	
    public led_runtime() {
    	requires(Robot.led);
    }

    protected void initialize() {
    	RobotMap.blinkin.setColor(Color.AQUA);
    }
    protected void execute() {
    	//RobotMap.blinkin.flow();
    }
    protected boolean isFinished() { return false;}
    protected void end() {}
    protected void interrupted() {this.end();}
}
