package com.team2337.robot.commands.ejector;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class ejector_doNothing extends Command {
	
    public ejector_doNothing() {
    	requires(Robot.ejector);
    }

    protected void initialize() {}
    protected void execute() {}
    protected boolean isFinished() { return true;}
    protected void end() {}
    protected void interrupted() {this.end();}
}
