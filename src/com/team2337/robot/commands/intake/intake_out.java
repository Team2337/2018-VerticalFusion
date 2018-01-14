/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
		Robot.intake.stop();
	}
	
	protected void interrupted() {
		this.end();
	}
}
