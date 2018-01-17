package com.team2337.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class DoNothing extends Command {
	
    public DoNothing() {
    	
    }

    protected void initialize() {}
    protected void execute() {}
    protected boolean isFinished() { return true;}
    protected void end() {}
    protected void interrupted() {this.end();}
}
