package com.team2337.robot.commands.intake;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class intake_doNothing extends Command {
	
    public intake_doNothing() {
    	requires(Robot.intake);
    }

    protected void initialize() {}
    protected void execute() {}
    protected boolean isFinished() { return true;}
    protected void end() {}
    protected void interrupted() {}
}
