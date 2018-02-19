package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scorePosition extends CommandGroup{
	public CG_scorePosition() {
		addSequential(new auto_LiftUp(3, 20));
		addSequential(new auto_moveUpperPosition(-135, 560));
	}
}
