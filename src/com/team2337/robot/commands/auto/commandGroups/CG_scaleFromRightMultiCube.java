package com.team2337.robot.commands.auto;


import com.team2337.robot.commands.claw.claw_open;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scaleFromRightMultiCube extends CommandGroup {

	String ourSwitch, scale;
	public CG_scaleFromRightMultiCube(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		if (scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			///////         First Cube
			addSequential (new auto_driveToAngleWithEncoder(.7,10,0,80000,80000,.13));
			addParallel (new CG_ArmToTop());  //Arm to top to
			addSequential (new auto_driveToAngleWithEncoder(.6,10,9,148000,148000,.06));

			addParallel(new CG_ScaleScore()); //Score first cube
			addSequential (new auto_driveToAngleWithEncoderandLine(.3,.8,18,160000,160000,.03)); 
			addSequential (new auto_brakeModeOn());
			
			///delay to allow arm to come to ground
			addSequential(new auto_wait(2.3));
			addSequential(new auto_resetEncoder());
			addSequential(new auto_clawOpen());
			// Drive to second cube
			addSequential(new auto_driveToAngleWithEncoder(-.7,10,-10,30000,30000,.06));
			
			/*
			addSequential(new auto_LiftDown(90, 300));
			addSequential(new auto_moveUpperPositionWithIsFinished(2850,60)); //100 practice
			addSequential(new auto_wait(0.3));
			addSequential(new auto_liftStop());
			addSequential(new auto_moveUpperPositionWithIsFinished(3300,60)); //100 practice
			addSequential(new auto_wait(0.3));
			*/
			
			addSequential(new auto_wait(0.2));
			addParallel(new auto_intake_in(1, 3));
			addSequential(new auto_wait(.1));
			addSequential(new auto_clawClose());
			addSequential(new auto_claw60());
			addSequential(new auto_resetEncoder());
			addSequential(new CG_scorePosition());  //score second cube
			


			
			//grab 3rd cube
			addSequential(new auto_resetEncoder());
			addSequential(new auto_clawOpen());
			//drive to third cube
			addSequential(new auto_driveToAngleWithEncoder(-.6,10,-35,26000,26000,.045));
			addSequential(new auto_wait(0.2));
			addParallel(new auto_intake_in(1, 4));
			addSequential(new auto_wait(.8));
			addSequential(new auto_clawClose());
			addSequential(new auto_claw60());
			addSequential(new auto_resetEncoder());
			
			addSequential(new auto_driveToAngleWithEncoder(.6,10,-35,26000,26000,.045));
			addSequential(new auto_driveToAngleWithEncoder(.6,10,0,35000,35000,.045));
			
		//Switch is on our side scale is  RLR
		//  We should never get here as it is covered in the first if, but if we diverge....	
		} else if (scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential (new auto_driveToAngleWithEncoder(.8, 4, 0, 118000, 118000, 0.04, true, 0.02, "Right"));// speed was .7 //115000
			addSequential (new auto_brakeModeOn());
			addSequential (new auto_driveToAngleWithEncoder(.6, 4, 90, 200000, 200000, 0.032, true, 0.02, "Right"));// speed was .5 
			addParallel (new CG_ArmToTop());
			addSequential (new auto_driveToAngleWithEncoder(.6, 4, 90, 260000, 260000, 0.04, true, 0.02, "Right"));// speed was .6 //282000
			addSequential (new auto_driveToAngleWithEncoder(.5, 4, 0, 245000, 245000, 0.03, true, 0.02, "Left"));// speed was .5 //276000
			
			
			addSequential(new auto_LiftUp(580, 500)); //600, 500 practice
			addSequential(new auto_moveUpperPosition(1850, 525)); //540 practice
			addSequential(new auto_resetEncoder());
			addSequential(new auto_wait(0.2));
			addSequential(new claw_open());
			addSequential(new auto_wait(0.2));
			addSequential(new auto_driveForwardWithTime(-0.5, .2));

			addSequential(new auto_moveUpperPositionWithIsFinished(2850,500)); //525 practice
			addSequential(new auto_wait(0.4));
			addSequential(new auto_LiftDown(90, 300));
			addSequential(new auto_moveUpperPosition(2850,60)); //100 practice
			addSequential(new auto_wait(0.3));
			addSequential(new auto_liftStop());
			addSequential(new auto_moveUpperPosition(3300,60)); //100 practice
		
			addParallel (new auto_driveToAngleWithEncoder(-.5, 4, 5, 30000, 30000, 0.04, true));// speed was .9 

			addSequential(new auto_wait(0.2));
			addParallel(new auto_intake_in(1, 3.2));
			addSequential(new auto_wait(1.3));
			addSequential(new auto_clawClose());
			addSequential(new auto_claw60());
			addSequential(new auto_wait(.2));
			addSequential(new auto_resetEncoder());
			
			
			addParallel(new auto_driveToAngleWithEncoder(0.5, .9, -8, 29000, 29000, 0.06));
			
			addSequential(new auto_wait(0.3));
			addSequential(new auto_moveUpperPositionWithIsFinished(2850,500,475)); //525, 500 practice
			addSequential(new auto_moveUpperPositionWithIsFinished(2300,525)); //540 practice
			addSequential(new auto_LiftUp(580, 500)); //600, 500 practice
			addSequential(new auto_moveUpperPosition(1850, 525)); //540 practice
			addSequential(new auto_wait(0.3));
//				addSequential(new auto_intakeOut(0.4, 0.4));
			addSequential(new claw_open());
			addSequential(new auto_wait(0.2));
			addSequential(new auto_moveUpperPositionWithIsFinished(2850,500)); //525 practice
			addSequential(new auto_wait(0.4));
			addParallel (new auto_driveToAngleWithEncoder(-.5, 4, 30, 10000, 10000, 0.04, true));// speed was .9		//Practice only***********************************************************
			addSequential(new auto_LiftDown(90, 300));
			addSequential(new auto_moveUpperPosition(2850,60)); //100 practice
			addSequential(new auto_wait(0.3));
			addSequential(new auto_liftStop());
			addSequential(new auto_moveUpperPosition(3300,60)); //100 practice
			
			
		//You choose poorly.  Switch and scale are NOT on our side.  RRR	
		//Drive to Center line?	

		} else {
			addSequential(new CG_crossTheLine());
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
		}
		//Switch and scale are on our side  LLL   OR   Switch is NOT on our side scale is  RLR  These are the same
		
		
	
	}
}
