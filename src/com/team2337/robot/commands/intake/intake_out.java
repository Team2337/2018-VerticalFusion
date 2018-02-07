package com.team2337.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Intake: OUT - Move the intake out
 * @category INTAKE
 * @author Brendan
 */
public class intake_out extends Command {
	private double power = 1;
	public intake_out(double power) {
		requires(Robot.intake);
		this.power = power;
	}

	protected void initialize() {
		Robot.intake.moveOut(this.power);
	}
	
	protected void execute() {}
	protected boolean isFinished() {return false;}
	
	protected void end() {
		Robot.claw.give30Hugs();
		Robot.intake.stop();
	}
	
	protected void interrupted() {
		this.end();
	}
}
