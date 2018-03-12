package com.team2337.robot.commands.auto;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class auto_driveToAngleWithEncoderandLine extends Command {

	double speed, turn, Pgain, Dgain, MaxCorrectionRatio, targetAngle, timeout;
	int encoderLeft, encoderRight;
	
	public auto_driveToAngleWithEncoderandLine(double speed, double timeout, double angle, int encoderTargetLeft, int encoderTargetRight, double Pgain) {
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
    	//if (Robot.ourswitch.equals("R") || Robot.ourswitch.equals("r")) {
    		encoderRight = Math.abs(encoderLeft);
    	//}
    	setTimeout(timeout);
    }
    
    protected void execute() {
    	
    	double currentAngle = Pigeon.pidgey.getFusedHeading();
    	//double currentAngularRate = Robot.gyro.getAngularRate();
    	double forward = speed; 	
    	
    	turn = -((targetAngle - currentAngle) * Pgain );//- (currentAngularRate) * Dgain;

    	RobotMap.drive.arcadeDrive(forward, turn, false);

    }
	
	@Override
	protected boolean isFinished() {
		return (isTimedOut() || ((Math.abs(RobotMap.chassis_rightFront.getSelectedSensorPosition(0)) > encoderRight) &&   RobotMap.lineReader.get()));
		}
	
    protected void end() {
    	RobotMap.drive.arcadeDrive(0,0,true);
    }
    
    protected void interrupted() {
    	this.end();
    }
}
