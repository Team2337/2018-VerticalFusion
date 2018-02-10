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
public class arm_joystickControl2 extends Command {

	boolean setPointSet = false;
	double armPositionValue = 0;
	double armPositionEncoder;
	double stringPot;
	boolean isAtTop;

	double armJoystickX;

	public arm_joystickControl2() {
		requires(Robot.arm);
	}

	protected void initialize() {
		setPointSet = false;
		Robot.arm.disable();
		Arm.setSoftLimits(968, 1);
	}

	protected void execute() {
		armPositionEncoder = RobotMap.arm_right.getSelectedSensorPosition(0);

		SmartDashboard.putBoolean("armSetSetpoint", setPointSet);
		SmartDashboard.putNumber("PIDArmPosition", RobotMap.arm_right.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("selectedSensorPosition", armPositionEncoder);
		SmartDashboard.putNumber("ArmJoystickValue", armJoystickX);

		armJoystickX = -Robot.oi.operatorJoystick.getRawAxis(5);

		stringPot = Math.abs(RobotMap.lift_right.getSelectedSensorPosition(0));

		if ((armJoystickX > -.2) && (armJoystickX < .2)) {

			armJoystickX = 0;

			if (!setPointSet) {
				Robot.arm.enable();
				Robot.arm.setSetpoint(RobotMap.arm_right.getSelectedSensorPosition(0));

				SmartDashboard.putNumber("PIDSetPosition", Robot.arm.getPosition());

				setPointSet = true;
			}
		} else {
			Robot.arm.disable();
			SmartDashboard.putNumber("StringPotValueInArm", stringPot);

			if ((armJoystickX > 0)) {
				if (stringPot > 1.0) {
					Arm.setSoftLimits(2560, 1);
					Arm.moveForward(armJoystickX);
				} else if (stringPot > 0.7 && stringPot < 1.0) {
					if (armPositionEncoder > 1080) {
						Arm.setSoftLimits(1080, 1);
						Arm.moveForward(armJoystickX);
					} else if (armPositionEncoder < 968) {
						Arm.setSoftLimits(968, 1);
						Arm.moveForward(armJoystickX);
					}
				}

			} else {
				if (stringPot > 0.7) {
					Arm.setSoftLimits(2560, 1);
					Arm.moveBackward(armJoystickX);
				} else if (stringPot > 0.7 && stringPot < 1.0) {
					if (armPositionEncoder > 1080) {
						Arm.setSoftLimits(1080, 1);
						Arm.moveBackward(armJoystickX);
					} else if (armPositionEncoder < 968) {
						Arm.setSoftLimits(968, 1);
						Arm.moveBackward(armJoystickX);
					}
				}
				

			}

			setPointSet = false;
		}
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
