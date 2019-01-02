package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scaleFromLeftMultiCube3 extends CommandGroup {


	String ourSwitch, scale;
	public CG_scaleFromLeftMultiCube3(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// Score Scale, get cube, Score Scale, get cube,  Score Scale	//LLL AND RLR
				if  (scale.equals("L")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					
					///////         First Cube
					addSequential (new auto_driveToAngleWithEncoder(.9,10,0,70000,70000,.13,"Left"));
					addParallel (new CG_ArmToTop());  //arm to top midway through drive
					addSequential (new auto_driveToAngleWithEncoder(.8,10,-9,120000,120000,.06,"Left"));
					addSequential (new auto_driveToAngleWithEncoder(.6,10,-18,136000,136000,.06,"Left"));

					addParallel(new CG_ScaleScore2());  //Score first cube
					//changed from 145000 to 148000 before q49
					addSequential (new auto_driveToAngleWithEncoder(.3,.8,-20,168000,168000,.03,"Left"));
					//addSequential (new auto_driveToAngleWithEncoderandLine(.3,.8,-20,165000,165000,.03)); 
					
					addSequential (new auto_brakeModeOn());
					/*
					///delay to allow arm to come to ground
					addSequential(new auto_wait(1.7));
					addSequential(new auto_resetEncoder());
					addSequential(new auto_clawOpen());
					addSequential(new auto_LiftDown(90, 300));
					
					// Drive to second cube
					addParallel(new auto_driveToAngleWithEncoder(-.7,10,10,36000,36000,.06,"Right"));
				
					//Move arm to the pickup position 
					addSequential(new auto_moveUpperPositionWithIsFinished(3300,60)); //100 practice
					addSequential(new auto_wait(0.3));
					addSequential(new auto_liftStop());
					
					//grab second cube
					addSequential(new auto_wait(0.1));
					addParallel(new auto_intake_in(0.6, 3));
					addSequential(new auto_wait(0.7));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addSequential(new auto_resetEncoder());
					
					//score second cube
					addSequential(new CG_scorePositionLeft());  //score second cube
					
					addSequential(new auto_resetEncoder());
					addSequential(new auto_clawOpen());
					
					//drive to third cube
					//changed from 32 to 28 before q49
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
					addParallel (new CG_ArmToTop2());
					//change from 26000 to 30000 before q49
					addSequential(new auto_driveToAngleWithEncoder(.6,10,0,30000,30000,.06,"Right"));
					
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


*/
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
					addSequential (new auto_driveToAngleWithEncoder(.6, 4, -95, 296000, 296000, 0.035, true, 0.02, "Left"));// speed was .6 //282000
					//**changed to 303000 from 305000 before q43**/
					addSequential (new auto_driveToAngleWithEncoder(.6, 4, -5, 303000, 303000, 0.04, true, 0.02, "Right"));//276000
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
				
					addParallel (new auto_driveToAngleWithEncoder(-.5, 4, -13, 33000, 33000, 0.04, true));// speed was .9 

					addSequential(new auto_wait(0.2));
					addParallel(new auto_intake_in(1, 3.2));
					addSequential(new auto_wait(1.6));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addSequential(new auto_wait(.2));
					addSequential(new auto_resetEncoder());
					
					
					addParallel(new auto_driveToAngleWithEncoder(0.6, .9, 0, 44000, 44000, 0.06));
					
					addSequential(new auto_wait(0.3));
					addSequential(new auto_moveUpperPositionWithIsFinished(2850,500,475)); //525, 500 practice
					addSequential(new auto_moveUpperPositionWithIsFinished(2300,525)); //540 practice
					addSequential(new auto_LiftUp(500, 420)); //600, 500 practice  /// 580, 500******
					addSequential(new auto_moveUpperPosition(1700, 525)); //540 practice
					addSequential(new auto_wait(0.8)); // hold arm to place cube instead of throwing it....
//						addSequential(new auto_intakeOut(0.4, 0.4));
					addSequential(new claw_open());
					addSequential(new auto_wait(0.4));
					addSequential(new auto_moveUpperPositionWithIsFinished(2850,500)); //525 practice
					addSequential(new auto_wait(0.4));
//					addParallel (new auto_driveToAngleWithEncoder(-.5, 4, -30, 10000, 10000, 0.04, true));// speed was .9		//Practice only***********************************************************
					addParallel(new auto_driveToAngleWithEncoder(-.6, 4, 0, 10000, 10000, 0.04, true));
					addSequential(new auto_LiftDown(90, 300));
					addSequential(new auto_moveUpperPosition(2850,60)); //100 practice
					addSequential(new auto_wait(0.3));
					addParallel(new auto_driveToAngleWithEncoder(-.6, 4, -30, 26000, 26000, 0.04, true));
					addSequential(new auto_liftStop());
					addSequential(new auto_moveUpperPosition(3300,60)); //100 practice
					
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
