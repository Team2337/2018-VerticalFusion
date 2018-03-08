package com.team2337.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Shifter: HIGH - Shifts drive train into low gear
 * 
 * @category CLIMBER
 * @author Bryce
 */
public class climber_PTOLift extends Command {
	public climber_PTOLift() {
		requires(Robot.climber);
	}

	protected void initialize() {
		Robot.climber.PTOLift();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
