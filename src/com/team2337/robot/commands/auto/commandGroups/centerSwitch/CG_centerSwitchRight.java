package com.team2337.robot.commands.auto.commandGroups.centerSwitch;


import com.team2337.robot.commands.auto.auto_bigBrother_DoNothing;
import com.team2337.robot.commands.auto.common.arm_trolley.commonCG_armToPickupFromFront;
import com.team2337.robot.commands.auto.common.backout.commonCG_switchBackoutRightToRight;
import com.team2337.robot.commands.auto.common.scoreSwitch.commonCG_scoreRightSwitch;
import com.team2337.robot.commands.auto.upperPosition.auto_holdUpperPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_centerSwitchRight extends CommandGroup {

	String ourSwitch, scale;
	public CG_centerSwitchRight(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential(new commonCG_scoreRightSwitch());
  			addSequential(new commonCG_armToPickupFromFront());
  			addSequential(new commonCG_switchBackoutRightToRight());
			
		
	
	}
}
