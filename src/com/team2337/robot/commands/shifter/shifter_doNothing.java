package com.team2337.robot.commands.shifter;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class shifter_doNothing extends Command {
	
    public shifter_doNothing() {
    	requires(Robot.shifter);
    }

    protected void initialize() {}
    protected void execute() {}
    protected boolean isFinished() { return true;}
    protected void end() {}
    protected void interrupted() {this.end();}
}
