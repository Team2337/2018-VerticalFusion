package com.team2337.robot.commands.auto.commandGroups.centerSwitch;


import com.team2337.robot.commands.auto.auto_bigBrother_DoNothing;
import com.team2337.robot.commands.auto.common.arm_trolley.commonCG_armToPickupFromFront;
import com.team2337.robot.commands.auto.common.backout.commonCG_switchBackoutLeftToLeft;
import com.team2337.robot.commands.auto.common.backout.commonCG_switchBackoutLeftToRight;
import com.team2337.robot.commands.auto.common.backout.commonCG_switchBackoutRightToLeft;
import com.team2337.robot.commands.auto.common.backout.commonCG_switchBackoutRightToRight;
import com.team2337.robot.commands.auto.common.dontScore.commonCG_rightSwitchDontScore;
import com.team2337.robot.commands.auto.upperPosition.auto_holdUpperPosition;
import com.team2337.robot.commands.claw.claw_CGOpenClose;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_centerSwitchThenGoAroundSwitch extends CommandGroup {

	String ourSwitch, scale;
	public CG_centerSwitchThenGoAroundSwitch(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// **************************  LLL  **************************
		// Score in switch, get cube, score in scale.
		if  (ourSwitch.equals("L") && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
//			addSequential(new commonCG_scoreLeftSwitch());
			addSequential(new commonCG_armToPickupFromFront());
			addSequential(new commonCG_switchBackoutLeftToLeft());
			
			
		// **************************  LRL  **************************
		// Score in switch, get cube drive to center.	
		} else if (ourSwitch.equals("L")  && scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
//			addSequential(new commonCG_scoreLeftSwitch());
			addSequential(new commonCG_armToPickupFromFront());
			addSequential(new commonCG_switchBackoutLeftToRight());
			
			
			
		// **************************  RLR  **************************
		// Score in switch, get cube drive to center.		
		} else if (ourSwitch.equals("R")  && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
//			addSequential(new commonCG_scoreRightSwitch());
			addSequential(new commonCG_armToPickupFromFront());
			addSequential(new commonCG_switchBackoutRightToLeft());
			
			
		// **************************  RRR  **************************
		// Score in switch, get cube, score in scale.			
		} else if (ourSwitch.equals("R")  && scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
//			addSequential(new commonCG_scoreRightSwitch());
  			addSequential(new commonCG_armToPickupFromFront());
  			addSequential(new commonCG_switchBackoutRightToRight());
			
  			//didnt get any game data, so we run into switch and hope we guessed right
		} else {
			addSequential(new commonCG_rightSwitchDontScore());
		}
		
	
	}
}
