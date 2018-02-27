package com.team2337.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Intake: OUT - Move the intake out
 * 
 * @category INTAKE
 * @author Brendan
 */
public class intake_out extends Command {
	int num = 0;

	private double power = 1;

	public intake_out(double power) {
		requires(Robot.intake);
		this.power = power;
	}

	protected void initialize() {

	}

	protected void execute() {
		Robot.intake.moveOut(this.power);


			if (num == 102) {
				Robot.claw.give60Hugs();
			}
			if (num == 110) {
				Robot.claw.open();
			}
			if (num == 120) {
				Robot.claw.give30Hugs();
			}
			if (num == 130) {
				Robot.claw.close();
			}

		num++;
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.intake.stop();
		num = 0;
	}

	protected void interrupted() {
		this.end();
	}
}
