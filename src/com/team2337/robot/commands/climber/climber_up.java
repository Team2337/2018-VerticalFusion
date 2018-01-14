package com.team2337.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Climber: UP - Moves the climber up
 * @category CLIMBER
 * @author Brendan
 */
public class climber_up extends Command {
	private double power = 0.5;
	public climber_up(double power) {
		this.power = power;

		requires(Robot.climber);
	}
	protected void initialize() {
		Robot.climber.up(this.power);
	}
	protected void execute() {}
	protected boolean isFinished() { return true;}
	protected void end() {
		Robot.climber.stop();
	}
	protected void interrupted() {this.end();}
}
