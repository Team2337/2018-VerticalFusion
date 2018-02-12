package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.team2337.robot.RobotMap;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.bigBrother.alt_Control_Import;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The system to move Trolley up and down
 * 
 * @category TROLLEY
 * @author Bryce
 */
public class Trolley extends Subsystem {

	private final static TalonSRX leftFront = RobotMap.trolley_left;  //4
	private final static TalonSRX rightFront = RobotMap.trolley_right; //3
	
	//private int absolutePosition;  //used to set relative position encoder
	private double maxSpeed = 0.2;
	private double nominalSpeed = 0;
	private double kF = 0;
	private double kP = 0.1;
	private double kI = 0;
	private double kD = 0;
	private int allowableError = 50;

	public static int forwardTrolleySoftLimit = -60;
	public static int reverseTrolleySoftLimit = -530;
	
	protected void initDefaultCommand() {
		//setDefaultCommand(new trolley_startPID());
	}

	public Trolley() {
		setSoftLimits(forwardTrolleySoftLimit, reverseTrolleySoftLimit);
		
		/* set the peak and nominal outputs, 12V? means full */
		rightFront.configNominalOutputForward(nominalSpeed, 0);
		rightFront.configNominalOutputReverse(nominalSpeed, 0);
		rightFront.configPeakOutputForward(maxSpeed, 0);
		rightFront.configPeakOutputReverse(-maxSpeed, 0);
		
		leftFront.configNominalOutputForward(nominalSpeed, 0);
		leftFront.configNominalOutputReverse(nominalSpeed, 0);
		leftFront.configPeakOutputForward(maxSpeed, 0);
		leftFront.configPeakOutputReverse(-maxSpeed, 0);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		rightFront.configAllowableClosedloopError(allowableError, 0, 0);  ///need to set *****************//TODO
		
		/* set closed loop gains in slot0, typically kF stays zero. */
		rightFront.config_kF(0, kF, 0);
		rightFront.config_kP(0, kP, 0);
		rightFront.config_kI(0, kI, 0);
		rightFront.config_kD(0, kD, 0);

	}
	
	/**
	 * Sets the position of the trolley
	 */
	public void setSetpoint(double pos){
		rightFront.set(ControlMode.Position, pos);
	}
	
	/**
	 * Gets the set point of the trolley
	 */
	public double getSetpoint(){
		return rightFront.getClosedLoopTarget(0);
	}
	
	/**
	 * Gets the set point error of the trolley
	 */
	public double getError(){
		return rightFront.getClosedLoopError(0);
	}
	
	/**
	 * Gets the position of the trolley
	 */
	public double getPosition(){
		return rightFront.getSelectedSensorPosition(0);
	}
	
	/**
	 * Set the software limits of the trolley
	 */
	public static void setSoftLimits(int forward, int reverse) {
		forwardTrolleySoftLimit = forward;
		reverseTrolleySoftLimit = reverse;
		
		rightFront.configForwardSoftLimitThreshold(forwardTrolleySoftLimit, 0);
		leftFront.configForwardSoftLimitThreshold(forwardTrolleySoftLimit, 0);
			
		rightFront.configReverseSoftLimitThreshold(reverseTrolleySoftLimit, 0);
		leftFront.configReverseSoftLimitThreshold(reverseTrolleySoftLimit, 0);
	}
	
	/**
	 * Debug, turn on/off in RobotMap
	 */
	public void periodic() {
		if(RobotMap.alt_ControlDebug) {
		SmartDashboard.putNumber("forwardTROLLEYSoftLimit", forwardTrolleySoftLimit);
		SmartDashboard.putNumber("reverseTROLLEYSoftLimit", reverseTrolleySoftLimit);
		}
		
	}
	
}
