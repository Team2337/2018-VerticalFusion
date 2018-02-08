package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.RobotMap;

import com.team2337.robot.commands.DoNothing;
import com.team2337.robot.commands.intake.intake_doNothing;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Allows for cubes to be intaked or blooped (released)
 * 
 * @category INTAKE
 * @author Brendan
 */
public class Intake extends Subsystem {

	public static TalonSRX right = RobotMap.intake_right; //Right motor of intake
	public static TalonSRX left = RobotMap.intake_left; //Left motor of intake
	
	public void initDefaultCommand() {
		setDefaultCommand(new intake_doNothing());
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
		right.set(ControlMode.PercentOutput, 0);
		left.set(ControlMode.PercentOutput, 0);
	}

}
