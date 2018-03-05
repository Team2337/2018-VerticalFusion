package com.team2337.robot.commands.auto;

import com.team2337.fusion.vision.VisionProcessing;
import com.team2337.robot.RobotMap;
import com.team2337.robot.Robot;
import com.team2337.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class auto_MMDriveRemote extends Command {

	public static double turnF, turnP, turnI, turnD, turnPeak, driveFR, drivePR, driveIR, driveDR, drivePeak,
			degree, secondsFromNeutralToFull, angleDifference;
	public static int timeoutMs = 0;
	public static int distancePIDSlot_0 = 0;
	public static int turningPIDSlot_1 = 1;
	public static int sensorUnitsPer100ms, sensorUnitsPer100msPerSec;
	private static int time = 0;
	static double acceptableAngleError = 1;
	public VisionProcessing boilerVision = RobotMap.vision;
	static boolean angleDone;
	private static int distance;
	public final static int REMOTE_0 = 0;
	public final static int REMOTE_1 = 1;
	/**
	 * This is a property of the Pigeon IMU, and should not be changed.
	 */	
	public final static int kPigeonUnitsPerRotation = 8192;
	/**
	 * Using the config feature, scale units to 3600 per rotation.
	 * This is nice as it keeps 0.1 deg resolution, and is fairly intuitive.
	 */
	public final static double kTurnTravelUnitsPerRotation = 3600;
	
	public auto_MMDriveRemote(int distance, double degree) {
		requires(Robot.chassis);
		auto_MMDriveRemote.distance = distance;
		auto_MMDriveRemote.degree = degree;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// set this to distance in inchesS
		RobotMap.chassis_rightFront.configRemoteFeedbackFilter(	RobotMap.chassis_leftFront.getDeviceID(),
				RemoteSensorSource.TalonSRX_SelectedSensor,
				REMOTE_0,
				timeoutMs);
		/* Remote 1 will be a pigeon */
		RobotMap.chassis_rightFront.configRemoteFeedbackFilter(	RobotMap.intake_left.getDeviceID(),
				RemoteSensorSource.Pigeon_Yaw,
				REMOTE_1,
				timeoutMs);
		
		RobotMap.chassis_rightFront.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, timeoutMs);
		RobotMap.chassis_rightFront.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.QuadEncoder, timeoutMs);
		RobotMap.chassis_rightFront.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0, timeoutMs);
		RobotMap.chassis_rightFront.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.QuadEncoder, timeoutMs);
		/* select sum for distance(0), different for turn(1) */
		RobotMap.chassis_rightFront.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, timeoutMs);
		
		
		RobotMap.chassis_rightFront.configSelectedFeedbackCoefficient(1.0, distancePIDSlot_0, timeoutMs);

		RobotMap.chassis_rightFront.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor1, turningPIDSlot_1, timeoutMs);

		RobotMap.chassis_rightFront.configSelectedFeedbackCoefficient(kTurnTravelUnitsPerRotation / kPigeonUnitsPerRotation, distancePIDSlot_0, timeoutMs);
		
		
		RobotMap.chassis_rightFront.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, timeoutMs);
		RobotMap.chassis_rightFront.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, timeoutMs);
		RobotMap.chassis_rightFront.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, timeoutMs);
		RobotMap.chassis_rightFront.setStatusFramePeriod(StatusFrame.Status_10_Targets, 20, timeoutMs);
		/* speed up the left since we are polling it's sensor */
		RobotMap.chassis_leftFront.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, timeoutMs);
		
		

		RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, timeoutMs);
		RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, timeoutMs);

		driveFR = 0; // 0.00425; //.01370574769317461; forward .17
		drivePR = 0.13;// .03; // forward .013
		driveIR = 0;
		driveDR = 2;
		drivePeak = .5;
		RobotMap.chassis_rightFront.config_kF(distancePIDSlot_0, driveFR, timeoutMs);
		RobotMap.chassis_rightFront.config_kP(distancePIDSlot_0, drivePR, timeoutMs);
		RobotMap.chassis_rightFront.config_kI(distancePIDSlot_0, driveIR, timeoutMs);
		RobotMap.chassis_rightFront.config_kD(distancePIDSlot_0, driveDR, timeoutMs);
		RobotMap.chassis_rightFront.configClosedLoopPeakOutput(distancePIDSlot_0, drivePeak, timeoutMs);

		turnF = 0;
		turnP = 0;
		turnI = 0;
		turnD = 0;
		turnPeak = .5;
		
		RobotMap.chassis_rightFront.config_kF(turningPIDSlot_1, driveFR, timeoutMs);
		RobotMap.chassis_rightFront.config_kP(turningPIDSlot_1, drivePR, timeoutMs);
		RobotMap.chassis_rightFront.config_kI(turningPIDSlot_1, driveIR, timeoutMs);
		RobotMap.chassis_rightFront.config_kD(turningPIDSlot_1, driveDR, timeoutMs);
		RobotMap.chassis_rightFront.configClosedLoopPeakOutput(turningPIDSlot_1, turnPeak, timeoutMs);

		sensorUnitsPer100ms = 4000; // Velocity // 2000//13000
		sensorUnitsPer100msPerSec = 4000; // Acceleration

		RobotMap.chassis_leftFront.configMotionCruiseVelocity(sensorUnitsPer100ms, timeoutMs); // 75% of 937
		RobotMap.chassis_rightFront.configMotionCruiseVelocity(sensorUnitsPer100ms, timeoutMs);

		RobotMap.chassis_leftFront.configMotionAcceleration(sensorUnitsPer100msPerSec, timeoutMs);
		RobotMap.chassis_rightFront.configMotionAcceleration(sensorUnitsPer100msPerSec, timeoutMs);

		RobotMap.chassis_leftFront.configClosedloopRamp(secondsFromNeutralToFull, timeoutMs);
		RobotMap.chassis_rightFront.configClosedloopRamp(secondsFromNeutralToFull, timeoutMs);
		
		RobotMap.chassis_leftFront.configNominalOutputForward(.2, timeoutMs);
		RobotMap.chassis_rightFront.configNominalOutputForward(.2, timeoutMs);
		RobotMap.chassis_leftFront.configNominalOutputReverse(-.2, timeoutMs);
		RobotMap.chassis_rightFront.configNominalOutputReverse(-.2, timeoutMs);
		
		RobotMap.chassis_leftFront.configPeakOutputForward(+1.0, timeoutMs);
		RobotMap.chassis_leftFront.configPeakOutputReverse(-1.0, timeoutMs);
		RobotMap.chassis_rightFront.configPeakOutputForward(+1.0, timeoutMs);
		RobotMap.chassis_rightFront.configPeakOutputReverse(-1.0, timeoutMs);


		Robot.chassis.setBrakeMode(NeutralMode.Brake);

	    RobotMap.chassis_rightFront.selectProfileSlot(REMOTE_0, REMOTE_0);
		RobotMap.chassis_rightFront.selectProfileSlot(REMOTE_1, REMOTE_1);
		
		

		RobotMap.chassis_rightFront.set(ControlMode.MotionMagic, distance, DemandType.AuxPID, degree);
		RobotMap.chassis_leftFront.follow(RobotMap.chassis_rightFront, FollowerType.AuxOutput1);
		setTimeout(10);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*
		if (var) {
			time++;
		} else {
			time = 0;
		}
		if (time > 10) {
			angleDone = true;
			time = 0;
		}
*/
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut(); //|| angleDone; // || getBetween(rev, RobotMap.chassis_leftFront.getPosition(), 0.002) ||
											// Robot.oi.getOperatorControls().getRawButton(3);
	}

	// Called once after isFinished returns true
	protected void end() {
		angleDone = false;
		Robot.chassis.setBrakeMode(NeutralMode.Coast);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}



}
