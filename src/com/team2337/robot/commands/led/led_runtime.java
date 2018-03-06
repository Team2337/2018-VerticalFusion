package com.team2337.robot.commands.led;

import com.team2337.fusion.wrappers.auto.AutoCommandManager;
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
		if (AutoCommandManager.getInstance().state.equals("teleop")) {
			if (Robot.lift.levelOfLift == 1) {
				if (!RobotMap.crateSensor.get()) {
					RobotMap.blinkin.setColor(0.76); // Solid Green
				} else if (Robot.intake.intakeOn) {
					RobotMap.blinkin.setColor(0.05); // HeartBeat Green
				} else {
					RobotMap.blinkin.setColor(0.09); // Breath Green
				}
			} else if (Robot.lift.levelOfLift == 2) {
				if (!RobotMap.crateSensor.get()) {
					RobotMap.blinkin.setColor(0.63); // Solid Yellow
				} else if (Robot.intake.intakeOn) {
					RobotMap.blinkin.setColor(0.25); // HeartBeat Yellow
				} else {
					RobotMap.blinkin.setColor(0.29); // Breath Yellow
				}
			} else if (Robot.lift.levelOfLift == 3) {
				if (!RobotMap.crateSensor.get()) {
					RobotMap.blinkin.setColor(0.62); // Solid Red
				} else if (Robot.intake.intakeOn) {
					RobotMap.blinkin.setColor(-0.25); // HeartBeat Red
				} else {
					RobotMap.blinkin.setColor(-0.17); // Breath Red
				}
			}
			// -0.59 = rainbow party
			// RobotMap.blinkin.flow();
		}
		if((RobotMap.clawPressure.getValue() / 21.37) > 40) {
			RobotMap.clawPressureDash = false;
		} else {
			RobotMap.clawPressureDash = true;
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
