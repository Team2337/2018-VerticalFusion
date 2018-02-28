package com.team2337.robot.commands.claw;

import com.team2337.fusion.wrappers.commands.Action;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class claw_CGOpenClose extends Action {
	public claw_CGOpenClose() {
		addSequential(new claw_give60psi());
		addWait(0.05);
		addSequential(new claw_open());
		addWait(0.1);
		addSequential(new claw_give30psi());
		addWait(0.05);
		addSequential(new claw_close());
		}
}
