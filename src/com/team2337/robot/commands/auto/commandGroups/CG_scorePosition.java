package com.team2337.robot.commands.auto;

import com.team2337.robot.commands.claw.claw_open;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scorePosition extends CommandGroup{
	public CG_scorePosition() {
		
		addParallel(new auto_driveToAngleWithEncoder(0.5, 2, 0, 34000, 34000, 0.06));	//Practice only***********************************************************
		
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
		addParallel(new auto_driveForwardWithTime(-0.5, 0.5));		//Practice only***********************************************************
		addSequential(new auto_LiftDown(90, 300));
		addSequential(new auto_moveUpperPosition(2850,60)); //100 practice
		addSequential(new auto_wait(0.3));
		addSequential(new auto_liftStop());
		addSequential(new auto_moveUpperPosition(3300,60)); //100 practice
		
	}
}
