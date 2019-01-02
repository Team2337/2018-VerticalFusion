package com.team2337.robot.commands.auto;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives the chassis motors to the desired angle using the pigeon and encoders
 * 
 * @category AUTO
 * @author Bryce G., Sean L.
 */
public class auto_driveToAngleWithEncoder2 extends Command {

	double speed, turn, Pgain, Dgain, MaxCorrectionRatio, targetAngle, timeout;
	int encoderLeft, encoderRight;
	
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
	public auto_driveToAngleWithEncoder2(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain) {
		requires(Robot.chassis);
		//Pgain = 0.04; /* percent throttle per degree of error */
		Dgain = 0.0004; /* percent throttle per angular velocity dps */
		MaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
		this.speed = speed;
	    this.timeout = timeout;
	    this.targetAngle = angle;
	    this.encoderLeft = encoderTargetLeft;
	    this.encoderRight = encoderTargetRight;
		this.Pgain = Pgain;
	}
	
    protected void initialize() {
    	if (Robot.ourswitch.equals("R") || Robot.ourswitch.equals("r")) {
    		encoderRight = Math.abs(encoderLeft);
    	}
    	setTimeout(timeout);
    }
    
    protected void execute() {
    	
    	double currentAngle = Robot.gyro.getYaw();
    	//double currentAngularRate = Robot.gyro.getAngularRate();
    	double forward = speed; 	
    	
    	turn = -((targetAngle - currentAngle) * Pgain );//- (currentAngularRate) * Dgain;

    	RobotMap.drive.arcadeDrive(forward, turn, false);

    }
	
	@Override
	protected boolean isFinished() {
		return (isTimedOut() || Math.abs(RobotMap.chassis_rightFront.getSelectedSensorPosition(0)) > Math.abs(encoderRight));
		}
	
    protected void end() {
    	RobotMap.drive.arcadeDrive(0,0,true);
    }
    
    protected void interrupted() {
    	this.end();
    }
}
