package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_scoreScaleLeftFast extends CommandGroup{
	public commonCG_scoreScaleLeftFast() {
	addParallel(new auto_bigBrother_DoNothing());
	addSequential(new auto_holdUpperPosition(0.1));
	//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					//COPIED FROM MULTI CUBE//
	//Drive forward with arm in shoot position
	addSequential (new auto_driveToAngleWithEncoder(.9,10,0,70000,70000,.13,"Left"));
	addParallel (new CG_ArmToTop3());  //arm to top midway through drive
	addSequential (new auto_driveToAngleWithEncoder(.8,10,-9,120000,120000,.06,"Left"));
	addSequential (new auto_driveToAngleWithEncoder(.6,10,-18,136000,136000,.06,"Left"));
	/////////////////////////////////////////////////////////////////////////////////////
	
	addSequential(new auto_intakeOutWithEncoder(0.75, 10, 140000));	//guessing this is where the scale is
//	addSequential(new auto_clawOpenWithEncoder(1.0, 10, 60000));
	
	addSequential(new auto_moveUpperPosition(2800,525)); //525, 500 practice
	addSequential(new auto_wait(0.35));
	addParallel(new auto_moveUpperPosition(2800,60)); //525, 500 practice
	addSequential(new auto_LiftDown(90, 150));
	addSequential(new auto_liftStop());
	
	}
}
