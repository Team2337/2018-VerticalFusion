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
 * Drive forward using Motion Magic, multiplying the ticks by 288
 * 
 * @category AUTO-DRIVE
 * @author Chris B., Sean L.
 */
public class auto_MMdriveTo288 extends Command {

	public static double driveFL, drivePL, driveIL, driveDL, driveFR, drivePR, driveIR, driveDR, rev,
			secondsFromNeutralToFull, angleDifference;
	public static int timeoutMs = 0;
	public static int slotIdx = 0;
	public static int sensorUnitsPer100ms, sensorUnitsPer100msPerSec;
	private static int time = 0;
	static double acceptableAngleError = 1;
	public VisionProcessing boilerVision = RobotMap.vision;
	static boolean angleDone;

	public auto_MMdriveTo288() {
		requires(Robot.chassis);
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// set this to distance in inchesS
		rev = Constants.EncoderTicksPerInch * 288;
		 System.out.println(rev);
		secondsFromNeutralToFull = .2;
		RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, timeoutMs);
		RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, timeoutMs);

		driveFR = 0.018; // 0.00425; //.01370574769317461; forward .17
		drivePR = 0.13;// .03; // forward .013
		driveIR = 0;
		driveDR = 2;
		RobotMap.chassis_rightFront.config_kF(slotIdx, driveFR, timeoutMs);
		RobotMap.chassis_rightFront.config_kP(slotIdx, drivePR, timeoutMs);
		RobotMap.chassis_rightFront.config_kI(slotIdx, driveIR, timeoutMs);
		RobotMap.chassis_rightFront.config_kD(slotIdx, driveDR, timeoutMs);

		driveFL = 0.017; // .005;
		drivePL = 0.13;// .03; // forward .013
		driveIL = 0;
		driveDL = 2;
		RobotMap.chassis_leftFront.config_kF(slotIdx, driveFL, timeoutMs);
		RobotMap.chassis_leftFront.config_kP(slotIdx, drivePL, timeoutMs);
		RobotMap.chassis_leftFront.config_kI(slotIdx, driveIL, timeoutMs);
		RobotMap.chassis_leftFront.config_kD(slotIdx, driveDL, timeoutMs);

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

		Robot.chassis.setBrakeMode(NeutralMode.Coast);

		RobotMap.chassis_leftFront.set(ControlMode.MotionMagic, rev);
		RobotMap.chassis_rightFront.set(ControlMode.MotionMagic, rev);
		setTimeout(5);
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
		// Robot.chassis.changeVbusToFollower(0);
		// Robot.chassis.setBrakeMode(NeutralMode.Brake);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
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
