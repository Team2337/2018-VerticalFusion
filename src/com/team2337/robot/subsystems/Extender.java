package com.team2337.robot.subsystems;

import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.extender.extender_doNothing;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Extends the intake out and in over the bumpers
 * 
 * @category EXTENDER
 * @author Brendan, Bryce
 */
public class Extender extends Subsystem {
	
	private final Solenoid left = RobotMap.extender_left;
	private final Solenoid right = RobotMap.extender_right;
	
	public void initDefaultCommand() {
		//setDefaultCommand(new extender_doNothing());
	}
	/**
	 * Extends the intake out over the bumpers
	 */
	public void extend() {
		left.set(true);
		right.set(true);
	}
	/**
	 * Retracts the intake in over the bumpers
	 */
	public void retract() {
		left.set(false);
		right.set(false);
	}
}
