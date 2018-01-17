package com.team2337.robot.commands.extender;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Extender: EXTEND - Moves the extender out
 * @category EXTENDER
 * @author Bryce
 */
public class extender_extend extends Command {
	public extender_extend() {
		requires(Robot.extender);
	}

    protected void initialize() {
    	Robot.extender.extend();
    }
    protected void execute() {}
    protected boolean isFinished() { return true;}
    protected void end() {}
    protected void interrupted() {this.end();}
}
