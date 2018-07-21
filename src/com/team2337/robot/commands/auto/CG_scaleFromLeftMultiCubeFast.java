package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scaleFromLeftMultiCubeFast extends CommandGroup {


	String ourSwitch, scale;
	public CG_scaleFromLeftMultiCubeFast(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// Score Scale, get cube, Score Scale, get cube,  Score Scale	//LLL AND RLR
				if  (scale.equals("L")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					
					///////         First Cube
					addSequential (new auto_driveToAngleWithEncoder(.9,10,0,71000,71000,.13,"Left"));
					addParallel (new CG_ArmToTop3());  //arm to top midway through drive
					addSequential (new auto_driveToAngleWithEncoder(.8,10,-9,120000,120000,.06,"Left"));
					addSequential (new auto_driveToAngleWithEncoder(.6,10,-18,136000,136000,.06,"Left"));
					/////////////////////////////////////////////////////////////////////////////////////
					
					// This command shoots the cube as the robot comes to its position at the scale
					addParallel(new auto_intakeOutWithEncoder(0.8, 10, 168000));	//guessing this is where the scale is
//					addParallel(new auto_intakeOutWithEncoder(0.65, 10, 168000));
					/* Changed after last match Friday
					 * The intake is is unable to spit out the first cube
					 * This problem just began today after all the Talon problem had been solved
					 * There is a possibility that the issue may be the motor
					 */
					
//					addSequential(new auto_clawOpenWithEncoder(1.0, 10, 60000));
					
					//Drive back to grab second cube
					addSequential (new auto_driveToAngleWithEncoder(.3,.8,-20,168000,168000,.03,"Left"));
					addSequential (new auto_brakeModeOn());
					addSequential(new auto_clawOpen()); //Open in case the intakes don't shoot
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
					
					/*
					 * Was Labeled "Practice Only!" but was used at CMP
					 * Not sure if this has any affect on the auto currently
					 */
					addParallel(new auto_driveToAngleWithEncoder(0.5, 2, 0, 26000, 26000, 0.06, "Right"));	
					
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
					/*
					 * Was Labeled "Practice Only!" but was used at CMP
					 * Not sure if this has any affect on the auto currently
					 */
					addParallel(new auto_driveForwardWithTime(-0.5, .5));		
					addSequential(new auto_LiftDown(90, 300));
					addParallel(new auto_moveUpperPosition(2850,45)); //100 practice
					addSequential(new auto_wait(0.3));
					addSequential(new auto_liftStop());
					addSequential(new auto_moveUpperPosition(3300,45)); //100 practice
					
					
					addSequential(new auto_resetEncoder());
					addSequential(new auto_clawOpen());
					
					//drive to third cube
					addParallel(new auto_driveToAngleWithEncoder(-.6,10,28,28500,28500,.045,"Right"));
					//changed 28000 to 28500 allowing the intake to grab the cube
					
					//grab third cube
					addSequential(new auto_wait(0.2));
					addParallel(new auto_intake_in(0.6, 2));
					addSequential(new auto_wait(1));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addParallel(new auto_intake_in(1, 1));
					addSequential(new auto_resetEncoder());
					
					//go to carry and move to score
					addSequential(new auto_driveToAngleWithEncoder(.6,10,35,18000,18000,.045,"Right"));
					//changed practice\
//					addParallel (new CG_ArmToTopTrolleyAndLift());
					addParallel (new CG_ArmToTop2());
					//change from 26000 to 30000 before q49
					addSequential(new auto_driveToAngleWithEncoder(.6,10,0,36000,36000,.06,"Right"));
					//addSequential(new auto_driveToAngleWithEncoder(.6,10,0,33000,33000,.06,"Right"));
					
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
				} else if (scale.equals("R")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE8 LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					addSequential(new auto_driveToAngleWithEncoder(.8, 4, 0, 120000, 120000, 0.04, true, 0.02, "Left"));// speed was .7 //115000
					addSequential(new auto_brakeModeOn());
					addSequential(new auto_driveToAngleWithEncoder(.6, 4, -93, 200000, 200000, 0.032, true, 0.02, "Left"));// speed was .5 
					addParallel(new CG_ArmToTop());
					addSequential(new auto_driveToAngleWithEncoder(.6, 4, -95, 293000, 293000, 0.035, true, 0.02, "Left"));// 296000
					//**changed to 303000 from 305000 before q43**//
					addSequential(new auto_driveToAngleWithEncoder(.6, 4, 0, 306000, 306000, 0.04, true, 0.02, "Right"));//276000
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
					addSequential(new auto_moveUpperPosition(2850,45)); //100 practice
					addSequential(new auto_wait(0.3));
					addSequential(new auto_liftStop());
					addSequential(new auto_moveUpperPosition(3300,45)); //100 practice
					
					//Drive to second cube.
				
					addParallel (new auto_driveToAngleWithEncoder(-.5, 4, -13, 33000, 33000, 0.04, "Left"));// speed was .9 

					addSequential(new auto_wait(0.2));
					addParallel(new auto_intake_in(1, 3.2));
					addSequential(new auto_wait(1.6));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addSequential(new auto_wait(.2));
					addSequential(new auto_resetEncoder());
					
					// Drive to scale 2nd cube
					addParallel(new auto_driveToAngleWithEncoder(0.6, .9, 5, 36000, 36000, 0.06, "Right"));
					
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
					addParallel(new auto_driveToAngleWithEncoder(-.6, 4, 0, 10000, 10000, 0.04, "Left"));
					addSequential(new auto_LiftDown(90, 300));
					addSequential(new auto_moveUpperPosition(2850,45)); //100 practice
					addSequential(new auto_wait(0.3));
					addParallel(new auto_driveToAngleWithEncoder(-.6, 4, -30, 26000, 26000, 0.04, "Left"));
					addSequential(new auto_liftStop());
					addSequential(new auto_moveUpperPosition(3300,45)); //100 practice
					
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
