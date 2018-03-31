package com.team2337.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Intake: IN - Move the intake in
 * 
 * @category INTAKE
 * @author Brendan
 */
public class intake_opposite extends Command {
	private double power = .75;
	
	
	private int time = 0;
	public intake_opposite(double power) {
		requires(Robot.intake);
		this.power = power;
	}

	protected void initialize() {
		
	}
	protected void execute() {
		Robot.intake.rotateRight(this.power);
		
	}
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.intake.stop();
	}

	protected void interrupted() {
		this.end();
	}
}
