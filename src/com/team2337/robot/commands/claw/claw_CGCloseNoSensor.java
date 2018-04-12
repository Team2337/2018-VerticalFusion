package com.team2337.robot.commands.claw;

import com.team2337.fusion.wrappers.commands.Action;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class claw_CGCloseNoSensor extends Action {
	public claw_CGCloseNoSensor() {
		addSequential(new claw_close());
		addWait(0.25);
		addSequential(new claw_give60psi());
	}
}
