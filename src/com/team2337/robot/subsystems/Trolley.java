package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.trolley.TEST_ONLY_trolley_joystickControl;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Changes the position of the trolley using PID setpoints
 * 
 * @category TROLLEY
 * @author Bryce G.
 */
public class Trolley extends Subsystem {

	private final static TalonSRX rightFront = RobotMap.trolley_right;

	//private int absolutePosition;  //used to set relative position encoder
	public double maxSpeedUp = 1;  //1.0
	private double maxSpeedDown = 0.5; //.7
	private double nominalSpeed = 0;
	private double kF = 0;
	private double kP = 7;
	private double kI = 0.001;
	private double kD = 0;
	private int allowableError = 0;
	
	
	//comp  
	
	public double trolleyPassover = 460;  //practice 540, comp 510
	
	public static int trolleyStart = 60;
	
	public static int trolleyLowHold;
	public static int trolleyPlus215;
	public static int trolleyPlus315;
	public static int trolleyTop, trolleyHookPos;

	public static int forwardTrolleySoftLimit = 585;
	public static int reverseTrolleySoftLimit = 50;
	

	
	
	protected void initDefaultCommand() {
		//Defualt command is BigBrother, which is already running this subsystem through other means
	}

	public Trolley() {
		//Changes the trolley set points based on the robot being used (comp or practice)
		if(Robot.isComp) {
			trolleyLowHold = trolleyStart + 90;
			trolleyPlus215 = trolleyStart + 215;
			trolleyPlus315 = trolleyStart + 330;
			trolleyTop	   = trolleyStart + 485; 
			trolleyHookPos = trolleyStart + 300;
		} else {
			trolleyLowHold = trolleyStart + 90;
			trolleyPlus215 = trolleyStart + 215;
			trolleyPlus315 = trolleyStart + 330;  
			trolleyTop	   = trolleyStart + 485;
		}
		
		setSoftLimits(forwardTrolleySoftLimit, reverseTrolleySoftLimit);
		
		/* set the peak and nominal outputs, 12V? means full */
		rightFront.configNominalOutputForward(nominalSpeed, 0);
		rightFront.configNominalOutputReverse(-nominalSpeed, 0);
		rightFront.configPeakOutputForward(maxSpeedUp, 0);
		rightFront.configPeakOutputReverse(-maxSpeedDown, 0);
		
		//leftFront.configNominalOutputForward(nominalSpeed, 0);
		//leftFront.configNominalOutputReverse(nominalSpeed, 0);
		//leftFront.configPeakOutputForward(maxSpeed, 0);
		//leftFront.configPeakOutputReverse(-maxSpeed, 0);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		rightFront.configAllowableClosedloopError(0, allowableError, 0);  ///need to set *****************//TODO
		
		/* set closed loop gains in slot0, typically kF stays zero. */
		rightFront.config_kF(0, kF, 0);
		rightFront.config_kP(0, kP, 0);
		rightFront.config_kI(0, kI, 0);
		rightFront.config_kD(0, kD, 0);

	}
	
	/**
	 * Returns if trolley is at top, so that arm can go over
	 */
	public boolean isAtTop() {
		return (getPosition() > 0.95 * forwardTrolleySoftLimit);
	}
	
	/**
	 * Returns if trolley is above midpoint
	 */
	public boolean isAboveMid() {
		return (getPosition() > (0.5 * (forwardTrolleySoftLimit - reverseTrolleySoftLimit) + reverseTrolleySoftLimit));
	}
	
	
	/**
	 * Sets the position of the trolley
	 */
	public void setSetpoint(double pos) {
		rightFront.set(ControlMode.Position, pos);
	}
	
	public void holdPosition() {
		rightFront.set(ControlMode.Position, getPosition());
	}
	
	/**
	 * Gets the set point of the trolley
	 */
	public double getSetpoint() {
		return rightFront.getClosedLoopTarget(0);
	}
	
	/**
	 * Gets the set point error of the trolley
	 */
	public double getError() {
		return rightFront.getClosedLoopError(0);
	}
	
	/**
	 * Gets the position of the trolley
	 */
	public double getPosition() {
		return rightFront.getSelectedSensorPosition(0);
	}

	/**
	 * Runs the trolley by power input
	 * @param power Percentage of power given to the motors to move the trolley (Value of 0-1)
	 */
	public void move(double power) {
		rightFront.set(ControlMode.PercentOutput, power);
	}

	public void stop() {
		rightFront.set(ControlMode.PercentOutput, 0);
	}
	
	/**
	 * Set the soft limits of the trolley
	 * Soft limits are position limits that are envoked through the talons, and are not physical stops
	 * @param forward encoder value in which the talon keeps the system from going past (-4096 - 4096)
	 * @param reverse encoder value in which the talon keeps the system from going past (-4096 - 4096)
	 */
	public static void setSoftLimits(int forward, int reverse) {
		forwardTrolleySoftLimit = forward;
		reverseTrolleySoftLimit = reverse;
		
		rightFront.configForwardSoftLimitThreshold(forwardTrolleySoftLimit, 0);
		//leftFront.configForwardSoftLimitThreshold(forwardTrolleySoftLimit, 0);
			
		rightFront.configReverseSoftLimitThreshold(reverseTrolleySoftLimit, 0);
		//leftFront.configReverseSoftLimitThreshold(reverseTrolleySoftLimit, 0);
	}
	
	/**
	 * Runs the code while this subsystem is in use
	 * Debug, turn on/off in RobotMap
	 */
	public void periodic() {
		if(RobotMap.alt_ControlDebug) {
		SmartDashboard.putNumber("forwardTROLLEYSoftLimit", forwardTrolleySoftLimit);
		SmartDashboard.putNumber("reverseTROLLEYSoftLimit", reverseTrolleySoftLimit);
		}
		
	}
	
}
