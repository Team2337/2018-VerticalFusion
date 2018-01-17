package com.team2337.robot.commands.chassis;

//import org.usfirst.frc3467.robot.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.RobotMap.*;
import com.team2337.fusion.drive.*;
import com.team2337.robot.subsystems.Pixy.*;

import com.team2337.robot.subsystems.PixyCam.NoTargetException;

public class chassis_PixyCamDrive extends Command {

	// Configurable parameters
	public static final double ANGLE_PRECISION = 1.0;
	
	boolean targetFound = false;
	int targetSearchCount = 1;
	int targetSearchDirection = 1;
	int targetSearchIncrement = 10;
	
	int aimState;
	public static final int AIM_READCAMERA = 1;
	public static final int AIM_MOVING = 2;
	
	public chassis_PixyCamDrive() {
		requires(Robot.pixyCam);
		requires(Robot.chassis);

		this.setInterruptible(true);
	}
	
	public void initialize() {
		targetFound = false;
		aimState = AIM_READCAMERA;
		SmartDashboard.putString("ShooterTurret", "Auto Aim");
	}
	
	public void execute() {

    	double angleX;

    	switch (aimState) {
    	
    	case AIM_READCAMERA:
    		try {
    			// Read PixyCam
    			angleX = readCamera();
        	} catch (NoTargetException ex) {
        		// No target found
        		searchForTarget();
        		aimState = AIM_MOVING;
        		return;
        	}

    		// Command is completed if we are on target in the X direction
    		if (Math.abs(angleX) <= ANGLE_PRECISION) {
    			targetFound = true;
    		}
    		else
    		{
        		// Start moving turret to center of object
            	shooterTurret.setDesiredAngle(shooterTurret.getAngle() + angleX);
            	aimState = AIM_MOVING;
    		}
    		break;

    	case AIM_MOVING:
    		if (shooterTurret.isOnTarget()) {
    			// Finished movement - read camera again
    			aimState = AIM_READCAMERA;
    		}
    		break;
    	}

	}
	
	// This will throw a NoTargetException error if the camera cannot find the target
	private double readCamera() {

		double [] pixyData = {0.0, 0.0, 0.0};

		// Read PixyCam
		pixyData = pixyCamShooter.getBoilerLocationData();

		// Display the returned data
	   	SmartDashboard.putString("Turret AutoAim","distance = " + pixyData[0] +"   angleX = " + pixyData[1] + "   angleY = "+ pixyData[2]);

   		// Return X angle
	   	return pixyData[1];
	}
	
	private void searchForTarget() {
		// Turn back and forth in ever increasing angles  until target is found
		int movement = targetSearchCount * targetSearchIncrement * targetSearchDirection ;
		double target = shooterTurret.getAngle() + movement;
		if (target > ShooterTurret.SOFT_MAX_TURRET_ANGLE || target < ShooterTurret.SOFT_MIN_TURRET_ANGLE) {
			target = 0.0;
			targetSearchCount = 1;
		}
		
		// Move turret 
    	shooterTurret.setDesiredAngle(target);
		
    	// Go the other way next time
    	targetSearchDirection *= -1.0;
    	targetSearchCount++;
	}
	
	protected boolean isFinished() {
		return targetFound;
	}
	
	public void end() {
		shooterTurret.runTurret(0.0);
		
	}
	
	protected void interrupted() {
		end();
	}

}
