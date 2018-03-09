package com.team2337.robot.commands.auto;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_centerSwitchLeft extends CommandGroup {

	String ourSwitch, scale;
	public CG_centerSwitchLeft(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential(new auto_driveToAngleWithEncoder(.9, 4, 40, 52000, 52000, 0.0175));// speed was .9 
			addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 69000, 69000, 0.02));
			addSequential(new auto_moveUpperPosition(1800,60));  //-250,60  ///Score into Switch position
			addParallel(new auto_driveForwardWithTime(.3, 1));
			addSequential(new auto_clawOpenWithCollision(4));
			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPosition(2127,550));  //50,386  ///Touch down
			addSequential(new auto_wait(1.6));
			addSequential(new auto_moveUpperPosition(2900,550));
			addSequential(new auto_wait(1));
			addSequential(new auto_moveUpperPosition(2900,100));
			
			
		// **************************  LRL  **************************
		// Score in switch, get cube drive to center.	
		
	
	}
}
