/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.team2337.robot.RobotMap;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Ejector extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    public Solenoid ejector_push = RobotMap.ejector_push;
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
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
