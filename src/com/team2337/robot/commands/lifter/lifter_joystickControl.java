/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.lifter;

import edu.wpi.first.wpilibj.command.Command;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Lifter: JOYSTICKCONTROL - Moves the lifter based joystick
 * 
 * @category LIFTER
 * @author - Bryce
 */
public class lifter_joystickControl extends Command {
	
	public boolean setPointSet = false;
	
	public lifter_joystickControl() {
		requires(Robot.lifter);
	}

	protected void initialize() {
		setPointSet = false; 
    	Robot.lifter.disable();
	}

	protected void execute() {
		double liftJoystickY = Robot.oi.operatorJoystick.getRawAxis(1);
		liftJoystickY = -liftJoystickY;
    	
    	//Check the joystick for a dead band, if in do...
    	if ((liftJoystickY > -.1 ) && (liftJoystickY < .1)) { 	//Dead band
    		
    		liftJoystickY = 0;  //Set Motor to 0 if in dead band
    		//If setPointSet, is not set (so false), run this ONCE and
    		//enable the Lift PID and set the PID to where the lift is
    		if (!setPointSet) {
    			Robot.lifter.enable(); //Enable Lift Pid
    			Robot.lifter.setSetpoint(Robot.lifter.getPosition()); //Set the Lift
    			//Make setPointSet true so this statement true so it won't loop
    			setPointSet = true; 
    		}
    	} else {		//If the Joystick is out of the dead band, do..		
    		Robot.lifter.disable(); //Disable the Lift PID
    		//Make the motor be controlled by the joystick but at a multiplied speed
    		if  ((liftJoystickY > .1)) {
    			RobotMap.lift_rightFront.set(ControlMode.PercentOutput, liftJoystickY);
    			RobotMap.lift_leftFront.set(ControlMode.PercentOutput, -liftJoystickY);
    			//System.out.println("UP!");
    			
    		} 
    		else if (liftJoystickY < -.1) {
    			RobotMap.lift_rightFront.set(ControlMode.PercentOutput, liftJoystickY);
    			RobotMap.lift_leftFront.set(ControlMode.PercentOutput, -liftJoystickY);
    			//System.out.println("HEY, This should be going down!");
    		}
    		else {
    			RobotMap.lift_rightFront.set(ControlMode.PercentOutput, 0);
    			RobotMap.lift_leftFront.set(ControlMode.PercentOutput, 0);
    			
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
