package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.lifter.lifter_main;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The system to move cubes up and down
 * 
 * @category LIFTER
 * @author Brendan
 */
public class Lifter extends Subsystem {
	
	private final TalonSRX left = RobotMap.lift_leftTop; 
	private final TalonSRX right = RobotMap.lift_rightTop;
	
	public Lifter() {}
	protected void initDefaultCommand() {	
		setDefaultCommand(new lifter_main());
	}
	/**
	 * Vertical Movement of the lift
	 * @param power Power
	 */
	public void verticleMovement(double power)
	{
		left.set(ControlMode.PercentOutput, power);
		right.set(ControlMode.PercentOutput, power);	
	}
}
