package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The system to move lift up and down
 * 
 * @category lift
 * @author Bryce
 */
public class Lift extends Subsystem {

	private final static TalonSRX rightFront = RobotMap.lift_right; //3
	//private final static TalonSRX leftFront = RobotMap.lift_left;  //4
	
	public int levelOfLift = 1;

	private double maxSpeed = 0.2;
	private double nominalSpeed = 0;
	private double kF = 0;
	private double kP = 0.1;
	private double kI = 0;
	private double kD = 0;
	private int allowableError = 50;     						 ///need to set *****************//TODO
	
	public static int forwardLIFTSoftLimit = 0;					 ///need to set *****************//TODO
	public static  int reverseLIFTSoftLimit = 100;				 ///need to set *****************//TODO

	protected void initDefaultCommand() {
		//setDefaultCommand(new lift_startPID());
	}

	public Lift() {
		setSoftLimits(forwardLIFTSoftLimit, reverseLIFTSoftLimit);
		/* set the peak and nominal outputs, 12V? means full */
		rightFront.configNominalOutputForward(nominalSpeed, 0);
		rightFront.configNominalOutputReverse(nominalSpeed, 0);
		rightFront.configPeakOutputForward(maxSpeed, 0);
		rightFront.configPeakOutputReverse(-maxSpeed, 0);
		
		//leftFront.configNominalOutputForward(nominalSpeed, 0);
		//leftFront.configNominalOutputReverse(nominalSpeed, 0);
		//leftFront.configPeakOutputForward(maxSpeed, 0);
		//leftFront.configPeakOutputReverse(-maxSpeed, 0);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		rightFront.configAllowableClosedloopError(allowableError, 0, 0); 
		
		/* set closed loop gains in slot0, typically kF stays zero. */
		rightFront.config_kF(0, kF, 0);
		rightFront.config_kP(0, kP, 0);
		rightFront.config_kI(0, kI, 0);
		rightFront.config_kD(0, kD, 0);
		
	}
	
	/**
	 * Sets the position of the lift
	 */
	public void setSetpoint(double pos){
		rightFront.set(ControlMode.Position, pos);
	}
	
	/**
	 * Gets the set point of the lift
	 */
	public double getSetpoint(){
		return rightFront.getClosedLoopError(0);
	}
	
	/**
	 * Gets the position of the lift
	 */
	public double getPosition(){
		return rightFront.getSelectedSensorPosition(0);
	}
	
	/**
	 * Set the motion array of the lift based on the input from the buttons on the side of the throttle stick.
	 */	
	public void liftLeveler(int liftLevel) {
		levelOfLift = liftLevel;
	}
	
	/**
	 * Runs the lift by joystick
	 */

	public void move(double power) {
		rightFront.set(ControlMode.PercentOutput, power);
	}

	public void stop() {
		rightFront.set(ControlMode.PercentOutput, 0);
	}
	
	/**
	 * Set the software limits of the lift
	 */
	public static void setSoftLimits(int forward, int reverse) {
		forwardLIFTSoftLimit = forward;
		reverseLIFTSoftLimit = reverse;
		
		rightFront.configForwardSoftLimitThreshold(forwardLIFTSoftLimit, 0);
		//leftFront.configForwardSoftLimitThreshold(forwardLIFTSoftLimit, 0);
			
		rightFront.configReverseSoftLimitThreshold(reverseLIFTSoftLimit, 0);
		//leftFront.configReverseSoftLimitThreshold(reverseLIFTSoftLimit, 0);			
	}

	/**
	 * Debug, turn on/off in RobotMap
	 */
	public void periodic() {
		if(RobotMap.alt_ControlDebug) {
			SmartDashboard.putNumber("forwardLIFTSoftLimit", forwardLIFTSoftLimit);
			SmartDashboard.putNumber("reverseLIFTSoftLimit", reverseLIFTSoftLimit);
		}
	}
}