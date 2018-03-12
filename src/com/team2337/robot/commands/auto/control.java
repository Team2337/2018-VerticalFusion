package com.team2337.robot.commands.auto;


import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.auto.profiles.MotionProfile;
import com.team2337.robot.commands.auto.profiles.MotionProfileManager;

import edu.wpi.first.wpilibj.command.Command;

public class control extends Command 
{
	
	private int finalHeading_units;
	private boolean isForward;
	public final static int REMOTE_0 = 0;
	public final static int REMOTE_1 = 1;
	public final static int PID_PRIMARY = 0;
	public final static int PID_TURN = 1;
	public final static int PID_MotProf = 2;
	/**
	 * How many sensor units per rotation.
	 * Using CTRE Magnetic Encoder.
	 * @link https://github.com/CrossTheRoadElec/Phoenix-Documentation#what-are-the-units-of-my-sensor
	 */
	public final static int kSensorUnitsPerRotation = 4096;
	/**
	 * This is a property of the Pigeon IMU, and should not be changed.
	 */
	public final static int kPigeonUnitsPerRotation = 8192;
	/**
	 * Using the config feature, scale units to 3600 per rotation.
	 * This is nice as it keeps 0.1 deg resolution, and is fairly intuitive.
	 */
	public final static double kTurnTravelUnitsPerRotation = 3600;

	/**
	 * set to zero to skip waiting for confirmation, set to nonzero to wait
	 * and report to DS if action fails.
	 */
	public final static int kTimeoutMs = 10;
	
	double timeout = 1.5;
	
	TalonSRX rightFront = RobotMap.chassis_rightFront;
	VictorSPX rightMid = RobotMap.chassis_rightMid;
	VictorSPX rightRear = RobotMap.chassis_rightRear;
		
	TalonSRX leftFront = RobotMap.chassis_leftFront;
	VictorSPX leftMid = RobotMap.chassis_leftMid;
	VictorSPX leftRear = RobotMap.chassis_leftRear;
	
	PigeonIMU gyro = Pigeon.pidgey;
	
	
	
	MotionProfileManager motionMgr;// = RobotMap.motionManager;
	
	public control() {

		
		 
	}
	
	protected void initialize() {
		requires(Robot.chassis);
		
		motionMgr = new MotionProfileManager(rightFront, 0, MotionProfile.Points, MotionProfile.kNumPoints);
		
		//provide the final heading.
		finalHeading_units = 0;

		rightFront.neutralOutput();
		Robot.chassis.resetEncoders();
		Robot.gyro.resetPidgey();
		motionMgr.reset();
		// isForward sets the direction of the robot. True = forward false = reverse
		isForward = true;
		motionMgr.start(finalHeading_units, isForward);
		setTimeout(timeout);
		
		
		rightFront.configRemoteFeedbackFilter(	leftFront.getDeviceID(),
				RemoteSensorSource.TalonSRX_SelectedSensor,
				REMOTE_0,
				kTimeoutMs);
		
		rightFront.configRemoteFeedbackFilter(	gyro.getDeviceID(),
				RemoteSensorSource.Pigeon_Yaw,
				REMOTE_1,
				kTimeoutMs);
		
		/* setup sum and difference signals */
		rightFront.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, kTimeoutMs);
		rightFront.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.QuadEncoder, kTimeoutMs);
		rightFront.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0, kTimeoutMs);
		rightFront.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.QuadEncoder, kTimeoutMs);
		/* select sum for distance(0), different for turn(1) */
		rightFront.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, PID_PRIMARY, kTimeoutMs);
		
		
		
		
		/* do not scale down the primary sensor (distance).  If selected sensor is going to be a sensorSum
		 * user can pass 0.5 to produce an average. */
		rightFront.configSelectedFeedbackCoefficient(1.0, PID_PRIMARY, kTimeoutMs);

		rightFront.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor1,
												PID_TURN,
												kTimeoutMs);

		rightFront.configSelectedFeedbackCoefficient(kTurnTravelUnitsPerRotation / kPigeonUnitsPerRotation, PID_TURN, kTimeoutMs);
		
		
		
		
		
		rightFront.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, kTimeoutMs);
		rightFront.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, kTimeoutMs);
		rightFront.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, kTimeoutMs);
		rightFront.setStatusFramePeriod(StatusFrame.Status_10_Targets, 20, kTimeoutMs);
		/* speed up the left since we are polling it's sensor */
		leftFront.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, kTimeoutMs);

		
		
		
		rightFront.configMotionAcceleration(6000, kTimeoutMs);
		rightFront.configMotionCruiseVelocity(6000, kTimeoutMs);

		/* max out the peak output (for all modes).  However you can
		 * limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		leftFront.configPeakOutputForward(+1.0, kTimeoutMs);
		leftFront.configPeakOutputReverse(-1.0, kTimeoutMs);
		leftFront.configPeakOutputForward(+1.0, kTimeoutMs);
		leftFront.configPeakOutputReverse(-1.0, kTimeoutMs);
		
		
		//Drive PID
		rightFront.config_kP(PID_PRIMARY, 0.1, kTimeoutMs);
		rightFront.config_kI(PID_PRIMARY, 0.0, kTimeoutMs);
		rightFront.config_kD(PID_PRIMARY, 0.0, kTimeoutMs);
		rightFront.config_kF(PID_PRIMARY, 0.0, kTimeoutMs);
		
		//Turn PID
		rightFront.config_kP(PID_TURN, 2.0, kTimeoutMs);
		rightFront.config_kI(PID_TURN, 0.0, kTimeoutMs);
		rightFront.config_kD(PID_TURN, 4.0, kTimeoutMs);
		rightFront.config_kF(PID_TURN, 0.0, kTimeoutMs);
		rightFront.config_IntegralZone(PID_TURN, 200, kTimeoutMs);
		rightFront.configClosedLoopPeakOutput(	PID_TURN, 1.0, kTimeoutMs);

		/* magic servo */
		rightFront.config_kP(PID_MotProf, 1.0, kTimeoutMs);
		rightFront.config_kI(PID_MotProf, 0.0, kTimeoutMs);
		rightFront.config_kD(PID_MotProf, 0.0, kTimeoutMs);
		rightFront.config_kF(PID_MotProf, 1023/14000, kTimeoutMs);
		rightFront.config_IntegralZone(PID_MotProf, 400, kTimeoutMs);
		rightFront.configClosedLoopPeakOutput(PID_MotProf, 1.00, kTimeoutMs);		
		
		rightFront.setNeutralMode(NeutralMode.Brake);
		leftFront.setNeutralMode(NeutralMode.Brake);
		

		
		
		/* 1ms per loop.  PID loop can be slowed down if need be.
		 * For example,
		 * - if sensor updates are too slow
		 * - sensor deltas are very small per update, so derivative error never gets large enough to be useful.
		 * - sensor movement is very slow causing the derivative error to be near zero.
		
		int closedLoopTimeMs = 1;
		rightFront.configSetParameter(ParamEnum.ePIDLoopPeriod, closedLoopTimeMs, 0x00, PID_PRIMARY, kTimeoutMs);
		rightFront.configSetParameter(ParamEnum.ePIDLoopPeriod, closedLoopTimeMs, 0x00, PID_TURN, kTimeoutMs);
 */
		/**
		 * false means talon's local output is PID0 + PID1, and other side Talon is PID0 - PID1
		 * true means talon's local output is PID0 - PID1, and other side Talon is PID0 + PID1
		 */
		rightFront.configAuxPIDPolarity(false, kTimeoutMs);
		
		rightFront.set(ControlMode.MotionProfileArc, 1);
		leftFront.follow(rightFront, FollowerType.AuxOutput1);
		
		zeroSensors();
		
	}
	
	void zeroSensors() {
		leftFront.getSensorCollection().setQuadraturePosition(0, kTimeoutMs);
		rightFront.getSensorCollection().setQuadraturePosition(0, kTimeoutMs);
		gyro.setYaw(0, kTimeoutMs);
		gyro.setAccumZAngle(0, kTimeoutMs);
		System.out.println("        [Sensors] All sensors are zeroed.\n");
	}

	protected void execute() {
		motionMgr.control();
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
	

}
