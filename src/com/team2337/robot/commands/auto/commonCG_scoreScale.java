package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_scoreScale extends CommandGroup{
	public commonCG_scoreScale() {
	addParallel(new auto_bigBrother_DoNothing());
	addSequential(new auto_holdUpperPosition(0.1));
	//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
	addSequential (new auto_driveToAngleWithEncoder(.6,10,0,15000,15000,.13,"Left"));
	addParallel(new CG_ArmToTop3());
	addSequential (new auto_driveToAngleWithEncoder(.6,10,0,40000,40000,.13,"Left"));
	addSequential(new auto_intakeOutWithEncoder(0.75, 10, 60000));
//	addSequential(new auto_clawOpenWithEncoder(1.0, 10, 60000));
	
	addSequential(new auto_armMoveWithFinish(2800, 2700));
	addParallel(new auto_moveUpperPosition(2800,60)); //525, 500 practice
	addSequential(new auto_LiftDown(90, 150));
	addSequential(new auto_liftStop());
	
	}
}
