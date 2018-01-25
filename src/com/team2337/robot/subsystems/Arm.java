package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.arm.arm_joystickControl;
import com.team2337.robot.commands.lifter.lifter_joystickControl;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The subsystem to control the arm via a PID
 * 
 * @category LIFTER
 * @author Brendan, Jennah, Bryce
 */
public class Arm extends PIDSubsystem {

	private final TalonSRX armLeft = RobotMap.arm_left;
	private final TalonSRX armRight = RobotMap.arm_right;

	private boolean PIDStatus = false;

	public static double armAngle = 0;
	private double maxSpeedUp = 0.5;
	private double maxSpeedDown = -0.5;

	protected void initDefaultCommand() {
		setDefaultCommand(new arm_joystickControl());
	}

	public Arm() {

		super("Arm", 4.0, 0.0, 0.0);
		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
		getPIDController().setInputRange(0, 1);
	}
	
	/**
	 * Returns the input of the PID 
	 */
	public double returnPIDInput() {


		return armLeft.getSensorCollection().getQuadraturePosition();
//		return SmartDashboard.getNumber("ArmPosition", 0.5);
	}
	
	/**
	 * Returns the Output of the PID
	 */
	protected void usePIDOutput(double output) {

		armLeft.set(ControlMode.PercentOutput, -output);
		armRight.set(ControlMode.PercentOutput, output);

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
	public void setPosition(double pos){
	    	setSetpoint(pos);
	}
	
	public void moveForward(double power) {
		armLeft.set(ControlMode.PercentOutput, power);
		armRight.set(ControlMode.PercentOutput, power);
	}
	public void moveBackward(double power) {
		armLeft.set(ControlMode.PercentOutput, -power);
		armRight.set(ControlMode.PercentOutput, -power);
	}
	public void stop() {
		armLeft.set(ControlMode.PercentOutput, 0);
		armRight.set(ControlMode.PercentOutput, 0);
	}
	
	public static void setSoftLimits(int forward, int reverse) {
		RobotMap.arm_left.configForwardSoftLimitThreshold(forward, 0);
		RobotMap.arm_right.configForwardSoftLimitThreshold(forward, 0);
		
		RobotMap.arm_left.configReverseSoftLimitThreshold(reverse, 0);
		RobotMap.arm_right.configReverseSoftLimitThreshold(reverse, 0);
		
		SmartDashboard.putNumber("forwardSoftLimit", forward);
		SmartDashboard.putNumber("reverseSoftLimit", reverse);
		
	}
	
}
