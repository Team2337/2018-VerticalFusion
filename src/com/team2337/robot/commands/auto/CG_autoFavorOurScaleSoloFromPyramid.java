package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_autoFavorOurScaleSoloFromPyramid extends CommandGroup {

	String ourSwitch, scale;
	public CG_autoFavorOurScaleSoloFromPyramid(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// **************************  LLL  **************************
		// Score in switch, get cube, score in scale.
		if  (ourSwitch.equals("L") && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential(new commonCG_scoreLeftSwitch());
			addSequential(new commonCG_armToPickupFromFront());
			addSequential(new commonCG_driveAroundNearSwitchLeft());
			
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 9000, 9000, 0.04, true));
			
			addSequential(new auto_brakeModeOff());
			
		// **************************  LRL  **************************
		// Score in switch, get cube drive to center.	
		} else if (ourSwitch.equals("L")  && scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
//				addSequential(new commonCG_scoreLeftSwitch());
			addParallel(new commonCG_armToPickupFromFront());
//			addSequential(new commonCG_driveAroundSwitchRight());
			addSequential(new auto_wait(.25));
			
			
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 24000, 24000, 0.04));
			addSequential(new auto_clawOpen());
			
			addSequential(new auto_gyroMMTurn(105, 0.75));
			addSequential(new auto_moveUpperPosition(3300,45)); // 80 practice);
			addSequential(new auto_resetEncoder());
			
			
			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, 105, 20000, 20000, 0.025, "Left"));
			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, 85, 85000, 85000, 0.04, "Left"));	//44000
//				addSequential(new auto_wait(0.7));
				addSequential(new auto_clawClose());
				addSequential(new auto_claw60());
			
			addSequential(new auto_driveToAngleWithEncoder(-.6, 8, 180, 130000, 130000, 0.03, "Right"));
			addSequential(new auto_moveUpperPosition(2700,45));
			addSequential(new auto_driveToAngleWithEncoder(-.6, 8, 180, 200000, 200000, 0.03, "Right"));
			addSequential(new auto_brakeModeOn());
			/*
			addSequential(new auto_clawCGOpenClose());
			addParallel(new auto_moveUpperPosition(3300,45)); // 80 practice
			addSequential(new auto_resetEncoder());
			addSequential(new auto_clawOpen());
			addParallel(new auto_driveToAngleWithEncoder(-.5, 8, -45, 16000, 16000, 0.04, true));
			addSequential(new auto_wait(0.8));
			addParallel(new auto_intake_in(1, 3));
			addSequential(new auto_wait(1));
			addSequential(new auto_clawClose());
			addSequential(new auto_claw60());
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 9000, 9000, 0.04, true));
			
			
			

			
			
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 9000, 9000, 0.04, true));
			
			*/
//			addSequential(new auto_driveToAngleWithEncoderandLine(.5, 3, 0, 9000, 9000, 0.04));
			
//			addSequential (new CG_scorePosition());
//			addSequential (new auto_clawOpen());
			
			
			
			addSequential(new auto_brakeModeOff());
			
		// **************************  RLR  **************************
		// Score in switch, get cube drive to center.		
		} else if (ourSwitch.equals("R")  && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
			addSequential(new commonCG_scoreRightSwitch());
			addSequential(new commonCG_armToPickupFromFront());
			addSequential(new commonCG_driveAroundSwitchLeft());
			
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 9000, 9000, 0.04, true));
//			addSequential(new auto_driveToAngleWithEncoderandLine(.5, 3, 0, 9000, 9000, 0.04));
			
//			addSequential (new CG_scorePosition());
//			addSequential (new auto_clawOpen());
			
			
			addSequential(new auto_brakeModeOff());
			
		// **************************  RRR  **************************
		// Score in switch, get cube, score in scale.			
		} else if (ourSwitch.equals("R")  && scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
			addSequential(new commonCG_scoreRightSwitch());
			addSequential(new commonCG_armToPickupFromFront());
			addSequential(new commonCG_driveAroundNearSwitchRight());
			
			
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 9000, 9000, 0.04, true));
//			addSequential(new auto_driveToAngleWithEncoderandLine(.5, 3, 0, 9000, 9000, 0.04));
			
//			addSequential (new CG_scorePosition());
//			addSequential (new auto_clawOpen());
			
			addSequential(new auto_brakeModeOff());
			
			//didnt get any game data, so we run into switch and hope we guessed right
		} else {
			addSequential(new commonCG_rightSwitchDontScore());
		}
		
	
	}
}
