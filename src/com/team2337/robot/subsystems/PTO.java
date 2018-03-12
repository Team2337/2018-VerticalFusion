package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.PTO.PTO_DoNothing;
import com.team2337.robot.commands.climber.climber_doNothing;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The climber of the robot. It uses to motors
 * 
 * @category CLIMBER
 * @author Brendan
 */
public class PTO extends Subsystem {
	
	private final Solenoid PTO = RobotMap.PTO;
	private final Solenoid PTO0 = RobotMap.PTO0;
	public PTO() {
	}

	public void initDefaultCommand() {
		setDefaultCommand(new PTO_DoNothing());
	}

	
	public void PTOClimb() {
		PTO.set(false);
		PTO0.set(true);
	}
	
	public void PTOLift() {
		PTO.set(true);
		PTO0.set(false);
	}
}
