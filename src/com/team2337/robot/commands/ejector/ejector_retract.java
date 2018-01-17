package com.team2337.robot.commands.ejector;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Ejector: RETRACT - Moves the ejector in 
 * @category EJECTOR
 * @author Brendan
 */
public class ejector_retract extends Command {
	public ejector_retract() {
		requires(Robot.ejector);
	}
	protected void initialize() {
		Robot.ejector.retract();
	}
	protected void execute() {}
	protected boolean isFinished() { return true;}
	protected void end() {}
	protected void interrupted() {this.end();}
}
