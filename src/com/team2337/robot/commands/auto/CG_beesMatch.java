package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_beesMatch extends CommandGroup {


	String ourSwitch, scale;
	public CG_beesMatch(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// Score Scale, get cube, Score Scale, get cube,  Score Scale	//LLL AND RLR
				if  (scale.equals("L")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					
					///////         First Cube
					addSequential (new auto_driveToAngleWithEncoder(.9,10,0,70000,70000,.13,"Left"));
					addParallel (new CG_ArmToTop3());  //arm to top midway through drive
					addSequential (new auto_driveToAngleWithEncoder(.8,10,-9,120000,120000,.06,"Left"));
					addSequential (new auto_driveToAngleWithEncoder(.6,10,-18,136000,136000,.06,"Left"));
					/////////////////////////////////////////////////////////////////////////////////////
					
					addParallel(new auto_intakeOutWithEncoder(0.6, 10, 168000));	//guessing this is where the scale is
//					addSequential(new auto_clawOpenWithEncoder(1.0, 10, 60000));
					addSequential (new auto_driveToAngleWithEncoder(.3,.8,-20,168000,168000,.03,"Left"));
					addSequential (new auto_brakeModeOn());
					addSequential(new auto_clawOpen());
					addSequential(new auto_armMoveWithFinish(2800, 2700));
					addParallel(new auto_moveUpperPosition(2800,45)); //525, 500 practice
					addSequential(new auto_LiftDown(90, 150));
//					addSequential(new auaddSequential(new auto_clawOpen());to_liftStop());
					addSequential(new auto_clawOpen());
					addSequential(new auto_resetEncoder());
					
					
					
					// Drive to second cube
					addSequential(new auto_clawOpen());
					addParallel(new auto_driveToAngleWithEncoder(-.7,10,7,30000,30000,.06,"Right"));
					//Move arm to the pickup position 
					addSequential(new auto_moveUpperPositionWithIsFinished(3300,45)); //100 practice
					addSequential(new auto_wait(0.4));
					addSequential(new auto_liftStop());
					
					//grab second cube
					addSequential(new auto_wait(0.1));
					addParallel(new auto_intake_in(0.6, 3)); 
					addSequential(new auto_wait(0.6));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addParallel(new auto_intake_in(1.0, 1));
					addSequential(new auto_resetEncoder());
					
					//score second cube
//					addSequential(new CG_scorePositionLeftFast());  //score second cube
					
					
					addParallel(new auto_driveToAngleWithEncoder(0.5, 2, 0, 26000, 26000, 0.06, "Right"));	//Practice only***********************************************************
					
					addSequential(new auto_wait(0.4));
					addSequential(new auto_moveUpperPositionWithIsFinished(2300,525)); //540 practice
					addSequential(new auto_LiftUp(580, 500)); //600, 500 practice
					addSequential(new auto_wait(0.1));
					addSequential(new auto_moveUpperPosition(1750, 525)); //540 practice
					addSequential(new auto_wait(0.7));
//						addSequential(new auto_intakeOut(0.4, 0.4));
					addSequential(new claw_open());
					addSequential(new auto_wait(0.2));
					addSequential(new auto_moveUpperPositionWithIsFinished(2850,500)); //525 practice
					addSequential(new auto_wait(0.4));
					addParallel(new auto_driveForwardWithTime(-0.5, .5));		//Practice only***********************************************************
					addSequential(new auto_LiftDown(90, 300));
					addParallel(new auto_moveUpperPosition(2850,45)); //100 practice
					addSequential(new auto_wait(0.3));
					addSequential(new auto_liftStop());
					addSequential(new auto_moveUpperPosition(3300,45)); //100 practice
					
					
					addSequential(new auto_resetEncoder());
					addSequential(new auto_clawOpen());
					
					//drive to third cube
					addParallel(new auto_driveToAngleWithEncoder(-.6,10,28,28000,28000,.045,"Right"));
					
					//grab third cube
					addSequential(new auto_wait(0.2));
					addParallel(new auto_intake_in(0.6, 2));
					addSequential(new auto_wait(1));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addParallel(new auto_intake_in(1, 1));
					addSequential(new auto_resetEncoder());
					
					//go to carry and move to score
					addSequential(new auto_driveToAngleWithEncoder(.6,10,35,16000,16000,.045,"Right"));
//					addParallel (new CG_ArmToTopTrolleyAndLift());
					addParallel (new CG_ArmToTop2());
					//change from 26000 to 30000 before q49
					addSequential(new auto_driveToAngleWithEncoder(.6,10,0,37000,37000,.06,"Right"));
					
					//score third cube
					addSequential(new auto_wait(0.35));
					addSequential(new auto_LiftUp(400, 300)); //600, 500 practice
					addSequential(new auto_moveUpperPosition(1600, 525)); //540 practice
					addSequential(new auto_wait(0.6));
					addSequential(new claw_open());
					addSequential(new auto_wait(0.2));
					addSequential(new auto_moveUpperPositionWithIsFinished(2850,500)); //525 practice
					addSequential(new auto_wait(0.4));
					addSequential(new auto_LiftDown(90, 150));
					addSequential(new auto_moveUpperPosition(2850,60)); //100 practice
					addSequential(new auto_wait(0.3));
					addSequential(new auto_liftStop());
					/*
					addSequential(new auto_wait(0.35));
					addSequential(new auto_moveUpperPosition(1600, 525)); //540 practice
					addSequential(new auto_wait(0.6));
					addSequential(new claw_open());
					addSequential(new auto_wait(0.2));
					addSequential(new auto_moveUpperPositionWithIsFinished(2850,500)); //525 practice
					addSequential(new auto_wait(0.4));
					addSequential(new auto_LiftDown(90, 150));
					addSequential(new auto_moveUpperPosition(2850,60)); //100 practice
					addSequential(new auto_wait(0.3));
					addSequential(new auto_liftStop());
					*/


				//Switch is on our side scale is not LRL
					//Score in switch from side and try to grab cube
				}  else if (ourSwitch.equals("L")  && scale.equals("R")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					addSequential (new auto_driveToAngleWithEncoder(.5, 4, 0, 93000, 93000, 0.04));// speed was .9 
					addSequential (new auto_brakeModeOn());
					addSequential (new auto_gyroMMTurn(-80, 1));
					addSequential (new auto_driveForwardWithTime(.5,0.5)); //.5,2
					addSequential (new auto_moveUpperPosition(1800,60));  //-250,60  ///Score into Switch position
					addSequential (new auto_wait(0.3));
					addSequential (new auto_resetEncoder());
					addSequential (new auto_intakeOut(.8,1.5));
					addSequential (new commonCG_armToPickupFromFront());
					addSequential (new auto_driveToAngleWithEncoder(-.5, 4, -90, 12000, 12000, 0.04));
				} else {
					addSequential(new CG_crossTheLine());
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
				}
		//Switch and scale are NOT on our side  LLL
		
		
	
	}
}
