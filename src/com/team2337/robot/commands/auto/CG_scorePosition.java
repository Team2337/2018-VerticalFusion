package com.team2337.robot.commands.auto;

import com.team2337.robot.commands.claw.claw_open;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scorePosition extends CommandGroup{
	public CG_scorePosition() {
		addParallel(new auto_bigBrother_DoNothing());
		addSequential(new auto_holdUpperPosition(0.1));
		//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
		addParallel(new auto_driveToAngleWithEncoder(0.5, 2, 0, 28000, 28000, 0.03));
		
		addSequential(new auto_wait(0.3));
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,525,500));
		addSequential(new auto_moveUpperPositionWithIsFinished(2300,540));
		addSequential(new auto_LiftUp(600, 500));
		addSequential(new auto_moveUpperPosition(1850, 540));
		addSequential(new auto_wait(0.3));
//			addSequential(new auto_intakeOut(0.4, 0.4));
		addSequential(new claw_open());
		addSequential(new auto_wait(0.2));
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,525));
		addSequential(new auto_wait(0.4));
//		addParallel(new auto_driveToAngleWithEncoder(-0.5, 2, 0, 0, 0, 0.02));
		addParallel(new auto_driveForwardWithTime(-0.5, 1));
		addSequential(new auto_LiftDown(90, 300));
		addSequential(new auto_moveUpperPositionWithIsFinished(2850,100));
		addSequential(new auto_wait(0.3));
		addSequential(new auto_liftStop());
		addSequential(new auto_moveUpperPositionWithIsFinished(3300,100));
		
	}
}
