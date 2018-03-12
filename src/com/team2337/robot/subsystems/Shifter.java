package com.team2337.robot.subsystems;

import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.shifter.shifter_doNothing;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Shifts the chassis to a different gear ration
 * 
 * @category SHIFTER
 * @author Brendan
 */
public class Shifter extends Subsystem {
	
	private final Solenoid left = RobotMap.shifter_left;
	
	public void initDefaultCommand() {
		setDefaultCommand(new shifter_doNothing());
	}
	/**
	 * Shift the robot into high gear
	 */
	public void shiftHighGear() {
		left.set(false);
	}
	/**
	 * Shift the robot into low gear
	 */
	public void shiftLowGear() {
		left.set(true);
	}
}
