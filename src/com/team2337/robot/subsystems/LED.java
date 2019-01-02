package com.team2337.robot.subsystems;

import com.team2337.robot.commands.led.led_runtime;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Where all the LEDs are controlled
 * 
 * @category LED
 * @author Brendan F.
 */
public class LED extends Subsystem {
	public void initDefaultCommand() {
		setDefaultCommand(new led_runtime());
	}
}
