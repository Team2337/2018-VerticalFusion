/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Intake: IN - Move the intake in
 * @category INTAKE
 * @author Brendan
 */
public class intake_in extends Command {
	private double power;
	
	
	private int time = 0;
	public intake_in(double power) {
		requires(Robot.intake);
		this.power = power;
	}
	protected void initialize() {
		
	}
	protected void execute() {
		if(Robot.intake.hasCrate()) {
			time++;
		} else {
			time = 0;
		}
		if (time > 10) {
			Robot.intake.stop();
			Robot.claw.give60Hugs();
		} else {
			Robot.intake.moveIn(this.power);
			Robot.claw.give30Hugs();
		}
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
