package com.team2337.robot.commands.auto;

import com.team2337.robot.commands.claw.claw_open;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_crossFirstScorePosition extends CommandGroup{
	public CG_crossFirstScorePosition() {
		
		addSequential(new auto_resetEncoder());
		addParallel (new auto_driveToAngleWithEncoderandLine(.3,1.5,18,20000,20000,.03));
		
		addSequential(new auto_wait(0.3));
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,500,475)); //525, 500 practice
		addSequential(new auto_moveUpperPositionWithIsFinished(2300,525)); //540 practice
		addSequential(new auto_LiftUp(580, 500)); //600, 500 practice
		addSequential(new auto_moveUpperPosition(1850, 525)); //540 practice
		addSequential(new auto_wait(0.3));
//			addSequential(new auto_intakeOut(0.4, 0.4));
		addSequential(new claw_open());
		addSequential(new auto_wait(0.2));
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,500)); //525 practice
		addSequential(new auto_wait(0.4));
		addParallel(new auto_driveForwardWithTime(-0.5, 1));		//Practice only***********************************************************
		addSequential(new auto_LiftDown(90, 300));
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,60)); //100 practice
		addSequential(new auto_wait(0.3));
		addSequential(new auto_liftStop());
		addSequential(new auto_moveUpperPositionWithIsFinished(3300,60)); //100 practice
		
	}
}
