package com.team2337.robot.commands.auto;

import com.team2337.fusion.wrappers.command.action.Action;
import com.team2337.fusion.wrappers.command.auto.AutoCommandManager;
import com.team2337.fusion.wrappers.command.auto.Wait;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.chassis.chassis_moveForward;
import com.team2337.robot.commands.intake.intake_in;

public class auto_ActionColorExample extends Action {
	public auto_ActionColorExample() {
		AutoCommandManager.getInstance().setBlinkin(RobotMap.blinkin);
		AutoCommandManager.getInstance().reset();
		
		addSequential(new chassis_moveForward(3), 3);
		addSequential(new chassis_moveForward(3), 3);
		addParallel(new Wait(3), 3);
		addSequential(new chassis_moveForward(3), 3);
		addSequential(new chassis_moveForward(3), 3);
		addSequential(new chassis_moveForward(3), 3);
		addSequential(new chassis_moveForward(3), 3);
		addSequential(new chassis_moveForward(3), 3);
		addSequential(new chassis_moveForward(3), 3);
		addWait(10);
	}
}
