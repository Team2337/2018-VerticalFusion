package com.team2337.robot.commands.auto;



import com.team2337.fusion.vision.VisionProcessing;
import com.team2337.robot.RobotMap;
import com.team2337.robot.Robot;
import com.team2337.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class auto_gyroMMTurn extends Command {

    public static double driveF, driveP, driveI, driveD, rev;
	public static int timeoutMs = 0;
	public static int slotIdx = 0;
	public static int sensorUnitsPer100ms, sensorUnitsPer100msPerSec;
    public VisionProcessing boilerVision = RobotMap.vision;
   
    public auto_gyroMMTurn() {
        requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, timeoutMs);
    	RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, timeoutMs);
        driveF = 0.1420574769317461;
        driveP = 0.01136; //.0077;//0.04508;
        driveI = 0;
        driveD = 0;
    	RobotMap.chassis_rightFront.config_kF(slotIdx, driveF, timeoutMs); 
    	RobotMap.chassis_rightFront.config_kP(slotIdx, driveP, timeoutMs); 
    	RobotMap.chassis_rightFront.config_kI(slotIdx, driveI, timeoutMs);
    	RobotMap.chassis_rightFront.config_kD(slotIdx, driveD, timeoutMs);
    	
    	RobotMap.chassis_leftFront.config_kF(slotIdx, driveF, timeoutMs);
    	RobotMap.chassis_leftFront.config_kF(slotIdx, driveP, timeoutMs);
    	RobotMap.chassis_leftFront.config_kF(slotIdx, driveI, timeoutMs);
    	RobotMap.chassis_leftFront.config_kF(slotIdx, driveD, timeoutMs);
    	
    	sensorUnitsPer100ms = 3000; //Velocity
    	sensorUnitsPer100msPerSec = 2000; //Acceleration
    	
		RobotMap.chassis_leftFront.configMotionCruiseVelocity(sensorUnitsPer100ms, timeoutMs);  //75% of 937
		RobotMap.chassis_rightFront.configMotionCruiseVelocity(sensorUnitsPer100ms, timeoutMs);
		
		RobotMap.chassis_leftFront.configMotionAcceleration(sensorUnitsPer100msPerSec, timeoutMs);
		RobotMap.chassis_rightFront.configMotionAcceleration(sensorUnitsPer100msPerSec, timeoutMs);
		
		Robot.chassis.setBrakeMode(NeutralMode.Coast);
    	//rev = Constants.kTargetingCamera_RevDegree * Constants.kAuton_TurnDegreeRed;
    	rev = 50000;
    	System.out.println(rev);
    	Robot.chassis.setMotionMagicTurn(rev);
		setTimeout(6);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(rev);
		
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut(); //|| getBetween(rev, RobotMap.chassis_leftFront.getPosition(), 0.002) || Robot.oi.getOperatorControls().getRawButton(3);
    }

    // Called once after isFinished returns true
    protected void end() {

	 	Robot.chassis.changeVbusToFollower(0);
    	Robot.chassis.setBrakeMode(NeutralMode.Brake);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
    
    private boolean getBetween(double constant, double input, double deadband) {
    	if (input >= (constant - deadband)  && input <= (constant + deadband)) {
    		return true;
    	}
    	return false; 	
    }

}
