package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scaleFromRightScoreOnSide extends CommandGroup {

	String ourSwitch, scale;
	public CG_scaleFromRightScoreOnSide(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		if ( (ourSwitch.equals("L") && scale.equals("R"))  || (ourSwitch.equals("R") && scale.equals("R")) ) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			/* Working code
			addSequential (new auto_driveToAngleWithEncoder(.5,10,0,205379,205379,.13));
			addSequential (new auto_brakeModeOn());
			addSequential (new auto_gyroMMTurn(-90));
			*/
			addSequential (new auto_driveToAngleWithEncoder(.7,10,0,80000,80000,.13));
			addSequential (new auto_driveToAngleWithEncoder(.6,10,0,160000,160000,.13));
			addParallel (new auto_moveUpperPosition(2127, 500));
			addSequential (new auto_driveToAngleWithEncoderandLine(.5,10,0,160000,160000,.13)); 
			addSequential (new auto_brakeModeOn());
			addSequential(new auto_wait(1));
//			addSequential (new auto_MMdrive(0));
			addSequential (new auto_gyroMMTurn(90, 1));
//				addSequential (new auto_moveUpperPosition(2127, 500));
//				addSequential (new auto_wait(1));
			addSequential (new auto_LiftUp(600));
			addSequential (new auto_resetEncoder());
			addSequential (new auto_driveToAngleWithEncoder(.5,10,90,5000,5000,.13));
			addSequential (new auto_moveUpperPosition(1850, 500));
			addSequential (new auto_wait(0.5));
			addSequential (new auto_clawOpen());
				//addSequential (new auto_wait(.5));			
				//addSequential (new auto_MMdrive(12250));
			//turn back to cube at switch
				//addSequential (new auto_gyroMMTurn(53));
			
			//Distance back to cube after turn is 45800
		//Switch is on our side scale is NOT  LRL
		//Score switch on side, get cube, drive to centerline	
		} else if (ourSwitch.equals("L")  && scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			
		//Switch is NOT on our side scale is  RLR
		//  We should never get here as it is covered in the first if, but if we diverge....	
		} else if (ourSwitch.equals("R")  && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			
		//You choose poorly.  Switch and scale are NOT on our side.  RRR	
		//Drive to Center line?	
		} else {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential (new auto_driveToAngleWithEncoder(.5,10,0,165000,165000,.13));
			addSequential (new auto_brakeModeOn());
			addSequential (new auto_gyroMMTurn(90, 2));
			//addSequential (new auto_driveToAngleWithEncoder(.5,10,-90,82500,82500,.13));
			
		}
		//Switch and scale are on our side  LLL   OR   Switch is NOT on our side scale is  RLR  These are the same
		
		
	
	}
}
