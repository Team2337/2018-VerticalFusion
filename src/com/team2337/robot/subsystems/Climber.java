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
public class Climber extends Subsystem {
	
	private final Solenoid PTO = RobotMap.PTO;

	public Climber() {
	}

	public void initDefaultCommand() {
		setDefaultCommand(new climber_doNothing());
	}

	
	public void PTOClimb() {
		PTO.set(true);
	}
	
	public void PTOLift() {
		PTO.set(false);
	}
}
