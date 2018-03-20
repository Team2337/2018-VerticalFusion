package com.team2337.robot.commands.auto;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.team2337.fusion.wrappers.auto.AutoCommand;
import com.team2337.robot.RobotMap;


public class auto_resetEncoder extends AutoCommand {
	
	public auto_resetEncoder() {
	
	}

	protected void init() {
		setTimeout(0.2);
		 
	}


	protected void execute() {
		RobotMap.chassis_leftFront.setSelectedSensorPosition(0,0,0);
		RobotMap.chassis_rightFront.setSelectedSensorPosition(0,0,0);
	}


	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void stop() {
		System.out.println("**********ENDED***********");
		
	}


	protected void interrupted() {
		this.end();
	}
	
}
