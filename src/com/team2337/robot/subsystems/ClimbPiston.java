package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.climber.climber_doNothing;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The climber of the robot. It uses to motors
 * 
 * @category CLIMBER
 * @author Brendan
 */
public class ClimbPiston extends Subsystem {
	
	private final Solenoid climbEjector = RobotMap.climb_ejector;

	public ClimbPiston() {
	}

	public void initDefaultCommand() {
		setDefaultCommand(new climber_doNothing());
	}


	public void hookerEject() {
		climbEjector.set(true);
	}
	public void hookerRetract() {
		climbEjector.set(false);
	}
}
