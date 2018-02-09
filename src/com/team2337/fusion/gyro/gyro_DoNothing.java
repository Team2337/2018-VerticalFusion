package com.team2337.fusion.gyro;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class gyro_DoNothing extends Command {

	
	public gyro_DoNothing() {
		requires(Robot.gyro);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
