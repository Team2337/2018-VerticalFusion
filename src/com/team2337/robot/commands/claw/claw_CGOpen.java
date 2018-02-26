package com.team2337.robot.commands.claw;

import com.team2337.fusion.wrappers.commands.Action;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class claw_CGOpen extends Action {
	public claw_CGOpen() {
		addSequential(new claw_give60psi());
		addWait(0.3);
		addSequential(new claw_open());
		addWait(0.3);
		addSequential(new claw_openHeld());
	}
}
