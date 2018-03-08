package com.team2337.robot.commands.auto;

import com.team2337.fusion.wrappers.auto.AutoCommand;
import com.team2337.robot.RobotMap;


public class auto_resetEncoder extends AutoCommand {
	
	public auto_resetEncoder() {
	
	}

	protected void init() {
		 RobotMap.chassis_leftFront.setSelectedSensorPosition(0,0,0);
		 RobotMap.chassis_rightFront.setSelectedSensorPosition(0,0,0);
	}


	protected void execute() {
	}


	protected boolean isFinished() {
		return true;
	}

	protected void stop() {
		
		
	}


	protected void interrupted() {
		this.end();
	}
	
}
