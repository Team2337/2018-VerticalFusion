package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.arm.TEST_ONLY_arm_joystickControl;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The subsystem to control the arm via a PID
 * 
 * @category LIFTER
 * @author Brendan, Jennah, Bryce
 */
public class Arm extends PIDSubsystem {

	private final static TalonSRX armLeft = RobotMap.arm_left;
	public final static TalonSRX armRight = RobotMap.arm_right;

	private boolean PIDStatus = false;

	public static final double armAngle 	= 0;
	
	private static final double maxSpeedUp 		= 0.3;
	private static final double maxSpeedDown 	= -0.3;
	
	public static final int forwardSoftLimit 	= 5320;
	public static final int forwardTopSL 		= 4538;
	public static final int reverseTopSL 		= 4023;
	public static final int reverseSoftLimit 	= 3400;
	
	public static final double stringpotTopPos 		= 1.0;
	public static final double stringpotMidPos 		= 0.7;
	public static final double stringpotBottomPos 	= 0.1;

	protected void initDefaultCommand() {
		//setDefaultCommand(new TEST_ONLY_arm_joystickControl());
	}

	public Arm() {

		super("Arm", 0.004, 0.0, 0.0);
		setAbsoluteTolerance(70);
		getPIDController().setContinuous(false);
		getPIDController().setInputRange(0, 8000);
		
		setSoftLimits(forwardSoftLimit, reverseSoftLimit);
		setTeleopArmSpeed();
	}

	

	/**
	 * Returns the input of the PID
	 */
	public double returnPIDInput() {


		return RobotMap.arm_right.getSelectedSensorPosition(0);
		// return SmartDashboard.getNumber("ArmPosition", 0.5);
	}

	/**
	 * Returns the Output of the PID
	 */
	protected void usePIDOutput(double output) {

		/*
		 * armLeft.set(ControlMode.PercentOutput, -output);
		 * armRight.set(ControlMode.PercentOutput, output);
		 */
		SmartDashboard.putNumber("numberOutput", output);
		Arm.moveArm(output);

	}

	// StopPID, toggle option for stopping it.
	/**
	 * Disables the PID subsystem on the lift.
	 */
	public void stopPID() {
		this.PIDStatus = true;
		this.disable();

	}

	/**
	 * Enables the PID subsystem on the lift.
	 */
	public void startPID() {
		this.PIDStatus = false;
		this.enable();

	}

	// Return the PID when needed to another command.
	/**
	 * Returns the status of the PID subsystem to determine whether it is enabled.
	 * 
	 * @return true or false
	 */
	public boolean getPIDStatus() {
		return this.PIDStatus;
	}
	//
	/*
	 * public boolean LiftAutoTote() { return liftAutoTote.get(); }
	 */

	/**
	 * Returns the status of the operator station button to determine whether the
	 * joystick Y axis controls the lift or the back arm.
	 * 
	 * @return true or false
	 */

	public boolean joystickModeStatus() {
		// return this.joystickStatus;
		return false;
	}

	/**
	 * Set the Output range for the Lift during Teleop.
	 */
	public void setTeleopArmSpeed() {
		getPIDController().setOutputRange(maxSpeedDown, maxSpeedUp); // For the lift PID
	}

	/**
	 * Sets the position of the lifter
	 */
	public void setPosition(double pos) {
		setSetpoint(pos);
		//RobotMap.arm_right.set(ControlMode.Position, pos);
		enable();
	}

	public static void moveArm(double power) {
		//armLeft.set(ControlMode.PercentOutput, power);
		armRight.set(ControlMode.PercentOutput, power);
	}

	public static void moveBackward(double power) {
		armLeft.set(ControlMode.PercentOutput, power);
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
