package com.team2337.robot.commands.led;

import com.team2337.fusion.led.Color;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class led_runtime extends Command {

	public led_runtime() {
		requires(Robot.led);
	}

	protected void initialize() {
		
	}

	protected void execute() {
		
		//TODO: If not auton, do this, else go do command colors
		double RED, YELLOW, GREEN = 1;
		if (Robot.intake.hasCrate()) {
			 RED = Color.RED;
			 YELLOW = Color.YELLOW;
			 GREEN = Color.AQUA;
			
		} else {
			 RED = Color.STROBE_RED;
			 YELLOW = Color.STROBE_YELLOW;
			 GREEN = Color.STROBE_GREEN;
		}

		if (Robot.lifter.levelOfLift == 1) {
			RobotMap.blinkin.setColor(GREEN);
		} else if (Robot.lifter.levelOfLift == 2) {
			RobotMap.blinkin.setColor(YELLOW);
		} else if (Robot.lifter.levelOfLift == 3) {
			RobotMap.blinkin.setColor(RED);
		}	
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
