package com.team2337.robot.commands.auto.commandGroups;

import com.team2337.robot.commands.auto.auto_bigBrother_DoNothing;
import com.team2337.robot.commands.auto.auto_wait;
import com.team2337.robot.commands.auto.lift.auto_LiftDown;
import com.team2337.robot.commands.auto.lift.auto_LiftUp;
import com.team2337.robot.commands.auto.lift.auto_liftStop;
import com.team2337.robot.commands.auto.upperPosition.auto_holdUpperPosition;
import com.team2337.robot.commands.auto.upperPosition.auto_moveUpperPosition;
import com.team2337.robot.commands.auto.upperPosition.auto_moveUpperPositionWithIsFinished;
import com.team2337.robot.commands.claw.claw_open;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scorePosition extends CommandGroup{
	public CG_scorePosition() {
		addParallel(new auto_bigBrother_DoNothing());
		addSequential(new auto_holdUpperPosition(0.1));
		//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
		
//		addParallel(new auto_driveToAngleWithEncoder(0.5, 2, 0, 28000, 28000, 0.03));	//Practice only***********************************************************
		
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
//		addParallel(new auto_driveForwardWithTime(-0.5, 1));		//Practice only***********************************************************
		addSequential(new auto_LiftDown(90, 300));
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,60)); //100 practice
		addSequential(new auto_wait(0.3));
		addSequential(new auto_liftStop());
		addSequential(new auto_moveUpperPositionWithIsFinished(3300,60)); //100 practice
		
	}
}
