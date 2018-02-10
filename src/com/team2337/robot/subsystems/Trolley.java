package com.team2337.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.bigBrother.setPointsChecking;
import com.team2337.robot.commands.trolley.trolley_joystickControl;
import com.team2337.robot.commands.trolley.trolley_joystickControl2;
import com.team2337.robot.commands.trolley.trolley_joystickControl3;
import com.team2337.robot.commands.trolley.trolley_setPID;
import com.team2337.robot.commands.trolley.trolley_startPID;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The system to move cubes up and down
 * 
 * @category TROLLEY
 * @author Bryce
 */
//public class Trolley extends PIDSubsystem {
public class Trolley extends Subsystem {

	//public final static AnalogPotentiometer potentiometer = RobotMap.lift_potentiometer;
	//private final TalonSRX leftBack = RobotMap.lift_leftBack;
	//private final TalonSRX rightBack = RobotMap.lift_rightBack;
	private final static TalonSRX leftFront = RobotMap.trolley_left;  //4
	private final static TalonSRX rightFront = RobotMap.trolley_right; //3


	//private boolean PIDStatus = false;

	//public double potValue = RobotMap.lift_potentiometer.get();
	
	private double maxSpeed = 0.5;
	private double nominalSpeed = 0;
	private double kF = 0;
	private double kP = 0.1;
	private double kI = 0;
	private double kD = 0;
	private int allowableError = 50;

	protected void initDefaultCommand() {
		//setDefaultCommand(new trolley_startPID());
	}

	public Trolley() {
		

		//super("Lift", 0.01, 0.0, 0.0);
		//setAbsoluteTolerance(2);
		//getPIDController().setContinuous(false);

		//getPIDController().setInputRange(-1000, 1000);
		
		
		rightFront.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);  //string pot
		rightFront.setSensorPhase(false);    //choose to ensure sensor is positive when output is positive
		rightFront.setInverted(true); //* choose what direction you want forward/positive to be.  * This does not affect sensor phase. */ 

		leftFront.follow(rightFront);
		leftFront.setInverted(true);
		
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
		rightFront.configAllowableClosedloopError(allowableError, 0, 0);  ///nned to set *****************//TODO
		
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
		int absolutePosition = rightFront.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		// if sensor out of phase:  			absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
		rightFront.setSelectedSensorPosition(absolutePosition, 0, 0);
	}
	
	/**
	 * Sets the position of the trolley
	 */
	public void setPosition(double pos){
		rightFront.set(ControlMode.Position, pos);
	    	//setSetpoint(pos);
	}
	
	/**
	 * Gets the set point of the trolley
	 */
	public double getSetpoint(){
		return rightFront.getClosedLoopTarget(0);
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
		rightFront.configForwardSoftLimitThreshold(forward, 0);
		leftFront.configForwardSoftLimitThreshold(forward, 0);
			
		rightFront.configReverseSoftLimitThreshold(reverse, 0);
		leftFront.configReverseSoftLimitThreshold(reverse, 0);
			
		SmartDashboard.putNumber("forwardLIFTSoftLimit", forward);
		SmartDashboard.putNumber("reverseLIFTSoftLimit", reverse);
			
	}
	//***********************************************************************************************************
	
	/**
	 * Returns the input of the PID 
	 */
	//protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		//SmartDashboard.putNumber("RightLIFTERSoftPoint", RobotMap.lift_right.getSelectedSensorPosition(0));
		//SmartDashboard.putNumber("frontLeftPIDInput", leftFront.getSensorCollection().getQuadraturePosition());
		//return (RobotMap.lift_right.getSelectedSensorPosition(0));//RobotMap.lift_stringPot.pidGet();
	//}
	
	/**
	 * Returns the Output of the PID
	 */
	//protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
	//	SmartDashboard.putNumber("liftNumberOutput", output);
	//	leftFront.set(ControlMode.PercentOutput, -output);
	//	rightFront.set(ControlMode.PercentOutput, output);
	//}

	// StopPID, toggle option for stopping it.
	/**
	 * Disables the PID subsystem on the lift.
	 */   
	//public void stopPID() {
	//	this.PIDStatus = true;
	//	this.disable();
//	}

	/**
	 * Enables the PID subsystem on the lift.
	 */
	//public void startPID() {
	//	this.PIDStatus = false;
	//	this.enable();
	//}

	// Return the PID when needed to another command.
	/**
	 * Returns the status of the PID subsystem to determine whether it is enabled.
	 * 
	 * @return true or false
	 */
	//public boolean getPIDStatus() {
	//	return this.PIDStatus;
	//}
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

	//public boolean joystickModeStatus() {
	//	// return this.joystickStatus;
//		return false;
	//}


	
	//public static void stop() {
	//	leftFront.set(ControlMode.PercentOutput, 0);
	//	rightFront.set(ControlMode.PercentOutput, 0);
//	}
	
}
