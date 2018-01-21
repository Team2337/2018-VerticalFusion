/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.subsystems.Arm;

/**
 * An example command.  You can replace me with your own command.
 */
public class arm_decreaseAngle extends Command {
	public static double armAngle = 0;
	public double potValue;
	boolean on = true;
	
	public arm_decreaseAngle() {
		// Use requires() here to declare subsystem dependencies
		//requires();
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
		if(on && Arm.armAngle > 0) {
		if(com.team2337.robot.commands.lifter.lifter_joystickControl.isAtTop==false) {
			if(Arm.armAngle > 85) {
			Arm.armAngle -= 1;
			}
		}
		else if(com.team2337.robot.commands.lifter.lifter_joystickControl.isAtTop==true) {
			Arm.armAngle -=1;
		}
		else if(Arm.armAngle < 85) {
			Arm.armAngle -=1;
		}
		else {
			Arm.armAngle +=0;
		}
		}
		else this.end();
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
