package com.team2337.robot.commands.auto;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.sensors.PigeonIMU_StatusFrame;
import com.team2337.robot.Robot;
import com.team2337.fusion.drive.Constants;
import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
/**
 *
 */
public class auto_MMDrivePigeon extends Command {

	TalonSRX chassis_leftFront = RobotMap.chassis_leftFront;
	TalonSRX chassis_rightFront = RobotMap.chassis_rightFront;
	public int distance;
	public int degree;
	PigeonIMU pidgey = Pigeon.pidgey;
	


	public auto_MMDrivePigeon(int distance, int degree) {
		requires(Robot.chassis);
		this.distance = distance;
		this.degree = degree;
	}


	protected void initialize() {
		/* Disable all motor controllers */
		chassis_rightFront.set(ControlMode.PercentOutput, 0);
		chassis_leftFront.set(ControlMode.PercentOutput, 0);
		
		/* Set Neutral Mode */
		chassis_leftFront.setNeutralMode(NeutralMode.Brake);
		chassis_rightFront.setNeutralMode(NeutralMode.Brake);

		//chassis_leftFront.getSensorCollection(
		
		/** Feedback Sensor Configuration */
		
		/* Configure the left Talon's selected sensor as local QuadEncoder */
		chassis_leftFront.configSelectedFeedbackSensor(	FeedbackDevice.QuadEncoder,				// Local Feedback Source
													Constants.PID_PRIMARY,					// PID Slot for Source [0, 1]
													Constants.kTimeoutMs);					// Configuration Timeout

		/* Configure the Remote Talon's selected sensor as a remote sensor for the right Talon */
		chassis_rightFront.configRemoteFeedbackFilter(chassis_leftFront.getDeviceID(),					// Device ID of Source
												RemoteSensorSource.TalonSRX_SelectedSensor,	// Remote Feedback Source
												Constants.REMOTE_0,							// Source number [0, 1]
												Constants.kTimeoutMs);						// Configuration Timeout
		
		/* Configure the Pigeon IMU to the other remote slot available on the right Talon */
		chassis_rightFront.configRemoteFeedbackFilter(pidgey.getDeviceID(),
												RemoteSensorSource.Pigeon_Yaw,
												Constants.REMOTE_1,	
												Constants.kTimeoutMs);
		
		/* Setup Sum signal to be used for Distance */
		chassis_rightFront.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, Constants.kTimeoutMs);				// Feedback Device of Remote Talon
		chassis_rightFront.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kTimeoutMs);	// Quadrature Encoder of current Talon
		
		/* Configure Sum [Sum of both QuadEncoders] to be used for Primary PID Index */
		chassis_rightFront.configSelectedFeedbackSensor(	FeedbackDevice.SensorSum, 
													Constants.PID_PRIMARY,
													Constants.kTimeoutMs);
		
		/* Scale Feedback by 0.5 to half the sum of Distance */
		chassis_rightFront.configSelectedFeedbackCoefficient(	0.5, 						// Coefficient
														Constants.PID_PRIMARY,		// PID Slot of Source 
														Constants.kTimeoutMs);		// Configuration Timeout
		
		/* Configure Remote 1 [Pigeon IMU's Yaw] to be used for Auxiliary PID Index */
		chassis_rightFront.configSelectedFeedbackSensor(	FeedbackDevice.RemoteSensor1,
													Constants.PID_TURN,
													Constants.kTimeoutMs);
		
		/* Scale the Feedback Sensor using a coefficient */
		chassis_rightFront.configSelectedFeedbackCoefficient(	Constants.kTurnTravelUnitsPerRotation / Constants.kPigeonUnitsPerRotation,
														Constants.PID_TURN,
														Constants.kTimeoutMs);
		
		/* Configure output and sensor direction */
		
		/*
	    chassis_leftFront.setInverted(false);
		chassis_leftFront.setSensorPhase(true);
		chassis_rightFront.setInverted(true);
		chassis_rightFront.setSensorPhase(false);
		 */
		
		chassis_leftFront.setInverted(false);
		chassis_leftFront.setSensorPhase(true);
		chassis_rightFront.setInverted(true);
		chassis_rightFront.setSensorPhase(false);
		
		/* Set status frame periods to ensure we don't have stale data */
		chassis_rightFront.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, Constants.kTimeoutMs);
		chassis_rightFront.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, Constants.kTimeoutMs);
		chassis_rightFront.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, Constants.kTimeoutMs);
		chassis_rightFront.setStatusFramePeriod(StatusFrame.Status_10_Targets, 20, Constants.kTimeoutMs);
		chassis_leftFront.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, Constants.kTimeoutMs);
		pidgey.setStatusFramePeriod(PigeonIMU_StatusFrame.CondStatus_9_SixDeg_YPR , 5, Constants.kTimeoutMs);

		/* Configure neutral deadband */
		chassis_rightFront.configNeutralDeadband(Constants.kNeutralDeadband, Constants.kTimeoutMs);
		chassis_leftFront.configNeutralDeadband(Constants.kNeutralDeadband, Constants.kTimeoutMs);
		
		/* Motion Magic Configurations */
		chassis_rightFront.configMotionAcceleration(16000, Constants.kTimeoutMs);
		chassis_rightFront.configMotionCruiseVelocity(16000, Constants.kTimeoutMs);

		/* Max out the peak output (for all modes).  
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		chassis_leftFront.configPeakOutputForward(+1.0, Constants.kTimeoutMs);
		chassis_leftFront.configPeakOutputReverse(-1.0, Constants.kTimeoutMs);
		chassis_rightFront.configPeakOutputForward(+1.0, Constants.kTimeoutMs);
		chassis_rightFront.configPeakOutputReverse(-1.0, Constants.kTimeoutMs);
		
		chassis_rightFront.configNominalOutputForward(0.25, Constants.kTimeoutMs);
		chassis_rightFront.configNominalOutputReverse(-0.25, Constants.kTimeoutMs);
		chassis_leftFront.configNominalOutputForward(0.25, Constants.kTimeoutMs);
		chassis_leftFront.configNominalOutputReverse(-0.25, Constants.kTimeoutMs);

		/* FPID Gains for distance servo */
		chassis_rightFront.config_kP(Constants.kSlot_Distanc, Constants.kGains_Distanc.kP, Constants.kTimeoutMs);
		chassis_rightFront.config_kI(Constants.kSlot_Distanc, Constants.kGains_Distanc.kI, Constants.kTimeoutMs);
		chassis_rightFront.config_kD(Constants.kSlot_Distanc, Constants.kGains_Distanc.kD, Constants.kTimeoutMs);
		chassis_rightFront.config_kF(Constants.kSlot_Distanc, Constants.kGains_Distanc.kF, Constants.kTimeoutMs);
		chassis_rightFront.config_IntegralZone(Constants.kSlot_Distanc, (int)Constants.kGains_Distanc.kIzone, Constants.kTimeoutMs);
		chassis_rightFront.configClosedLoopPeakOutput(Constants.kSlot_Distanc, Constants.kGains_Distanc.kPeakOutput, Constants.kTimeoutMs);
		chassis_rightFront.configAllowableClosedloopError(Constants.kSlot_Distanc, 0, Constants.kTimeoutMs);

		/* FPID Gains for turn servo */
		chassis_rightFront.config_kP(Constants.kSlot_Turning, Constants.kGains_Turning.kP, Constants.kTimeoutMs);
		chassis_rightFront.config_kI(Constants.kSlot_Turning, Constants.kGains_Turning.kI, Constants.kTimeoutMs);
		chassis_rightFront.config_kD(Constants.kSlot_Turning, Constants.kGains_Turning.kD, Constants.kTimeoutMs);
		chassis_rightFront.config_kF(Constants.kSlot_Turning, Constants.kGains_Turning.kF, Constants.kTimeoutMs);
		chassis_rightFront.config_IntegralZone(Constants.kSlot_Turning, (int)Constants.kGains_Turning.kIzone, Constants.kTimeoutMs);
		chassis_rightFront.configClosedLoopPeakOutput(Constants.kSlot_Turning, Constants.kGains_Turning.kPeakOutput, Constants.kTimeoutMs);
		chassis_rightFront.configAllowableClosedloopError(Constants.kSlot_Turning, 0, Constants.kTimeoutMs);
		
		/* 1ms per loop.  PID loop can be slowed down if need be.
		 * For example,
		 * - if sensor updates are too slow
		 * - sensor deltas are very small per update, so derivative error never gets large enough to be useful.
		 * - sensor movement is very slow causing the derivative error to be near zero.
		 */
		int closedLoopTimeMs = 1;
		chassis_rightFront.configSetParameter(ParamEnum.ePIDLoopPeriod, closedLoopTimeMs, 0x00, 0, Constants.kTimeoutMs);
		chassis_rightFront.configSetParameter(ParamEnum.ePIDLoopPeriod, closedLoopTimeMs, 0x00, 1, Constants.kTimeoutMs);

		/* configAuxPIDPolarity(boolean invert, int timeoutMs)
		 * false means talon's local output is PID0 + PID1, and other side Talon is PID0 - PID1
		 * true means talon's local output is PID0 - PID1, and other side Talon is PID0 + PID1
		 */
		chassis_rightFront.configAuxPIDPolarity(false, Constants.kTimeoutMs);

		/* Initialize */

		zeroSensors();	
		zeroDistance();
		
		/* Determine which slot affects which PID */
		chassis_rightFront.selectProfileSlot(Constants.kSlot_Distanc, Constants.PID_PRIMARY);
		chassis_rightFront.selectProfileSlot(Constants.kSlot_Turning, Constants.PID_TURN);
		
		/* Configured for MotionMagic on Quad Encoders' Sum and Auxiliary PID on Pigeon */
		chassis_rightFront.set(ControlMode.MotionMagic, distance, DemandType.AuxPID, degree);
		chassis_leftFront.follow(chassis_rightFront, FollowerType.AuxOutput1);

	}


	protected void execute() {
		//I'm not sure if anything needs to go here
	}


	protected boolean isFinished() {
		//Ends the command if time is up, or the right encoder is equal to the disired position
		return isTimedOut() || chassis_rightFront.getSensorCollection().getQuadraturePosition() == distance; 

	}


	protected void end() {
		chassis_rightFront.set(ControlMode.PercentOutput, 0);
		chassis_leftFront.set(ControlMode.PercentOutput, 0);
	}


	protected void interrupted() {
		this.end();
	}
	/** Zero all sensors, both Talons and Pigeon */
	void zeroSensors() {
		chassis_leftFront.getSensorCollection().setQuadraturePosition(0, Constants.kTimeoutMs);
		chassis_rightFront.getSensorCollection().setQuadraturePosition(0, Constants.kTimeoutMs);
		pidgey.setYaw(0, Constants.kTimeoutMs);
		pidgey.setAccumZAngle(0, Constants.kTimeoutMs);
		System.out.println("[Sensors] All sensors are zeroed.\n");
	}
	
	/** Zero QuadEncoders, used to reset position when initializing Motion Magic */
	void zeroDistance(){
		chassis_leftFront.getSensorCollection().setQuadraturePosition(0, Constants.kTimeoutMs);
		chassis_rightFront.getSensorCollection().setQuadraturePosition(0, Constants.kTimeoutMs);
		System.out.println("[QuadEncoders] All encoders are zeroed.\n");
	}
	

}
