package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.lifter.lifter_joystickControl;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;


/**
 * The system to move cubes up and down
 * 
 * @category LIFTER
 * @author Brendan, Bryce
 */
public class Lifter extends PIDSubsystem {

	private final AnalogPotentiometer potentiometer = RobotMap.lift_potentiometer;
	private final TalonSRX leftBack = RobotMap.lift_leftBack;
	private final TalonSRX rightBack = RobotMap.lift_rightBack;
	private final TalonSRX leftFront = RobotMap.lift_leftFront;
	private final TalonSRX rightFront = RobotMap.lift_rightFront;

	private boolean PIDStatus = false;

	public double potValue = RobotMap.lift_potentiometer.get();
	
	private double maxSpeedUp = 0.5;
	private double maxSpeedDown = -0.5;

	protected void initDefaultCommand() {
		setDefaultCommand(new lifter_joystickControl());
	}

	public Lifter() {

		super("Lift", 4.0, 0.0, 0.0);
		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);

		getPIDController().setInputRange(0, 5);

		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
	}
	
	/**
	 * Returns the input of the PID 
	 */
	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;

		return potentiometer.get();
	}
	
	/**
	 * Returns the Output of the PID
	 */
	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);

		leftFront.set(ControlMode.PercentOutput, -output);
		rightFront.set(ControlMode.PercentOutput, output);

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
	 * Set the Output range for the Lift during Telop.
	 */
	public void setTeleopLiftSpeed() {
		getPIDController().setOutputRange(maxSpeedDown, maxSpeedUp); // For the lift PID
	}
	/**
	 * Sets the position of the lifter
	 */
	public void setPosition(double pos){
	    	setSetpoint(pos);
	}
	
}
