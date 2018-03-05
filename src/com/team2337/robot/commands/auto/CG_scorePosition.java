package com.team2337.robot.commands.auto;

import com.team2337.robot.commands.claw.claw_open;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scorePosition extends CommandGroup{
	public CG_scorePosition() {
		addParallel(new auto_bigBrother_DoNothing());
		addSequential(new auto_holdUpperPosition(0.1));
		//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
		addSequential(new auto_moveUpperPositionWithIsFinished(2127,500,480));
		addSequential(new auto_wait(0.1));
		addSequential(new auto_LiftUp(300, 280));
		addSequential(new auto_moveUpperPosition(1790, 525));
		addSequential(new auto_wait(0.5));
		addSequential(new auto_intakeOut(0.8, 0.25));
		addSequential(new claw_open());
		addSequential(new auto_wait(0.2));
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,525));
		addSequential(new auto_wait(0.5));
		addSequential(new auto_LiftUp(90));
		addSequential(new auto_wait(0.28));
		addSequential(new auto_liftStop());
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,100));
		addSequential(new auto_wait(0.3));
		addSequential(new auto_moveUpperPositionWithIsFinished(3300,100));
	}
}
