/**
 * Fusion.Drive.NerdyDrive
 * @author Team2337 - Enginerds
 */



package com.team2337.fusion.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class NerdyDrive {
	private TalonSRX left; 
	private TalonSRX right;
	
	private double turnSensitivtiy = 1;
	private double deadband = 0.2;
	
	/**
	 * NerdyDrive - A custom RobotDrive
	 * @param left Left TalonSRX Motor Controller
	 * @param right Right TaonSRX Motor Controller
	 */
	public NerdyDrive(TalonSRX left,TalonSRX right)  {
		this.left = left;
		this.right = right;
	}
	/**
	 * Arcade Drive - Allows for power and turn simply
	 * @param power - Power for moving forward
	 * @param turn - Power for turning
	 */
	public void arcadeDrive(double power, double turn)
	{
		if (Math.abs(power) < this.deadband) {
			power = 0;
		}
		if (Math.abs(turn) < this.deadband) {
			turn = 0;
		}
		
		double left = power + (turn * this.turnSensitivtiy); 
		double right = power - (turn * this.turnSensitivtiy); 
		if (Math.abs(left) > 1) {
			right = right / Math.abs(left);
			left = left / Math.abs(left);	
		}
		if (Math.abs(right) > 1) {
			left = left / Math.abs(right);
			right = right / Math.abs(right);
		}
	
		this.left.set(ControlMode.PercentOutput, left);
		this.right.set(ControlMode.PercentOutput, right);
		
	}
	/**
	 * Setting the sensitivity of the turn
	 * @param sensitivity - Sensitivity of the turn (lower the number faster it goes)
	 */
	public void setSensitivity(double sensitivity) {
		this.turnSensitivtiy = sensitivity;
	}

}

