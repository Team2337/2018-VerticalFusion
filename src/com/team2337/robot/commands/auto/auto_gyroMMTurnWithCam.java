package com.team2337.robot.commands.auto;

import com.team2337.fusion.vision.VisionProcessing;
import com.team2337.robot.RobotMap;
import com.team2337.robot.Robot;
import com.team2337.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns to a desired angle using the gyro along side MotionMagic code, with the pixy cam 
 * 
 * @category AUTO-TURN
 * @author Robin B., Sean L. 
 */
public class auto_gyroMMTurnWithCam extends Command { 

	public static double driveFL, drivePL, driveIL, driveDL, driveFR, drivePR, driveIR, driveDR, rev, centerX,
			secondsFromNeutralToFull, angleDifference, timeout;
	public static int timeoutMs = 0;
	public static int slotIdx = 0;
	public static int sensorUnitsPer100ms, sensorUnitsPer100msPerSec;
	private static int time = 0;
	public static double degree;
	static double acceptableAngleError = 2;
	public VisionProcessing boilerVision = RobotMap.vision;
	static boolean angleDone;
	
	private double moveSpeed, cubeX, turnAngle;
	private double pixlesPerDegree = 3.86;

	/**
	 * @param timeout
	 * The amount of time the command is allowed to run before it is forced to terminate
	 */
	public auto_gyroMMTurnWithCam(double timeout) {
		requires(Robot.chassis);
		this.timeout = timeout;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.gyro.resetPidgey();
		angleDone = false;
		secondsFromNeutralToFull = .1;
		RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, timeoutMs);
		RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, timeoutMs);
		
		centerX = Robot.pixy.centerXX;
		cubeX = Robot.pixy.xx;
		degree = (cubeX - centerX) / pixlesPerDegree;
		
		System.out.println(degree);
		
		driveFR = 0.05; 
		if (Math.abs(degree) > 50) {
			drivePR = .12;
		} else if(Math.abs(degree) < 10) {
			drivePR = 0.5;
		} else {
			drivePR = 0.35;
		}
		driveIR = 0;
		driveDR = 2;
		RobotMap.chassis_rightFront.config_kF(slotIdx, driveFR, timeoutMs);
		RobotMap.chassis_rightFront.config_kP(slotIdx, drivePR, timeoutMs);
		RobotMap.chassis_rightFront.config_kI(slotIdx, driveIR, timeoutMs);
		RobotMap.chassis_rightFront.config_kD(slotIdx, driveDR, timeoutMs);

		driveFL = 0.05; 
		if (Math.abs(degree) > 50) {
			drivePL = .12;
		} else {
			drivePL = 0.4;
		}
		driveIL = 0;
		driveDL = 2;
		RobotMap.chassis_leftFront.config_kF(slotIdx, driveFL, timeoutMs);
		RobotMap.chassis_leftFront.config_kP(slotIdx, drivePL, timeoutMs);
		RobotMap.chassis_leftFront.config_kI(slotIdx, driveIL, timeoutMs);
		RobotMap.chassis_leftFront.config_kD(slotIdx, driveDL, timeoutMs);

		sensorUnitsPer100ms = 13000; // Velocity
		sensorUnitsPer100msPerSec = 13000; // Acceleration

		RobotMap.chassis_leftFront.configMotionCruiseVelocity(sensorUnitsPer100ms, timeoutMs); 
		RobotMap.chassis_rightFront.configMotionCruiseVelocity(sensorUnitsPer100ms, timeoutMs);

		RobotMap.chassis_leftFront.configMotionAcceleration(sensorUnitsPer100msPerSec, timeoutMs);
		RobotMap.chassis_rightFront.configMotionAcceleration(sensorUnitsPer100msPerSec, timeoutMs);

		RobotMap.chassis_leftFront.configClosedloopRamp(secondsFromNeutralToFull, timeoutMs);
		RobotMap.chassis_rightFront.configClosedloopRamp(secondsFromNeutralToFull, timeoutMs);
		RobotMap.chassis_leftFront.configNominalOutputForward(.2, timeoutMs);
		RobotMap.chassis_rightFront.configNominalOutputForward(.2, timeoutMs);

		RobotMap.chassis_leftFront.configNominalOutputReverse(-.2, timeoutMs);
		RobotMap.chassis_rightFront.configNominalOutputReverse(-.2, timeoutMs);

		Robot.chassis.setBrakeMode(NeutralMode.Coast);
		rev = Math.abs(degree) * 145;

		System.out.println("rev: " + rev);
		
		if (degree > 0 && (centerX < 250 && centerX > 75)) {
			// turn left
			RobotMap.chassis_leftFront.set(ControlMode.MotionMagic, -rev);
			RobotMap.chassis_rightFront.set(ControlMode.MotionMagic, rev);

			System.out.println("Left -" + rev + " " + rev  + "  " + degree);
		} else if (degree < 0 && (centerX < 250 && centerX > 75)) {
			// turn right
			RobotMap.chassis_leftFront.set(ControlMode.MotionMagic, rev);
			RobotMap.chassis_rightFront.set(ControlMode.MotionMagic, -rev);
			System.out.println("Right " + rev + " - " + rev + "  " + degree);
			

		}

		setTimeout(timeout);
	}

	protected void execute() {
		angleDifference = Robot.gyro.getYaw() - degree;
		boolean var = (Math.abs(angleDifference) < acceptableAngleError);
		if (var) {
			time++;
		} else {
			time = 0;
		}
		if (time > 10) {
			angleDone = true;
			time = 0;
		}
	

	}

	protected boolean isFinished() {
		return isTimedOut() || angleDone; // || getBetween(rev, RobotMap.chassis_leftFront.getPosition(), 0.002) ||
	}

	protected void end() {
		angleDone = false;
		System.out.println("finished: " + angleDifference);
		// Robot.chassis.changeVbusToFollower(0);
		// Robot.chassis.setBrakeMode(NeutralMode.Brake);
	}

	protected void interrupted() {
		this.end();
	}

	private boolean getBetween(double constant, double input, double deadband) {
		if (input >= (constant - deadband) && input <= (constant + deadband)) {
			return true;
		}
		return false;
	}

}
