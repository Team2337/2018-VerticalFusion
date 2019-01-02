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
			addSequential(new commonCG_scoreLeftSwitch());
			addSequential(new commonCG_armToPickupFromFront());
			addSequential(new commonCG_switchBackoutLeftToLeft());
			
			
		// **************************  LRL  **************************
		// Score in switch, get cube drive to center.	
		
	
	}
}
