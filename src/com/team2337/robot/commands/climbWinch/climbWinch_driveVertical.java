package com.team2337.robot.commands.climbWinch;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the climbing winch in the forward direction
 * 
 * @category CLIMBER
 * @author Brye G., Brendan F.
 */
public class climbWinch_driveVertical extends Command{

	double power;

	/**
	 * Drives the climber winch in the forward direction
	 * @param power Percent power for the motor to run at (Value of 0-1)
	 */
	public climbWinch_driveVertical(double power) {
		requires(Robot.climbWinch);
		this.power = power;
	}

	protected void initialize() {

	}

	protected void execute() {
		Robot.climbWinch.driveVertical(power);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.climbWinch.stop();
	}

	protected void interrupted() {
		this.end();
	}
}
