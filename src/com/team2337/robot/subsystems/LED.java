package com.team2337.robot.subsystems;

import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.intake.intake_doNothing;
import com.team2337.robot.commands.led.led_runtime;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Where all the LED are controlled
 * 
 * @category LED
 * @author Brendan
 */
public class LED extends Subsystem {
	public void initDefaultCommand() {
		//setDefaultCommand(new led_runtime());
	}
}
