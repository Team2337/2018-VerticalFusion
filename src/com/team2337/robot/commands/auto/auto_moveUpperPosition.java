package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class auto_moveUpperPosition extends Command {
	double arm,trolley;
	
	/**
	 * Sets the setpoints of the arm and trolley at the same time
	 * @param arm
	 * Arm setpoint
	 * @param trolley
	 * Trolley setpoint
	 */
	public auto_moveUpperPosition(double arm, double trolley) {
		requires(Robot.arm);
		this.arm = arm;
		this.trolley = trolley;
	}
	@Override
	protected void initialize() {
		Robot.arm.setSetpoint(arm);
		Robot.trolley.setSetpoint(trolley);
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
