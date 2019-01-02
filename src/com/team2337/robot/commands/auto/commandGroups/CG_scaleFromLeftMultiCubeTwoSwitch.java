package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scaleFromLeftMultiCubeTwoSwitch extends CommandGroup {


	String ourSwitch, scale;
	public CG_scaleFromLeftMultiCubeTwoSwitch(String ourSwitch, String scale) {
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

					addParallel(new CG_ScaleScore());  //Score first cube
					addSequential (new auto_driveToAngleWithEncoder(.3,.8,-20,160000,160000,.03)); 
					//addSequential (new auto_driveToAngleWithEncoderandLine(.3,.8,-20,165000,165000,.03)); 
					addSequential (new auto_brakeModeOn());
					
					///delay to allow arm to come to ground
					addSequential(new auto_wait(2.2));
					addSequential(new auto_resetEncoder());
					addSequential(new auto_clawOpen());
					// Drive to second cube
					addSequential(new auto_driveToAngleWithEncoder(-.7,10,10,30000,30000,.06));
					
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
				} else if (ourSwitch.equals("L")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
					addSequential(new commonCG_scoreRightSwitch());
					addSequential(new commonCG_armToPickupFromFront());
					addSequential(new commonCG_driveAroundNearSwitchLeft());
				} else if (ourSwitch.equals("R")) {
					addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 9000, 9000, 0.02)); //Drive Forward
					addSequential(new auto_gyroMMTurn(-75, 0.75)); //Turn Right
					addSequential(new auto_driveToAngleWithEncoder(.7, 8, -75, 101000, 101000, 0.025, true)); //101000 - Drive forward
					
					addSequential(new auto_driveToAngleWithEncoder(.7, 3, 0, 115000, 115000, 0.02)); //Turn left to drive to switch
					addSequential(new auto_moveUpperPosition(1800,60));  //-250,60 - Move arm to upper
					addParallel(new auto_driveForwardWithTime(.3, 1)); //Make sure we're on the switch
					addSequential(new auto_clawOpenWithCollision(4)); //Open Claw with collision
					addSequential (new auto_intakeOut(.3,1)); //Intake
					addSequential(new auto_resetEncoder()); //Reset
					addSequential(new commonCG_armToPickupFromFront());
					
					//Drive back and go around right side of switch
					addSequential(new auto_driveToAngleWithEncoder(-.6, 3, 0, 27000, 27000, 0.02)); //Move backwards 
					addSequential(new auto_resetEncoder());
					addSequential(new auto_driveToAngleWithEncoder(.6, 4, -35, 45000, 45000, 0.015)); //Turn towards right wall
					addSequential(new auto_driveToAngleWithEncoder(.6, 4, 0, 60000, 60000, 0.025)); //Move forward next to switch
					addSequential(new auto_brakeModeOn());
					
				} else {
					addSequential(new CG_crossTheLine());
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
				}
		//Switch and scale are NOT on our side  LLL
		
		
	
	}
}
