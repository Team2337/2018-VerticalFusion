package com.team2337.robot.commands.led;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class led_doNothing extends Command {
	
    public led_doNothing() {
    	requires(Robot.intake);
    }

    protected void initialize() {}
    protected void execute() {}
    protected boolean isFinished() { return true;}
    protected void end() {}
    protected void interrupted() {this.end();}
}
