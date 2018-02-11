package com.team2337.robot.subsystems;

import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.led.blinkinControllerYellow;
import com.team2337.robot.commands.led.led_doNothing;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Where all the LED are controlled
 * 
 * @category LED
 * @author Brendan
 */
public class LED extends Subsystem {
	private final Solenoid infoLED = RobotMap.led_info;
	public void initDefaultCommand() {
		
		setDefaultCommand(new led_doNothing());
	}
	/**
	 * Turn on the LED
	 * @param state Boolean of the STATE of the LED
	 */
	public void LEDState(boolean state) {
		infoLED.set(state);
	}
}
