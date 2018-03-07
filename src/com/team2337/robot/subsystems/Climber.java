package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The climber of the robot. It uses to motors
 * 
 * @category CLIMBER
 * @author Brendan
 */
public class Climber extends Subsystem {

	private final TalonSRX left = RobotMap.climber_left;
	private final TalonSRX right = RobotMap.climber_right;

	public Climber() {
	}

	public void initDefaultCommand() {
	}

	/**
	 * Moves the climber up
	 * 
	 * @param power
	 *            Power of the climber
	 */
	public void up(double power) {
		left.set(ControlMode.PercentOutput, power);
		right.set(ControlMode.PercentOutput, power);
	}

	/**
	 * Moves the climber down
	 * 
	 * @param power
	 *            Power of the climber
	 */
	public void down(double power) {
		left.set(ControlMode.PercentOutput, -power);
		right.set(ControlMode.PercentOutput, -power);
	}

	/**
	 * Stops the climber
	 */
	public void stop() {
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}
}
