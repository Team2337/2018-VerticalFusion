package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.arm.TEST_ONLY_arm_joystickControl;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The subsystem to control the arm via a PID
 * 
 * @category LIFTER
 * @author 2337
 */
public class Arm extends Subsystem {

	private final static TalonSRX armRight = RobotMap.arm_right;
	private final static TalonSRX armLeft = RobotMap.arm_left;

	//private boolean PIDStatus = false;
	
	private double kF = 0;
	private double kP = 0.1;
	private double kI = 0;
	private double kD = 0;
	private int allowableError = 70;                ///need to set *****************//TODO

	private static final double maxSpeedUp 		= 0.3;
	private static final double maxSpeedDown 	= -0.3;
	private double nominalSpeed = 0;
	
	public static final int forwardSoftLimit 	= 5320;
	public static final int forwardTopSL 		= 4538;
	public static final int reverseTopSL 		= 4023;
	public static final int reverseSoftLimit 	= 3400;
	
	//public static final double stringpotTopPos 		= 1.0;
	//public static final double stringpotMidPos 		= 0.7;
	//public static final double stringpotBottomPos 	= 0.1;
	
	private int absolutePosition;  //used to set relative position of the encoder based on absolute encoder

	protected void initDefaultCommand() {
		//setDefaultCommand(new TEST_ONLY_arm_joystickControl());
	}

	public Arm() {

		//getPIDController().setContinuous(false);
		//getPIDController().setInputRange(0, 8000);
		
		setSoftLimits(forwardSoftLimit, reverseSoftLimit);
				
		/* set the peak and nominal outputs, 12V? means full */
		armRight.configNominalOutputForward(nominalSpeed, 0);
		armRight.configNominalOutputReverse(nominalSpeed, 0);
		armRight.configPeakOutputForward(maxSpeedUp, 0);
		armRight.configPeakOutputReverse(maxSpeedDown, 0);
		
		armLeft.configNominalOutputForward(nominalSpeed, 0);
		armLeft.configNominalOutputReverse(nominalSpeed, 0);
		armLeft.configPeakOutputForward(maxSpeedUp, 0);
		armLeft.configPeakOutputReverse(maxSpeedDown, 0);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		armRight.configAllowableClosedloopError(allowableError, 0, 0);  
		
		/* set closed loop gains in slot0, typically kF stays zero. */
		armRight.config_kF(0, kF, 0);
		armRight.config_kP(0, kP, 0);
		armRight.config_kI(0, kI, 0);
		armRight.config_kD(0, kD, 0);
		/*
		 * lets grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match. may need to make negative if sensors phase inverted
		 * may also need to adjust to make it within the range we want to use.....//TODO
		 */
		absolutePosition = armRight.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		// if sensor out of phase:  			absolutePosition *= -1;  //TODO
		// motor inverted:           			absolutePosition *= -1;  //TODO    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!           
		/* set the quadrature (relative) sensor to match absolute */
		armRight.setSelectedSensorPosition(absolutePosition, 0, 0);
	}

	/**
	 * Sets the setpoint of the arm
	 */
	public void setSetpoint(double pos) {
		armRight.set(ControlMode.Position, pos);
	}	
	
	/**
	 * Gets the set point of the arm
	 */
	public double getSetpoint(){
		return armRight.getClosedLoopTarget(0);
	}
	
	/**
	 * Gets the set point error of the arm
	 */
	public double getError(){
		return armRight.getClosedLoopError(0);
	}
	
	/**
	 * Attempts to determine if on target
	 */
	public boolean closeToTarget(){
		return (armRight.getClosedLoopError(0) < allowableError);
	}

	/**
	 * Returns the PID input/position of the Arm PID
	 */
	public double getPosition() {
		return armRight.getSelectedSensorPosition(0);
	}

	//TODO ??
	/*
	 * public boolean LiftAutoTote() { return liftAutoTote.get(); }
	 */

	/**
	 * Set the Ramp Rate for the Arm for the PID.
	 */
	public void setArmPIDRampRate(double arg0, int arg1) {
		armRight.configClosedloopRamp(arg0, arg1);
	}

	public static void moveArm(double power) {
		//armLeft.set(ControlMode.PercentOutput, power);
		armRight.set(ControlMode.PercentOutput, power);
	}

	public void stop() {
		armLeft.set(ControlMode.PercentOutput, 0);
		armRight.set(ControlMode.PercentOutput, 0);
	}

	public void setSoftLimits(int forward, int reverse) {
		RobotMap.arm_left.configForwardSoftLimitThreshold(forward, 0);
		RobotMap.arm_right.configForwardSoftLimitThreshold(forward, 0);

		RobotMap.arm_left.configReverseSoftLimitThreshold(reverse, 0);
		RobotMap.arm_right.configReverseSoftLimitThreshold(reverse, 0);

		if(RobotMap.alt_ControlDebug) {
		SmartDashboard.putNumber("forwardArmSoftLimit", forward);
		SmartDashboard.putNumber("reverseArmSoftLimit", reverse);

		SmartDashboard.putBoolean("forwardArmLimitSwitch", RobotMap.arm_right.getSensorCollection().isFwdLimitSwitchClosed());
		SmartDashboard.putBoolean("reverseArmLimitSwitch", RobotMap.arm_right.getSensorCollection().isRevLimitSwitchClosed());

		}
	}

	public boolean sameSide(double currentPosition, double desiredPosition) {
		if(RobotMap.alt_ControlDebug) {
		SmartDashboard.putNumber("currentArmPositionSS", currentPosition);
		SmartDashboard.putNumber("desiredArmPositionSS", desiredPosition);
		}
		if (currentPosition <= reverseTopSL && desiredPosition <= reverseTopSL) {
			return true;
		} else if (currentPosition >= forwardTopSL && desiredPosition >= forwardTopSL) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Debug, turn on/off in RobotMap
	 */
	public void periodic() {
		if(RobotMap.alt_ControlDebug) {
			
		SmartDashboard.putNumber("armEncoderPosition", RobotMap.arm_right.getSelectedSensorPosition(0));
		
		SmartDashboard.putNumber("trolleyStringPotValue(in arm)", RobotMap.trolley_right.getSelectedSensorPosition(0));
		}
	}
}
