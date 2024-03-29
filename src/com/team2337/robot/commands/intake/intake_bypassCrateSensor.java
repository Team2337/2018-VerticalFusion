package com.team2337.robot.commands.intake;

import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the intake without the use of the crate sensor
 * 
 * @category INTAKE
 * @author Team2337 - EngiNERDs
 */
public class intake_bypassCrateSensor extends Command {

	public intake_bypassCrateSensor() {
		
	}

	protected void initialize() {
		RobotMap.crateBypass = true;
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
