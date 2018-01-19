package com.team2337.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.ejector.ejector_doNothing;

/**
 * The ejection system of a cube
 * 
 * @category EJECTOR
 * @author Brendan
 */
public class Arm extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    public Solenoid ejector_push = RobotMap.ejector_push;
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ejector_doNothing());
	}
	/**
	 * Extends the ejector
	 */
	public void extend() {
		ejector_push.set(true);
	}
	/**
	 * Retracts the ejector
	 */
	public void retract() {
		ejector_push.set(false);
	}
	/**
	 * Grab the state of the ejector
	 * @return State
	 */
	public boolean isExtended() {
		return ejector_push.get();
	}
}
