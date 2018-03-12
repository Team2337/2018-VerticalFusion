package com.team2337.robot.commands.PTO;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Shifter: HIGH - Shifts drive train into low gear
 * 
 * @category CLIMBER
 * @author Bryce
 */
public class PTO_Lift extends Command {
	public PTO_Lift() {
		requires(Robot.pto);
	}

	protected void initialize() {
		Robot.pto.PTOLift();
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
