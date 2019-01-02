package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.climber.climber_doNothing;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Releases the piston holding the hook in 
 * 
 * @category CLIMBER
 * @author Brendan F.
 */
public class ClimbPiston extends Subsystem {
	
	//Local instance of the hook ejector
	private final Solenoid climbEjector = RobotMap.climb_ejector;

	public ClimbPiston() {

	}

	public void initDefaultCommand() {
		setDefaultCommand(new climber_doNothing());
	}

	/**
	 * Ejects the hook from its holding positions
	 */
	public void hookerEject() {
		climbEjector.set(true);
	}

	/**
	 * Retracts the piston after the hook has been picked up by the arm 
	 */
	public void hookerRetract() {
		climbEjector.set(false);
	}
}
