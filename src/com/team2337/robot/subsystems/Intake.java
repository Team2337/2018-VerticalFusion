package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team2337.robot.RobotMap;

import com.team2337.robot.commands.intake.intake_doNothing;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Allows for cubes to be intaked or blooped (released)
 * 
 * @category INTAKE
 * @author Brendan
 */
public class Intake extends Subsystem {

	//public static TalonSRX right = RobotMap.intake_right; //Right motor of intake 
	public static VictorSPX right = RobotMap.intake_right;
	public static TalonSRX left = RobotMap.intake_left; //Left motor of intake
	
	public void initDefaultCommand() {
		setDefaultCommand(new intake_doNothing());
	}
	public boolean hasCrate() {
		return !RobotMap.crateSensor.get();
	}
	/**
	 * Move the intake inwards (intake)
	 * @param power Power of motors (-1.0 to 1.0)
	 */
	public void moveIn(double power) {
		right.set(ControlMode.PercentOutput, power);
		left.set(ControlMode.PercentOutput, power);
		
	}
	/**
	 * Move the intake outwards (release/bloop it)
	 * @param power Power of motors (-1.0 to 1.0) 
	 */
	public void moveOut(double power) {
		right.set(ControlMode.PercentOutput, -power);
		left.set(ControlMode.PercentOutput, -power);
	}
	/**
	 * Stop the intake
	 */
	public void stop() {

		right.neutralOutput();
		left.neutralOutput();
	}
	public void periodic() {
		if (RobotMap.intakeDebug) {
			SmartDashboard.putBoolean("crate", RobotMap.crateSensor.get());
		}
	}

}
