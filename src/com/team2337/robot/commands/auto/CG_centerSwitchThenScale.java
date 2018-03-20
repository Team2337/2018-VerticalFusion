package com.team2337.robot.commands.auto;


import com.team2337.robot.commands.claw.claw_CGOpenClose;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_centerSwitchThenScale extends CommandGroup {

	String ourSwitch, scale;
	public CG_centerSwitchThenScale(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// **************************  LLL  **************************
		// Score in switch, get cube, score in scale.
		if  (ourSwitch.equals("L") && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
//			addSequential(new auto_driveToAngleWithEncoder(.9, 4, 40, 46000, 46000, 0.0175));// speed was .9 
//			addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 60000, 60000, 0.02));
//			addSequential(new auto_moveUpperPosition(1800,60));  //-250,60  ///Score into Switch position
//			addParallel(new auto_driveForwardWithTime(.3, 2));
//			addSequential(new auto_clawOpenWithCollision(4));
//			addSequential(new auto_wait(2));
			addSequential(new auto_resetEncoder());
			addParallel(new auto_driveToAngleWithEncoder(-.5, 3, 0, 20000, 20000, 0.02));
			addSequential(new auto_moveUpperPosition(2127,500));  //50,386  ///Touch down
			addSequential(new auto_wait(1.6));
			addSequential(new auto_moveUpperPosition(2900,500));
			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPosition(2900,100));
			addSequential(new auto_wait(0.5));
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(.5, 4, 50, 16000, 16000, 0.015));
			addSequential(new auto_driveToAngleWithEncoder(.5, 4, 0, 30000, 30000, 0.0175));
			
			
			
		// **************************  LRL  **************************
		// Score in switch, get cube drive to center.	
		} else if (ourSwitch.equals("L")  && scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
//			addSequential(new auto_driveToAngleWithEncoder(.9, 4, 40, 46000, 46000, 0.0175));// speed was .9 
//			addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 60000, 60000, 0.02));
//			addSequential(new auto_moveUpperPosition(1800,60));  //-250,60  ///Score into Switch position
//			addParallel(new auto_driveForwardWithTime(.3, 1));
//			addSequential(new auto_clawOpenWithCollision(4));
//			addSequential(new auto_wait(2));
			//addSequential(new auto_resetEncoder());
//					addParallel(new auto_driveToAngleWithEncoder(-.5, 3, 0, 20000, 20000, 0.04));
			addSequential(new auto_moveUpperPosition(2127,500));  //50,386  ///Touch down
			addSequential(new auto_wait(0.175));
			addSequential(new auto_moveUpperPosition(2900,500));
			addSequential(new auto_wait(0.35));
			addSequential(new auto_moveUpperPosition(2900,100));
			addSequential(new auto_wait(0.25));
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 20000, 20000, 0.04));
			
			//Go around switch to right
//			addSequential(new auto_driveToAngleWithEncoder(.5, 4, 50, 16000, 16000, 0.015));
//			addSequential(new auto_driveToAngleWithEncoder(.5, 4, 0, 30000, 30000, 0.0175));
			
//			addSequential(new auto_driveToAngleWithEncoder(.5, 8, -90, 85000, 85000, 0.03, true));
			addSequential(new auto_driveToAngleWithEncoder(.7, 8, -90, 47000, 47000, 0.02, true, 0.64));
			addSequential(new auto_driveToAngleWithEncoder(.7, 8, -38, 101000, 101000, 0.03, true));
			addSequential(new auto_driveToAngleWithEncoder(.7, 8, 0, 160000, 160000, 0.025));
			addSequential(new auto_brakeModeOn());
			addSequential(new auto_resetEncoder());
			addSequential(new auto_clawCGOpenClose());
			addSequential(new auto_driveToAngleWithEncoder(-.5, 8, -40, 16000, 16000, 0.04));
			addParallel(new auto_moveUpperPosition(3300,100));
			addParallel(new auto_intake_in(1, 2));
//			addSequential(new auto_resetEncoder());
//			addSequential(new auto_driveToAngleWithEncoder(-.5, 8, -35, 45000, 45000, 0.0175));
			
			
			
			addSequential(new auto_brakeModeOff());
			
			
		// **************************  RLR  **************************
		// Score in switch, get cube drive to center.		
		} else if (ourSwitch.equals("R")  && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
			addSequential(new auto_driveToAngleWithEncoder(.9, 4, -40, 44000, 44000, 0.0175)); // right was 44000 left was 28000 left was 22000 
			addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 60000, 60000, 0.02));
			addSequential(new auto_moveUpperPosition(1800,60));  //-250,60
			addParallel(new auto_driveForwardWithTime(.3, 1));
			addSequential(new auto_clawOpenWithCollision(4));
			addSequential(new auto_wait(2));
			addSequential(new auto_resetEncoder());
			addParallel(new auto_driveToAngleWithEncoder(-.5, 3, 0, 27000, 12000, 0.02));
			addSequential(new auto_moveUpperPosition(2127,500));  //50,386  ///Touch down
			addSequential(new auto_wait(1.6));
			addSequential(new auto_moveUpperPosition(2900,500));
			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPosition(2900,100));
			addSequential(new auto_wait(0.5));
			addSequential(new auto_resetEncoder());
			//Go around switch to left
//			addSequential(new auto_driveToAngleWithEncoder(.5, 4, -50, 32000, 20000, 0.015));
//			addSequential(new auto_driveToAngleWithEncoder(.5, 4, 0, 65000, 30000, 0.0175));
			
			addSequential(new auto_driveToAngleWithEncoder(.5, 8, 90, 76000, 76000, 0.0175));
			addSequential(new auto_driveToAngleWithEncoderandLine(.5, 8, 0, 152000, 152000, 0.0175));
			addSequential(new auto_brakeModeOn());
			addSequential(new auto_moveUpperPosition(2900,100));
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(-.5, 8, 35, 45000, 45000, 0.0175));
			addSequential(new auto_intake_in(1, 2));
			
			
			addSequential(new auto_brakeModeOff());
			
			
		// **************************  RRR  **************************
		// Score in switch, get cube, score in scale.			
		} else {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
			addSequential(new auto_driveToAngleWithEncoder(.9, 4, -40, 44000, 44000, 0.0175)); // right was 44000 left was 28000 left was 22000 
			addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 60000, 60000, 0.02));
			addSequential(new auto_moveUpperPosition(1800,60));  //-250,60
			addParallel(new auto_driveForwardWithTime(.3, 1));
			addSequential(new auto_clawOpenWithCollision(4));
			addSequential(new auto_wait(2));
			addSequential(new auto_resetEncoder());
			addParallel(new auto_driveToAngleWithEncoder(-.5, 3, 0, 27000, 12000, 0.02));
			addSequential(new auto_moveUpperPosition(2127,500));  //50,386  ///Touch down
			addSequential(new auto_wait(1.6));
			addSequential(new auto_moveUpperPosition(2900,500));
			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPosition(2900,100));
			addSequential(new auto_wait(0.5));
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(.5, 4, -50, 32000, 20000, 0.015));
			addSequential(new auto_driveToAngleWithEncoder(.5, 4, 0, 65000, 30000, 0.0175));
			
		}
		
	
	}
}
