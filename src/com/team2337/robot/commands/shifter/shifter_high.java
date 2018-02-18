package com.team2337.robot.commands.shifter;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Shifter: HIGH - Shifts drive train into high gear
 * @category SHIFTER
 * @author Brendan
 */
public class shifter_high extends Command {
	public shifter_high() {
		requires(Robot.shifter);
	}
	protected void initialize() {
		Robot.shifter.shiftHighGear();
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
