/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.subsystems.Arm;

/**
 * An example command. You can replace me with your own command.
 */
public class arm_increaseAngle extends Command {
	public static double armAngle = 0;
	public double potValue;
	public static double armPosition; 
	boolean on = true;

	public arm_increaseAngle() {
		// Use requires() here to declare subsystem dependencies
		// requires();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		on = true;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		potValue = com.team2337.robot.commands.lifter.lifter_joystickControl.potValue;
		armPosition = RobotMap.arm_right.getSelectedSensorPosition(0);

		if (armPosition >= 944.44 && com.team2337.robot.commands.lifter.lifter_joystickControl.isAtTop == false) {
			System.out.println("IM INCREASING");
			this.end();
	}
		else if (armPosition < 2500) {
			if (com.team2337.robot.commands.lifter.lifter_joystickControl.isAtTop == true && on) {
				System.out.println("1");
				Arm.armAngle += 1;
			} else if (com.team2337.robot.commands.lifter.lifter_joystickControl.isAtTop == false
					&& armPosition < 944.44 
					&& on) {
				System.out.println("2");
				Arm.armAngle += 1;
				
			} else {
				System.out.println("3");
				Arm.armAngle += 0;
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		on = false;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		this.end();
	}
}
