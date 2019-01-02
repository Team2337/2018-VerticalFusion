package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_bootCamp extends CommandGroup {

	public CG_bootCamp() {	
	
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!

			
			
			/////Start here
			
//			addParallel(new auto_MMDriveRemote(xxx, 0));
//							addParallel(new auto_MMDriveRemote(70000, 0));
		
			/*
			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPositionWithIsFinished(2000, xxx, 500));
			addSequential(new auto_moveUpperPosition(xxx, 525));
			addSequential(new auto_LiftUp(xxx));
			
		
//			addSequential(new auto_wait(1));
//			addSequential(new auto_moveUpperPositionWithIsFinished(2000, 525, 500));
//			addSequential(new auto_moveUpperPosition(2300, 525));
//			addSequential(new auto_LiftUp(600));

			
			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPosition(xxx, 525));
//						addSequential(new auto_moveUpperPosition(1500, 525));
			addSequential(new auto_wait(1));
			addSequential(new auto_intakeOut(0.6, 1));
			
			
			
			addSequential(new auto_wait(.375));
			addSequential(new auto_moveUpperPosition(xxx, 525));
			addSequential(new auto_wait(1));
			addSequential(new auto_LiftDown(60, 200));
			addSequential(new auto_moveUpperPosition(2800, xxx));
			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPosition(xxx, 60));
			
			
//			addSequential(new auto_wait(.375));
//			addSequential(new auto_moveUpperPosition(2800, 525));
//			addSequential(new auto_wait(1));
//			addSequential(new auto_LiftDown(60, 200));
//			addSequential(new auto_moveUpperPosition(2800, 60));
//			addSequential(new auto_wait(1));
//			addSequential(new auto_moveUpperPosition(3300, 60));
			
			
//			base do not delete
			/*	   
			addParallel(new auto_MMDriveRemote(70000, 0));

			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPositionWithIsFinished(2000, 525, 500));
			addSequential(new auto_moveUpperPosition(2300, 525));
			addSequential(new auto_LiftUp(600));
			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPosition(1500, 525));
			addSequential(new auto_wait(1));
			addSequential(new auto_intakeOut(0.6, 1));
			addSequential(new auto_wait(.375));
			addSequential(new auto_moveUpperPosition(2800, 525));
			addSequential(new auto_wait(1));
			addSequential(new auto_LiftDown(60, 200));
			addSequential(new auto_moveUpperPosition(2800, 60));
			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPosition(3300, 60));
			*/
			
			/*
			addSequential(new auto_driveToAngleWithEncoder(.7, 4, 0, 65000, 65000, 0.04));// speed was .9 
			addSequential (new auto_brakeModeOn());
			addSequential(new auto_wait(1));
			addSequential(new commonCG_armToPickupFromFront());
			addSequential (new auto_brakeModeOff());
			*/
	
	}
}
