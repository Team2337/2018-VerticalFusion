package com.team2337.robot.commands.auto.commandGroups.switchThenScale;


import com.team2337.robot.commands.auto.auto_bigBrother_DoNothing;
import com.team2337.robot.commands.auto.auto_wait;
import com.team2337.robot.commands.auto.claw.auto_clawCGOpenClose;
import com.team2337.robot.commands.auto.common.arm_trolley.commonCG_armToPickupFromFront;
import com.team2337.robot.commands.auto.common.scoreSwitch.commonCG_scoreLeftSwitch;
import com.team2337.robot.commands.auto.common.scoreSwitch.commonCG_scoreRightSwitch;
import com.team2337.robot.commands.auto.driveCommands.brakeMode.auto_brakeModeOff;
import com.team2337.robot.commands.auto.driveCommands.brakeMode.auto_brakeModeOn;
import com.team2337.robot.commands.auto.driveCommands.driveWithEncoder.auto_driveToAngleWithEncoder;
import com.team2337.robot.commands.auto.intake.auto_intake_in;
import com.team2337.robot.commands.auto.motionMagic.auto_gyroMMTurn;
import com.team2337.robot.commands.auto.resetCommands.auto_resetEncoder;
import com.team2337.robot.commands.auto.upperPosition.auto_holdUpperPosition;
import com.team2337.robot.commands.auto.upperPosition.auto_moveUpperPosition;
import com.team2337.robot.commands.claw.claw_CGOpenClose;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_centerSwitchThenScaleTheirSide extends CommandGroup {

	String ourSwitch, scale;
	public CG_centerSwitchThenScaleTheirSide(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// **************************  LLL  **************************
		// Score in left switch, drive right around opp switch to scale and get cube.
		if  (ourSwitch.equals("L") && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
//			addSequential(new commonCG_scoreLeftSwitch());
			addSequential(new auto_resetEncoder());
			
			addSequential(new auto_moveUpperPosition(2127,500));   ///Touch down
			addSequential(new auto_wait(0.175));
			addSequential(new auto_moveUpperPosition(2900,500));
			addSequential(new auto_wait(0.35));
			addSequential(new auto_moveUpperPosition(2900,100));
			addSequential(new auto_wait(0.25));
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 34000, 34000, 0.04));
			
			addSequential(new auto_gyroMMTurn(-75, 0.75));
			addSequential(new auto_driveToAngleWithEncoder(.7, 8, -75, 101000, 101000, 0.03, true));  
			addSequential(new auto_driveToAngleWithEncoder(.6, 8, 0, 174000, 174000, 0.015));
			addSequential(new auto_brakeModeOn());
			addSequential(new auto_clawCGOpenClose());
			addParallel(new auto_moveUpperPosition(3300,100));
			addSequential(new auto_resetEncoder());
			addParallel(new auto_driveToAngleWithEncoder(-.5, 8, -40, 18000, 18000, 0.04, true));
			addSequential(new auto_wait(1.2));
			addParallel(new auto_intake_in(1, 3));
			addSequential(new auto_driveToAngleWithEncoder(.3, 3, 0, 9000, 9000, 0.04, true)); //can't backup
			
			addSequential(new auto_brakeModeOff());
			
			
			
		// **************************  LRL  **************************
		// Score in left switch, drive left around our switch to scale and get cube.	
		} else if (ourSwitch.equals("L")  && scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential(new commonCG_scoreLeftSwitch());
			addSequential(new commonCG_armToPickupFromFront());

			
		// **************************  RLR  **************************
		// Score in right switch, drive right around our switch to scale and get cube.	
		} else if (ourSwitch.equals("R")  && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
			addSequential(new commonCG_scoreRightSwitch());
			addSequential(new commonCG_armToPickupFromFront());
			
		// **************************  RRR  **************************
		//Score in right switch, drive left around opp switch to scale and get cube.			
		} else {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
			addSequential(new commonCG_scoreRightSwitch());
			addSequential(new commonCG_armToPickupFromFront());
			
		}
		
	
	}
}
