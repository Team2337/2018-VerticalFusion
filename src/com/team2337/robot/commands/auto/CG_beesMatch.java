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
					addSequential (new auto_driveToAngleWithEncoder(.9,10,0,70000,70000,.13,"Right"));
					addParallel (new CG_ArmToTop());  //arm to top midway through drive
					addSequential (new auto_driveToAngleWithEncoder(.8,10,-12,110000,110000,.06,"Right"));
					addSequential (new auto_driveToAngleWithEncoder(.6,10,-18,128000,128000,.06,"Right"));

					addParallel(new CG_ScaleScore2());  //Score first cube
					addSequential (new auto_driveToAngleWithEncoder(.3,.8,-20,145000,145000,.03,"Right")); 
					//addSequential (new auto_driveToAngleWithEncoderandLine(.3,.8,-20,165000,165000,.03)); 
					
					addSequential (new auto_brakeModeOn());
					
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
					addParallel(new auto_driveToAngleWithEncoder(-.6,10,32,28000,28000,.045,"Right"));
					
					//grab third cube
					addSequential(new auto_wait(0.2));
					addParallel(new auto_intake_in(0.6, 2));
					addSequential(new auto_wait(.8));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addSequential(new auto_resetEncoder());
					
					//go to carry and move to score
					addSequential(new auto_driveToAngleWithEncoder(.6,10,35,16000,16000,.045,"Right"));
					addParallel (new CG_ArmToTop2());
					addSequential(new auto_driveToAngleWithEncoder(.6,10,0,26000,26000,.06,"Right"));
					
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
