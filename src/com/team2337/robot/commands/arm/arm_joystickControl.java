/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * arm: JOYSTICKCONTROL - Moves the arm based joystick
 * 
 * @category arm
 * @author - Bryce
 */
public class arm_joystickControl extends Command {
	
	public boolean setPointSet = false;
	public static double armPositionValue;
	
	public arm_joystickControl() {
		requires(Robot.arm);
	}

	protected void initialize() {
		setPointSet = false; 
    	Robot.arm.disable();
	}

	protected void execute() {
		armPositionValue = Robot.oi.operatorJoystick.getRawAxis(4);
		double armJoystickX = Robot.oi.operatorJoystick.getRawAxis(4);
    	armJoystickX = -armJoystickX;
    	
    	//Check the joystick for a dead band, if in do...
    	if ((armJoystickX > -.1 ) && (armJoystickX < .1)) { //Dead band
    		
    		armJoystickX = 0;  //Set Motor to 0 if in dead band
    		//If setPointSet, is not set (so false), run this ONCE and
    		//enable the arm PID and set the PID to where the arm is
    		if (!setPointSet) {
    			Robot.arm.enable(); //Enable arm Pid
    			Robot.arm.setSetpoint(Robot.arm.getPosition()); //Set the arm
    			//Make setPointSet true so this statement true so it won't loop
    			setPointSet = true; 
    		}
    	} else {		//If the Joystick is out of the dead band, do..
    		Robot.arm.disable(); //Disable the arm PID
    		//Make the motor be controlled by the joystick but at a multiplied speed
    		if  ((armJoystickX > .1)) {
    			RobotMap.arm_right.set(ControlMode.PercentOutput, armJoystickX);
    			RobotMap.arm_left.set(ControlMode.PercentOutput, -armJoystickX);
    			//System.out.println("UP!");
    			
    		} 
    		else if (armJoystickX < -.1) {
    			RobotMap.arm_right.set(ControlMode.PercentOutput, armJoystickX);
    			RobotMap.arm_left.set(ControlMode.PercentOutput, -armJoystickX);
    			//System.out.println("HEY, This should be going down!");
    		}
    		else {
    			RobotMap.arm_right.set(ControlMode.PercentOutput, 0);
    			RobotMap.arm_left.set(ControlMode.PercentOutput, 0);
    			
    		}
    		//Make the setPointSet to false, so if in dead band, the PID can reset
    		setPointSet = false;
    	}	// End Deadband
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
