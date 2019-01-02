package com.team2337.robot.commands.auto;

import com.team2337.fusion.gyro.Pigeon;
import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Makes a U-Turn with the chassis 
 * 
 * @category AUTO-DRIVE
 * @author Bryce G., Sean L.
 */
public class auto_UTurn extends Command {

	double speedLeft, speedRight, turn, timeout, target;
	
	public auto_UTurn(double speedLeft, double speedRight, double timeout, double target) {
		requires(Robot.chassis);
		this.speedRight = speedRight;
		this.speedLeft = speedLeft;
		this.timeout = timeout;
		this.target = target;
	}

	protected void initialize() {
		setTimeout(timeout);
	}

	protected void execute() {
		RobotMap.drive.tankDrive(speedLeft, speedRight);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut() ;//|| Math.abs(Robot.gyro.getYaw()) > Math.abs(target);
	}

	protected void end() {
		//System.out.println(Robot.gyro.getYaw());
		//RobotMap.drive.arcadeDrive(.5, 0, true);
		
	}

	protected void interrupted() {
		this.end();
	}

}
