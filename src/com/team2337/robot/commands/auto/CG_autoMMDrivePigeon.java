package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_autoMMDrivePigeon extends CommandGroup{

	public CG_autoMMDrivePigeon() {
	addParallel(new auto_bigBrother_DoNothing());
	addSequential(new auto_holdUpperPosition(0.1));
	//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
	addSequential(new auto_MMDrivePigeon(50000, 0));
	}
}
