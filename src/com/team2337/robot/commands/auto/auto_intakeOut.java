package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Intake: OUT - Move the intake out
 * @category AUTO-INTAKE
 * @author Brendan F.
 */
public class auto_intakeOut extends Command {
	private double power = 1;
	private double timeout;

	/**
	 * @param power
	 * Percent of power output on the motor
	 * @param timeout
	 * The amount of time the command is allowed to run until it is forced to terminate
	 */
	public auto_intakeOut(double power, double timeout) {
		requires(Robot.intake);
		this.power = power;
		this.timeout = timeout;
	}

	protected void initialize() {
		setTimeout(timeout);
	}
	
	protected void execute() {
		Robot.intake.moveOut(this.power);
	}
	protected boolean isFinished() {
		return isTimedOut();
		}
	
	protected void end() {
		Robot.claw.give30Hugs();
		Robot.intake.stop();
	}
	
	protected void interrupted() {
		this.end();
	}
}
