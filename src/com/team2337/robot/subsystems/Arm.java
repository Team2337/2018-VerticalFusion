package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The subsystem to control the arm via a PID
 * 
 * @category ARM
 * @author 2337
 */
public class Arm extends Subsystem {

	private final static TalonSRX armRight = RobotMap.arm_right;

	//private boolean PIDStatus = false;
	
	private double kF = 0;
	private double kP = 2;  //1.1
	private double kI = 0;
	private double kD = 0;
	private int allowableError = 1;                ///need to set *****************//TODO

	private static final double maxSpeedUp 		= 1;
	private static final double maxSpeedDown 	= -1;
	private double nominalSpeed = 0;
	
	public static final int forwardSoftLimit 	= 350;   //1238 practice
	public static final int forwardLevel 		= -160;    // 900
	public static final int forwardTopSL 		= 548;   //548   /// not used

	public static final int centerPosition 		= -630;  //345
	
	public static final int reverseTopSL 		= -375;  //55   /// not used
	//public static final int reverseLevel 		= -550;	  //-550	
	public static final int reverseSoftLimit 	= -1680;   //-1100
	
	//public static final double stringpotTopPos 		= 1.0;
	//public static final double stringpotMidPos 		= 0.7;
	//public static final double stringpotBottomPos 	= 0.1;
	
	//private int absolutePosition;  //used to set relative position of the encoder based on absolute encoder

	protected void initDefaultCommand() {
		//setDefaultCommand(new TEST_ONLY_arm_joystickControl());
	}

	public Arm() {

		//getPIDController().setContinuous(false);
		//getPIDController().setInputRange(0, 8000);
		
		setSoftLimits(forwardSoftLimit, reverseSoftLimit);
				
		/* set the peak and nominal outputs, 12V? means full */
		armRight.configNominalOutputForward(nominalSpeed, 0);
		armRight.configNominalOutputReverse(-nominalSpeed, 0);
		armRight.configPeakOutputForward(maxSpeedUp, 0);
		armRight.configPeakOutputReverse(maxSpeedDown, 0);
		/*
		armLeft.configNominalOutputForward(nominalSpeed, 0);
		armLeft.configNominalOutputReverse(-nominalSpeed, 0);
		armLeft.configPeakOutputForward(maxSpeedUp, 0);
		armLeft.configPeakOutputReverse(maxSpeedDown, 0);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		armRight.configAllowableClosedloopError(0, allowableError, 0);  
		
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
		//absolutePosition = armRight.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		//absolutePosition &= 0xFFF;
		// if sensor out of phase:  			absolutePosition *= -1;  //TODO
		// motor inverted:           			absolutePosition *= -1;  //TODO    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!           
		/* set the quadrature (relative) sensor to match absolute */
		//armRight.setSelectedSensorPosition(absolutePosition, 0, 0);
	}

	/**
	 * Sets the setpoint of the arm
	 */
	public void setSetpoint(double pos) {
		armRight.set(ControlMode.Position, pos);
	}	
	
	public void holdPosition() {
		armRight.set(ControlMode.Position, getPosition());
	}
	
	/**
	 * Gets the set point of the arm
	 */
	/*public double getSetpoint(){
		//return armRight.getClosedLoopTarget(0);
	}*/
	
	/**
	 * Gets the set point error of the arm
	 */
	public double getError(){
		return armRight.getClosedLoopError(0);
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

	public void move(double power) {
		armRight.set(ControlMode.PercentOutput, power);
	}

	public void stop() {
		armRight.set(ControlMode.PercentOutput, 0);
	}
	
	public boolean armIsLevel() {
		return (getPosition() > 0.95 * forwardLevel);
	}
	public boolean armIsReverse() {
		return (getPosition() < reverseSoftLimit);
	}
	public boolean armIsForward() {
		return (getPosition() > forwardSoftLimit);
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
		if (desiredPosition >= Robot.bigBrother.points[10][4] && currentPosition >= Robot.bigBrother.points[10][4]) {
			return true;
		} else if (currentPosition <= Robot.bigBrother.points[11][4] && desiredPosition <= Robot.bigBrother.points[11][4]) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isScore() {
		if(getPosition() < centerPosition) {
			return true;
		}
		return false;
	}
	
	public boolean isPickUp() {
		if(getPosition() >= centerPosition) {
			return true;
		}
		return false;
	}
	
	/**
	 * Debug, turn on/off in RobotMap
	 */
	public void periodic() {
		if(RobotMap.alt_ControlDebug) {
			
		//SmartDashboard.putNumber("armGetSetpoint", getSetpoint());
		SmartDashboard.putNumber("armEncoderPositionPWM", RobotMap.arm_right.getSensorCollection().getPulseWidthPosition());
		SmartDashboard.putNumber("armGetSelectedSensorPos", RobotMap.arm_right.getSelectedSensorPosition(0));
		SmartDashboard.putString("controlMode", RobotMap.arm_right.getControlMode().toString());
		SmartDashboard.putNumber("armRight MotorOutput %", RobotMap.arm_right.getMotorOutputPercent());
		
		SmartDashboard.putNumber("trolleyStringPotValue(in arm)", RobotMap.trolley_right.getSelectedSensorPosition(0));
		}
	}
}
