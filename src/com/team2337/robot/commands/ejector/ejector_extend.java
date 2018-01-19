package com.team2337.robot.commands.ejector;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.subsystems.Arm;
/**
 * Ejector: EXTEND - Moves the ejector out 
 * @category EJECTOR
 * @author Brendan
 */
public class ejector_extend extends Command {
	public ejector_extend() {
		requires(Robot.ejector);
	}
	protected void initialize() {
		Robot.ejector.extend();
	}
	protected void execute() {}
	protected boolean isFinished() { return true;}
	protected void end() {}
	protected void interrupted() {this.end();}
}
