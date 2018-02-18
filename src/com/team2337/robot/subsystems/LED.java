package com.team2337.robot.subsystems;

import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.intake.intake_doNothing;
import com.team2337.robot.commands.led.led_runtime;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Where all the LED are controlled
 * 
 * @category LED
 * @author Brendan
 */
public class LED extends Subsystem {
	public double color = 0;
	public void initDefaultCommand() {
		setDefaultCommand(new led_runtime());
	}
	public void increase() {
		color = color + 0.01;
		SmartDashboard.putNumber("color", color);
		RobotMap.blinkin.setColor(color);
	}
	public void decrease() {
		color = color - 0.01;
		SmartDashboard.putNumber("color", color);
		RobotMap.blinkin.setColor(color);
	}
}
