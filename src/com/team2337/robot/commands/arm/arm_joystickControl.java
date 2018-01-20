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
	
	public arm_joystickControl() {
		requires(Robot.arm);
	}

	protected void initialize() {
		setPointSet = false; 
    	Robot.arm.disable();
	}

	protected void execute() {
		double liftJoystickY = Robot.oi.operatorJoystick.getRawAxis(1);
    	liftJoystickY = -liftJoystickY;
    	
    	//Check the joystick for a dead band, if in do...
    	if ((liftJoystickY > -.1 ) && (liftJoystickY < .1)) { //Dead band
    		
    		liftJoystickY = 0;  //Set Motor to 0 if in dead band
    		//If setPointSet, is not set (so false), run this ONCE and
    		//enable the Lift PID and set the PID to where the lift is
    		if (!setPointSet) {
    			Robot.arm.enable(); //Enable Lift Pid
    			Robot.arm.setSetpoint(Robot.arm.getPosition()); //Set the Lift
    			//Make setPointSet true so this statement true so it won't loop
    			setPointSet = true; 
    		}
    	} else {		//If the Joystick is out of the dead band, do..
    		Robot.arm.disable(); //Disable the Lift PID
    		//Make the motor be controlled by the joystick but at a multiplied speed
    		if  ((liftJoystickY < 0) && (Robot.arm.getPosition() > 7.9)) {
    			RobotMap.lift_rightFront.set(ControlMode.PercentOutput, 0);
    			RobotMap.lift_leftFront.set(ControlMode.PercentOutput, 0);
    		} else if (liftJoystickY < 0) {
    			RobotMap.lift_rightFront.set(ControlMode.PercentOutput, 1.00 * liftJoystickY); //Positive
    			RobotMap.lift_leftFront.set(ControlMode.PercentOutput, 1.00 * liftJoystickY);

    		} else if ((Robot.arm.getPosition() > 1.249) && (liftJoystickY > 0)) {
    			RobotMap.lift_rightFront.set(ControlMode.PercentOutput, 1.00 * liftJoystickY);	//Negative
    			RobotMap.lift_leftFront.set(ControlMode.PercentOutput, 1.00 * liftJoystickY);

    		} else  {
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
