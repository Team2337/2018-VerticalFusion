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
import com.team2337.robot.subsystems.PixyCam.*;

import com.team2337.robot.subsystems.PixyCam.NoTargetException;

public class chassis_PixyCamDrive extends Command {

	// Configurable parameters
	//public static final double ANGLE_PRECISION = 1.0;

	int pixyState;
	public static final int PIXY_READCAMERA = 1;
	public static final int PIXY_MOVING = 2;

	double MAX_TURN_ANGLE;
	double MIN_TURN_ANGLE;
	
	int targetSearchCount;
	int targetSearchDirection = 1;
	int targetSearchIncrement = 10;

	double turn, move, movement, moveSpeed;
	double angleX;
	
	private Joystick driverJoystick = Robot.oi.driverJoystick;
	
	public chassis_PixyCamDrive() {
		requires(Robot.pixyCam);
		requires(Robot.chassis);

		this.setInterruptible(true);
	}
	
	public void initialize() {
		pixyState = PIXY_READCAMERA;

	}
	
	public void execute() {

    	switch (pixyState) {
    	
    	case PIXY_READCAMERA:
    		try {
    			// Read PixyCam
    			angleX = readCamera();
    			pixyState = PIXY_MOVING;
    			targetSearchCount = 1;
        	} catch (NoTargetException ex) {
        		// No target found
        		SmartDashboard.putString("PIXY:", "No Power Cubes in sight.");
    			RobotMap.drive.arcadeDrive(0,0);
        		return;
        	}
    		break;
    		
    	case PIXY_MOVING:
    		try {
    			// Read PixyCam
    			angleX = readCamera();
        		SmartDashboard.putString("PIXY:", "Chasing a Power Cube!!!");
          	} catch (NoTargetException ex) {
        		// No target found
          		pixyState = PIXY_READCAMERA;
          		RobotMap.drive.arcadeDrive(0, 0);
          		return;
          	}	
			
    		
    		turn = angleX/10; ///////************************
    		moveSpeed = -driverJoystick.getRawAxis(1); //Left Y
    		RobotMap.drive.arcadeDrive(moveSpeed,turn);
    		break;
    	}

	}
	
	// This will throw a NoTargetException error if the camera cannot find the target
	private double readCamera() {

		double [] pixyData = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

		// Read PixyCam
		pixyData = Robot.pixyCam.getBasicPixyData();

		// Display the returned data
	   	SmartDashboard.putString("PIXY:","cX" + pixyData[0] +"cY = " + pixyData[1]);

   		// Return X angle
	   	return pixyData[0];
	}
	
	private void searchForTarget() {
		// Turn back and forth in ever increasing angles  until target is found
		int movement = targetSearchCount * targetSearchIncrement * targetSearchDirection ;
		double target = movement; // + gyro angle
		if (Math.abs(target) > MAX_TURN_ANGLE || Math.abs(target) < MIN_TURN_ANGLE) {
			target = 0.0;
			targetSearchCount = 1;
		}
		
		// Move turret 
    	//Robot.chassis.gyroTurn(target);
		
    	// Go the other way next time
    	targetSearchDirection *= -1.0;
    	targetSearchCount++;
	}
	
	protected boolean isFinished() {
		return false;
	}
	public void end() {
		RobotMap.drive.arcadeDrive(0,0);
	}
	protected void interrupted() {
		end();
	}

}
