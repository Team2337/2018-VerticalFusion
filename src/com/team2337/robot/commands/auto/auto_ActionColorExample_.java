package com.team2337.robot.commands.auto;

import com.team2337.fusion.wrappers.command.action.ActionColor;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.chassis.chassis_moveForward;


public class auto_ActionColorExample_ extends ActionColor {
	public auto_ActionColorExample_() {
		setBlinkIn(RobotMap.blinkin);
		newSequential(new chassis_moveForward(3), 3);
		newSequential(new chassis_moveForward(3), 3);
		addWait(10);
	}
}
