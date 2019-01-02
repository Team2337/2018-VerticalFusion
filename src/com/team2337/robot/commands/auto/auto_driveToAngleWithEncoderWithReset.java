package com.team2337.robot.commands.auto;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives the chassis to a desired angle using the gyro and encoders. Afterwards, reset the encoders
 * This command also consists of a few experimental constrctors 
 * Use auto_driveToAnlgeWithEncoder instead 
 * 
 * @category AUTO-DRIVE
 * @author Bryce G., Sean L.
 */
public class auto_driveToAngleWithEncoderWithReset extends Command {

	double speed, turn, Pgain, Dgain, MaxCorrectionRatio, targetAngle, timeout, isFinishedPos;
	int encoderLeft, encoderRight,encoderEnd;
	boolean isFinishedSide = false;
	boolean change = false;
	String changeScaleEnc = "false";
	
	/**
	 * This command drives the chassis motors, using a PID, towards a given setpoint
	 * @param speed
	 * Percent of power output on the motor
	 * @param timeout
	 * The amount of time the command is allowed the run until it is forced to terminate
	 * @param angle
	 * The desired angle the robot should be at, at the end of the command
	 * @param encoderTargetLeft
	 * The destination of the Left drive encoder
	 * @param encoderTargetRight
	 * The destination of the Right drive encoder
	 * @param Pgain
	 * The P given to the drive 
	 */
	public auto_driveToAngleWithEncoderWithReset(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain) {
		requires(Robot.chassis);
		Dgain = 0.0004; /* percent throttle per angular velocity dps */
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
	    this.encoderLeft = encoderTargetLeft;
	    this.encoderRight = encoderTargetRight;
		this.Pgain = Pgain;
	}
	
	public auto_driveToAngleWithEncoderWithReset(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain, boolean change) {
		requires(Robot.chassis);
		Dgain = 0.0004; /* percent throttle per angular velocity dps */
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
	    this.encoderLeft = encoderTargetLeft;
	    this.encoderRight = encoderTargetRight;
		this.Pgain = Pgain;
		this.change = change;
	}
	public auto_driveToAngleWithEncoderWithReset(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain, boolean change, double Dgain) {
		requires(Robot.chassis);
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
	    this.encoderLeft = encoderTargetLeft;
	    this.encoderRight = encoderTargetRight;
		this.Pgain = Pgain;
		this.change = change;
		this.Dgain = Dgain;
	}
	
	/**
	 * This command drives the chassis motors, using a PID, towards a given setpoint
	 * @param speed
	 * Percent of power output on the motor
	 * @param timeout
	 * The amount of time the command is allowed the run until it is forced to terminate
	 * @param angle
	 * The desired angle the robot should be at, at the end of the command
	 * @param encoderTargetLeft
	 * The destination of the Left drive encoder
	 * @param encoderTargetRight
	 * The destination of the Right drive encoder
	 * @param Pgain
	 * The P given to the drive 
	 * @param change
	 * Chnages the drive encoder side that is being read 
	 * Input "true" inverts the side being read
	 * @param Dgain
	 * Dampens the power on the motor to reduce occilation when the encoders nearthe set point 
	 * @param changeScaleEnc
	 * Decides which drive encoder is read
	 * Input "Right" or "Left" to decide what side to read
	 */
	public auto_driveToAngleWithEncoderWithReset(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain, boolean change, double Dgain, String changeScaleEnc) {
		requires(Robot.chassis);
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
	    this.encoderLeft = encoderTargetLeft;
	    this.encoderRight = encoderTargetRight;
		this.Pgain = Pgain;
		this.change = change;
		this.Dgain = Dgain;
		this.changeScaleEnc = changeScaleEnc;
	}
	
	public auto_driveToAngleWithEncoderWithReset(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain, String changeScaleEnc) {
		requires(Robot.chassis);
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
	    this.encoderLeft = encoderTargetLeft;
	    this.encoderRight = encoderTargetRight;
		this.Pgain = Pgain;
		this.changeScaleEnc = changeScaleEnc;
	}
	
    protected void initialize() {
    	if (Robot.ourswitch.equals("R") || Robot.ourswitch.equals("r") ) {
    		isFinishedSide = true;
   		}
   		setTimeout(timeout);
   		
   		if(change) {
   			isFinishedSide = !isFinishedSide;
   		}
   		
   		if (changeScaleEnc.equals("Right") && Robot.ourswitch.equals("L")) {
   			isFinishedSide = false;  //Read Right Encoder
   		}
   		if (changeScaleEnc.equals("Left") && Robot.ourswitch.equals("R")) {
   			isFinishedSide = true;  // Read Left Encoder
   		}
    }
    
    
    
    protected void execute() {
    	double currentAngle = Robot.gyro.getYaw();
//    	System.out.println(isFinishedPos + "  " + encoderRight + "  " + encoderLeft + "  " + currentAngle);
    	double currentAngularRate = Robot.gyro.getAngularRate();
    	double forward = speed; 	
    	if((targetAngle - currentAngle) > 75) {
    		turn = -((targetAngle - currentAngle) * Pgain) - (currentAngularRate) * Dgain;
    	} else {
    		turn = -((targetAngle - currentAngle) * Pgain);
    	}

    	RobotMap.drive.arcadeDrive(forward, turn, false);
    	if(isFinishedSide) {
    		//Read left side encoder
    		isFinishedPos = Math.abs(RobotMap.chassis_leftFront.getSelectedSensorPosition(0));
    		encoderEnd = Math.abs(encoderLeft);
    	} else {
    		//Read right side encoder
    		isFinishedPos = Math.abs(RobotMap.chassis_rightFront.getSelectedSensorPosition(0));
    		encoderEnd = Math.abs(encoderRight);
    		
    	}
//    	System.out.println(isFinishedPos);

    }
	
	@Override
	protected boolean isFinished() {
		return (isTimedOut() || isFinishedPos > Math.abs(encoderEnd));
		}
	
    protected void end() {
    	RobotMap.drive.arcadeDrive(0,0,true);
    	System.out.println(isFinishedPos);
    	RobotMap.chassis_leftFront.setSelectedSensorPosition(0,0,0);
		RobotMap.chassis_rightFront.setSelectedSensorPosition(0,0,0);
    }
    
    protected void interrupted() {
    	this.end();
    }
}
