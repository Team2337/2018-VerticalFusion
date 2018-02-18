package com.team2337.robot.commands.led;

import com.team2337.fusion.led.Color;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class led_decrease extends Command {

	public led_decrease() {
		requires(Robot.led);
	}

	protected void initialize() {
		Robot.led.decrease();
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
