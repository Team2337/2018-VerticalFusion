package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scaleFromLeftMultiCube2 extends CommandGroup {


	String ourSwitch, scale;
	public CG_scaleFromLeftMultiCube2(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// Score Scale, get cube, Score Scale, get cube,  Score Scale	//LLL AND RLR
				if  (scale.equals("L")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					///////         First Cube
					addSequential (new auto_driveToAngleWithEncoder(.7,10,0,80000,80000,.13));
					addParallel (new CG_ArmToTop());  //arm to top midway through drive
					addSequential (new auto_driveToAngleWithEncoder(.6,10,-9,148000,148000,.06));

					addParallel(new CG_ScaleScore2());  //Score first cube
					addSequential (new auto_driveToAngleWithEncoder(.3,.8,-20,160000,160000,.03)); 
					//addSequential (new auto_driveToAngleWithEncoderandLine(.3,.8,-20,165000,165000,.03)); 
					addSequential (new auto_brakeModeOn());
					
					///delay to allow arm to come to ground
					addSequential(new auto_wait(1.5));
					addSequential(new auto_resetEncoder());
					addSequential(new auto_clawOpen());
					
					addSequential(new auto_LiftDown(90, 300));
					// Drive to second cube
					addParallel(new auto_driveToAngleWithEncoder(-.7,10,10,34000,34000,.06));
				
					addSequential(new auto_moveUpperPositionWithIsFinished(3300,60)); //100 practice
//					addSequential(new auto_wait(0.3));
					addSequential(new auto_liftStop());
					
					
					addSequential(new auto_wait(1));
					addParallel(new auto_intake_in(1, 3));
					addSequential(new auto_wait(.1));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addSequential(new auto_resetEncoder());
					addSequential(new CG_scorePositionLeft());  //score second cube
					


					
					//grab 3rd cube
					addSequential(new auto_resetEncoder());
					addSequential(new auto_clawOpen());
					//drive to third cube
					addParallel(new auto_driveToAngleWithEncoder(-.6,10,40,26000,26000,.045));
					addSequential(new auto_wait(0.2));
					addParallel(new auto_intake_in(1, 4));
					addSequential(new auto_wait(.8));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addSequential(new auto_resetEncoder());
					
					addSequential(new auto_driveToAngleWithEncoder(.6,10,35,18000,18000,.045));
					addParallel (new CG_ArmToTop());
					addSequential(new auto_driveToAngleWithEncoder(.6,10,0,23000,23000,.045));
					
					
					addSequential(new auto_LiftUp(300, 260)); //600, 500 practice
					addSequential(new auto_moveUpperPosition(1850, 525)); //540 practice
					addSequential(new auto_wait(0.2));
					addSequential(new claw_open());
					addSequential(new auto_wait(0.2));
					addSequential(new auto_moveUpperPositionWithIsFinished(2850,500)); //525 practice
					addSequential(new auto_wait(0.4));
					addSequential(new auto_LiftDown(90, 300));
					addSequential(new auto_moveUpperPosition(2850,60)); //100 practice
					addSequential(new auto_wait(0.3));
					addSequential(new auto_liftStop());



				//Switch is on our side scale is not LRL
					//Score in switch from side and try to grab cube
				} else if (scale.equals("R")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE8 LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					addSequential (new auto_driveToAngleWithEncoder(.8, 4, 0, 118000, 118000, 0.04, true, 0.02, "Left"));// speed was .7 //115000
					addSequential (new auto_brakeModeOn());
					addSequential (new auto_driveToAngleWithEncoder(.6, 4, -93, 200000, 200000, 0.032, true, 0.02, "Left"));// speed was .5 
					addParallel (new CG_ArmToTop());
					addSequential (new auto_driveToAngleWithEncoder(.6, 4, -95, 292000, 292000, 0.035, true, 0.02, "Left"));// speed was .6 //282000
					addSequential (new auto_driveToAngleWithEncoder(.6, 4, -5, 292000, 292000, 0.04, true, 0.02, "Right"));//276000
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
				
					addParallel (new auto_driveToAngleWithEncoder(-.5, 4, -10, 33000, 33000, 0.04, true));// speed was .9 

					addSequential(new auto_wait(0.2));
					addParallel(new auto_intake_in(1, 3.2));
					addSequential(new auto_wait(1.3));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addSequential(new auto_wait(.2));
					addSequential(new auto_resetEncoder());
					
					
					addParallel(new auto_driveToAngleWithEncoder(0.5, .9, 5, 31000, 31000, 0.06));
					
					addSequential(new auto_wait(0.3));
					addSequential(new auto_moveUpperPositionWithIsFinished(2850,500,475)); //525, 500 practice
					addSequential(new auto_moveUpperPositionWithIsFinished(2300,525)); //540 practice
					addSequential(new auto_LiftUp(500, 420)); //600, 500 practice  /// 580, 500******
					addSequential(new auto_moveUpperPosition(1850, 525)); //540 practice
					addSequential(new auto_wait(0.6)); // hold arm to place cube instead of throwing it....
//						addSequential(new auto_intakeOut(0.4, 0.4));
					addSequential(new claw_open());
					addSequential(new auto_wait(0.2));
					addSequential(new auto_moveUpperPositionWithIsFinished(2850,500)); //525 practice
					addSequential(new auto_wait(0.4));
					addParallel (new auto_driveToAngleWithEncoder(-.5, 4, -30, 10000, 10000, 0.04, true));// speed was .9		//Practice only***********************************************************
					addSequential(new auto_LiftDown(90, 300));
					addSequential(new auto_moveUpperPosition(2850,60)); //100 practice
					addSequential(new auto_wait(0.3));
					addSequential(new auto_liftStop());
					addSequential(new auto_moveUpperPosition(3300,60)); //100 practice
					

					
					
					//addSequential (new auto_gyroMMTurn(0, 1));
					
					/*
					addParallel (new CG_ArmToTop());
					addSequential (new auto_driveToAngleWithEncoder(.5, 4, 90, 180000, 180000, 0.04, true));// speed was .9 
					addSequential (new auto_gyroMMTurn(0, 1));
					addSequential(new CG_crossFirstScorePosition());
					 
					addSequential (new auto_wait(2.2));
					addSequential(new auto_resetEncoder());
					addSequential(new auto_clawOpen());
					addSequential (new auto_driveToAngleWithEncoder(-.5,10,-10,35000,35000,.06));
					addSequential(new auto_wait(0.8));
					addParallel(new auto_intake_in(1, 3));
					addSequential(new auto_wait(1));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addSequential(new auto_resetEncoder());
					addSequential(new CG_scorePosition());
					
					
					
					
					
					addSequential (new auto_driveForwardWithTime(.5,0.5)); //.5,2
					addSequential (new auto_moveUpperPosition(1800,60));  //-250,60  ///Score into Switch position
					addSequential (new auto_wait(0.3));
					addSequential (new auto_resetEncoder());
					addSequential (new auto_intakeOut(.8,1.5));
					addSequential (new commonCG_armToPickupFromFront());
					addSequential (new auto_driveToAngleWithEncoder(-.5, 4, 90, 12000, 12000, 0.04));
					
					*/
				//Switch is NOT on our side scale is NOT RLR
					//Cross the line
				} else if (ourSwitch.equals("R")  && scale.equals("R")) {
					addSequential(new CG_crossTheLine());
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					
				} else {
					addSequential(new CG_crossTheLine());
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
				}
		//Switch and scale are NOT on our side  LLL
		
		
	
	}
}
