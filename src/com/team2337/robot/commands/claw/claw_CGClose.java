package com.team2337.robot.commands.claw;

import com.team2337.fusion.wrappers.commands.Action;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class claw_CGClose extends Action {
	public claw_CGClose() {
//		if(RobotMap.crateSensor.get()) {
		addSequential(new claw_give60psi());
		addWait(0.5);
		addSequential(new claw_give30psi());
		addWait(0.5);
//		}
		addSequential(new claw_close());
	}
}
