package com.team2337.robot.commands.shifter;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Shifter: HIGH - Shifts drive train into low gear
 * @category SHIFTER
 * @author Brendan
 */
public class shifter_low extends Command {
	public shifter_low() {
		requires(Robot.shifter);
	}

	protected void initialize() {
		Robot.shifter.shiftLowGear();
	}

	protected void execute() {}
	protected boolean isFinished() {return false;}
	protected void end() {}
	protected void interrupted() {
		this.end();
	}
}
