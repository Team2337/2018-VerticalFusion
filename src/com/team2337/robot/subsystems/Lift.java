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
 * The system to move lift up and down
 * 
 * @category lift
 * @author Bryce
 */
public class Lift extends Subsystem {

	private final static TalonSRX leftFront = RobotMap.lift_left;  //4
	private final static TalonSRX rightFront = RobotMap.lift_right; //3
	
	public static int levelOfLift = 1;
	
	private int absolutePosition;  //used to set relative position encoder
	private double maxSpeed = 0.2;
	private double nominalSpeed = 0;
	private double kF = 0;
	private double kP = 0.1;
	private double kI = 0;
	private double kD = 0;
	private int allowableError = 50;
	
	int forwardSoftLimit = 0;
	int reverseSoftLimit = 100;

	protected void initDefaultCommand() {
		//setDefaultCommand(new lift_startPID());
	}

	public Lift() {
		setSoftLimits(forwardSoftLimit, reverseSoftLimit);
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
		/*
		 * lets grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match. may need to make negative if sensors phase inverted
		 * may also need to adjust to make it within the range we want to use.....//TODO
		 */
//		absolutePosition = rightFront.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
//		absolutePosition &= 0xFFF;
		// if sensor out of phase:  			absolutePosition *= -1;  //TODO
		/* set the quadrature (relative) sensor to match absolute */
//		rightFront.setSelectedSensorPosition(absolutePosition, 0, 0);
		
	}
	
	/**
	 * Sets the position of the lift
	 */
	public void setPosition(double pos){
		rightFront.set(ControlMode.Position, pos);
	    	//setSetpoint(pos);
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
	 * Set the software limits of the lift
	 */
	public static void setSoftLimits(int forward, int reverse) {
		rightFront.configForwardSoftLimitThreshold(forward, 0);
		leftFront.configForwardSoftLimitThreshold(forward, 0);
			
		rightFront.configReverseSoftLimitThreshold(reverse, 0);
		leftFront.configReverseSoftLimitThreshold(reverse, 0);
			
		if(RobotMap.alt_ControlDebug) {
		SmartDashboard.putNumber("forwardLIFTSoftLimit", forward);
		SmartDashboard.putNumber("reverseLIFTSoftLimit", reverse);
		}
			
	}
	public void liftLeveler(int liftLevel) {
		levelOfLift = liftLevel;
	}
}
