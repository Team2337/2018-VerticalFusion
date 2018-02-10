/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.trolley;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.Robot;
import com.team2337.robot.OI;
import com.team2337.robot.RobotMap;
import com.team2337.robot.subsystems.Arm;
import com.team2337.robot.subsystems.Trolley;

/**
 * Lifter: JOYSTICKCONTROL - Moves the lifter based joystick
 * 
 * @category LIFTER
 * @author - Bryce
 */
public class trolley_joystickControl3 extends Command {
	public boolean setPointSet = false;
	public static boolean isAtTop = false;
	public static double potValue;
	double trolleyJoystickY;
	
	public static TalonSRX frontRight = RobotMap.lift_right;
//	public static TalonSRX frontLeft = RobotMap.lift_leftFront;
	//public static TalonSRX backRight = RobotMap.lift_leftBack;
	
	
	public trolley_joystickControl3() {
		requires(Robot.trolley);
	}

	protected void initialize() {
		isAtTop = false;
		setPointSet = false; 
    	//Robot.trolley.disable();
    	//Lifter.setSoftLimits(5, -5);
	}

	protected void execute() {
		trolleyJoystickY = -Robot.oi.operatorJoystick.getRawAxis(1);
		
		SmartDashboard.putNumber("TROLLEYJOYSTICKVALUE", trolleyJoystickY);
		/*if(Arm.armAngle <= 85) {
		liftJoystickY = -liftJoystickY;
		}
		else if(Arm.armAngle >85 && Arm.armAngle <180) {
			liftJoystickY = +liftJoystickY;
		}*/
		
		//potValue = RobotMap.lift_potentiometer.get();
		/*
		if(potValue <= 1.1 && potValue > 1.025) {
			isAtTop = true; 
			
		}
		else if(potValue <= 1.0) {
			isAtTop = false; 
		}*/
    	//Check the joystick for a dead band, if in do...
    	if ((trolleyJoystickY > -.2 ) && (trolleyJoystickY < .2)) { 	//Dead band
    		
    		trolleyJoystickY = 0;  //Set Motor to 0 if in dead band
    		//If setPointSet, is not set (so false), run this ONCE and
    		//enable the Lift PID and set the PID to where the lift is
    		if (!setPointSet) {
    			//Robot.lifter.enable(); //Enable Lift Pid
    			//SmartDashboard.putNumber("selectedSensorPositionLIFTER", RobotMap.lift_rightFront.getSelectedSensorPosition(0));
    			//Robot.lifter.setSetpoint(RobotMap.lift_rightFront.getSelectedSensorPosition(0)); //Set the Lift
    			//Make setPointSet true so this statement true so it won't loop
    			setPointSet = true; 
    		}
    	} else {		//If the Joystick is out of the dead band, do..		
    		//Robot.trolley.disable(); //Disable the Lift PID
    		//Make the motor be controlled by the joystick but at a multiplied speed
    		if  ((trolleyJoystickY > .1)) {
    			RobotMap.lift_right.set(ControlMode.PercentOutput, trolleyJoystickY);
    			RobotMap.lift_left.set(ControlMode.PercentOutput, -trolleyJoystickY);
    			//System.out.println("UP!");
    			
    		} 
    		else if (trolleyJoystickY < -.1) {
    			RobotMap.lift_right.set(ControlMode.PercentOutput, trolleyJoystickY);
    			RobotMap.lift_left.set(ControlMode.PercentOutput, -trolleyJoystickY);
    			//System.out.println("HEY, This should be going down!");
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