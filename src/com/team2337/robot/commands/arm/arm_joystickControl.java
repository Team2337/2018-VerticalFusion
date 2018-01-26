/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.subsystems.Arm;

/**
 * arm: JOYSTICKCONTROL - Moves the arm based joystick
 * 
 * @category arm
 * @author - Bryce
 */
public class arm_joystickControl extends Command {

	boolean setPointSet = false;
	double armPositionValue = 0;
	double armPositionEncoder;
	boolean isAtTop;

	public arm_joystickControl() {
		requires(Robot.arm);
	}

	protected void initialize() {
		setPointSet = false;
		Robot.arm.disable();
		Arm.setSoftLimits(1080, 1);
	}

	protected void execute() {
		armPositionEncoder = RobotMap.arm_right.getSelectedSensorPosition(0);
		isAtTop = com.team2337.robot.commands.lifter.lifter_joystickControl.isAtTop;
		
		SmartDashboard.putNumber("PIDArmPosition", Robot.arm.getPosition());

		// armPositionValue = Robot.oi.operatorJoystick.getRawAxis(5);
		double armJoystickX = Robot.oi.operatorJoystick.getRawAxis(5);
		armJoystickX = -armJoystickX;

		// Check the joystick for a dead band, if in do...
		if ((armJoystickX > -.2) && (armJoystickX < .2)) { // Dead band
			//Robot.arm.enable();
			armJoystickX = 0; // Set Motor to 0 if in dead band
			// If setPointSet, is not set (so false), run this ONCE and
			// enable the arm PID and set the PID to where the arm is
			if (!setPointSet) {
    			Robot.arm.enable(); //Enable Lift Pid
    			Robot.arm.setSetpoint(Robot.arm.getPosition()); //Set the arm
    			//Make setPointSet true so this statement true so it won't loop
    			setPointSet = true; 
			}
		} else { // If the Joystick is out of the dead band, do..
			Robot.arm.disable(); // Disable the arm PID
			// Make the motor be controlled by the joystick but at a multiplied speed
			if ((armJoystickX > 0)) {
				Arm.moveForward(armJoystickX);
				//Arm.setSoftLimits(1080, 1);
				/*if (armPositionEncoder <= 2500 && !isAtTop && armPositionValue >= 0 && armPositionValue < 225) {
					Arm.setSoftLimits(968, 0);
					
					RobotMap.arm_right.set(ControlMode.PercentOutput, armJoystickX);
					RobotMap.arm_left.set(ControlMode.PercentOutput, -armJoystickX);
					armPositionValue++;
					System.out.println("UP!");
				} else if (armPositionEncoder <= 2500 && isAtTop && armPositionValue >= 0 && armPositionValue < 225) {
					RobotMap.arm_right.set(ControlMode.PercentOutput, armJoystickX);
					RobotMap.arm_left.set(ControlMode.PercentOutput, -armJoystickX);
					
					Arm.setSoftLimits(2560, 0);
					armPositionValue++;
					System.out.println("UP!Lift SetPoint");
				} /*else {
					Arm.setSoftLimits(2560, 0);
					System.out.println("Stop");
				}*/

			} else if (armJoystickX < 0) {
				Arm.moveBackward(armJoystickX);
				//Arm.setSoftLimits(1080, 1);
				/* if (armPositionEncoder <= 2560 && armPositionEncoder > 0 && armPositionValue <= 225 && armPositionValue >0) {
					if (isAtTop) {
						Arm.setSoftLimits(2560, 0);
						armPositionValue--;
						System.out.println("DOWN At Top");
					} else {
						if(armPositionEncoder >1080 && armPositionValue <= 225 && armPositionValue > 0) {
						Arm.setSoftLimits(2560, 1810);
						armPositionValue--;
						System.out.println("DOWN! Not At Top");
						}
						else if(armPositionEncoder < 968 && armPositionValue <= 225 && armPositionValue > 0) {
							Arm.setSoftLimits(968, 0);
							armPositionValue--;
						}
					}
				}*/
			} else {
				RobotMap.arm_right.set(ControlMode.PercentOutput, 0);
				RobotMap.arm_left.set(ControlMode.PercentOutput, 0);

			}
			// Make the setPointSet to false, so if in dead band, the PID can reset
			setPointSet = false;
		} // End Deadband
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
