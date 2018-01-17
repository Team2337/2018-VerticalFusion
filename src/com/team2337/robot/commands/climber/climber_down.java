/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Climber: DOWN - Moves the climber down
 * @category CLIMBER
 * @author Brendan
 */
public class climber_down extends Command {
	private double power = 0.5;
	public climber_down(double power) {
		this.power = power;
		
		requires(Robot.climber);
	}
	protected void initialize() {
		Robot.climber.down(this.power);
	}
	protected void execute() {}
	protected boolean isFinished() { return true;}
	protected void end() {
		Robot.climber.stop();
	}
	protected void interrupted() {this.end();}
}
