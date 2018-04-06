package com.team2337.robot.commands.auto;

import com.team2337.robot.commands.claw.claw_open;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_ScaleScore extends CommandGroup{
	public CG_ScaleScore() {

//		addSequential(new auto_wait(0.3));
//		addSequential(new auto_moveUpperPositionWithIsFinished(2000,500,475)); //525, 500 practice
//		addSequential(new auto_moveUpperPositionWithIsFinished(2300,525)); //540 practice
		addSequential(new auto_LiftUp(580, 500)); //600, 500 practice
		addSequential(new auto_moveUpperPosition(1600, 525)); //540 practice
		addSequential(new auto_wait(0.3));

		addSequential(new claw_open());
		addSequential(new auto_wait(0.2));
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,500)); //525 practice
		addSequential(new auto_wait(0.4));
		
		addSequential(new auto_LiftDown(90, 300));
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,60)); //100 practice
		addSequential(new auto_wait(0.3));
		addSequential(new auto_liftStop());
		addSequential(new auto_moveUpperPositionWithIsFinished(3300,60)); //100 practice
		addSequential(new auto_wait(0.3));
		
		
		/*
		addSequential(new auto_resetEncoder());
		addSequential(new auto_clawOpen());
		addSequential (new auto_driveToAngleWithEncoder(-.5,10,-15,18000,18000,.2));
		*/
		
	}
}
