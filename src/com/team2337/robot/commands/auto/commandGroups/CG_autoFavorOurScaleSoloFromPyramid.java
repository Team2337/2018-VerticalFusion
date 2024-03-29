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
//			addSequential(new commonCG_grabPyramidCubeFromLeftSwitch());
			
			
			addParallel(new commonCG_armToPickupFromFront());
			
			addSequential(new auto_wait(.25));
			
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 23500, 23500, 0.04, "Left"));
			addSequential(new auto_clawOpen());
			
			addSequential(new auto_gyroMMTurn(98, 0.9));
			addSequential(new auto_moveUpperPosition(3300,45)); // 80 practice);
			addSequential(new auto_resetEncoder());
			
			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, 98, 20000, 20000, 0.025, "Left"));
			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, 85, 32000, 32000, 0.04, "Left"));
			

			
			addSequential(new auto_wait(.75));
			addSequential(new auto_clawClose());
			addSequential(new auto_claw60());
			addSequential(new auto_wait(.25));
			addSequential(new auto_moveUpperPosition(2700,45));
			
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(.7, 8, 95, 40000, 40000, 0.025, "Right"));
			addParallel(new auto_intake_in(0.5, 1));
			
			addSequential(new auto_driveToAngleWithEncoder(.7, 8, 95, 85000, 85000, 0.025, "Right"));
			addSequential(new auto_driveToAngleWithEncoder(.7, 8, 2, 180000, 180000, 0.03, "Right"));
			addSequential(new auto_brakeModeOn());
			
			
			
		// **************************  LRL  **************************
		// Score in switch, get cube drive to center.	
		} else if (ourSwitch.equals("L")  && scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential(new commonCG_scoreLeftSwitch());
			
			//grab Pyramid Cube From Right Switch
			addParallel(new commonCG_armToPickupFromFront());

			addSequential(new auto_wait(.25));

			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 29000, 29000, 0.04, "Right"));
			addSequential(new auto_clawOpen());

			addSequential(new auto_gyroMMTurn(98, 0.9));
			addSequential(new auto_moveUpperPosition(3300, 45)); // 80 practice);
			addSequential(new auto_resetEncoder());

			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, 98, 22000, 22000, 0.025, "Right"));
			addParallel(new auto_intake_in(0.5, 1));
			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, 85, 32000, 32000, 0.04, "Right"));
			
			
			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, 85, 93000, 93000, 0.04, "Left"));	//44000
//				addSequential(new auto_wait(0.7));
				addSequential(new auto_clawClose());
				addSequential(new auto_claw60());
			
			addSequential(new auto_driveToAngleWithEncoder(-.6, 8, 178, 130000, 130000, 0.025, "Right"));
			//changed angle to 178 from 175 before q88
			addSequential(new auto_moveUpperPosition(2700,525));
			addParallel(new auto_intake_in(0.5, 0.2));
			addSequential(new auto_driveToAngleWithEncoder(-.6, 8, 176, 160000, 160000, 0.03, "Right"));
			//changed angle to 176 from 175 before q88
			addSequential(new auto_moveUpperPosition(2500,525));
			addSequential(new auto_driveToAngleWithEncoder(-.6, 8, 173.5, 235000, 235000, 0.03, "Right")); 
			//changed angle from 175 to 173.5 dist to 235000 from 230000before q88
			addSequential(new auto_brakeModeOn());
			
			
			//Score on side
			addSequential (new auto_wait(0.5));	//changed from 1 to .5 before q88
			addSequential(new shifter_low());
			addSequential (new auto_gyroMMTurn(100, 1)); //changed to 100 from 90 before q88
			addParallel (new auto_driveForwardWithTime(0,1));
			addSequential(new auto_intakeOut(1, 1));
			addSequential (new auto_wait(1));
			addSequential (new auto_moveUpperPosition(2800, 525));
			addSequential (new auto_wait(1));
			addSequential (new auto_moveUpperPosition(2800, 80));

			addSequential(new auto_brakeModeOff());
			
			
		// **************************  RLR  **************************
		// Score in switch, get cube drive to center.		
		} else if (ourSwitch.equals("R")  && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
			addSequential(new commonCG_scoreRightSwitch());
			addSequential(new commonCG_armToPickupFromFront());
//			addSequential(new commonCG_grabPyramidCubeFromRightSwitch());
			
			
			addParallel(new commonCG_armToPickupFromFront());

			addSequential(new auto_wait(.25));

			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 27500, 27500, 0.04, "Right"));
			addSequential(new auto_clawOpen());

			addSequential(new auto_gyroMMTurn(-105, 0.9));
			addSequential(new auto_moveUpperPosition(3300, 45)); // 80 practice);
			addSequential(new auto_resetEncoder());

			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, -105, 22000, 22000, 0.025, "Right"));
			addParallel(new auto_intake_in(0.5, 1));
			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, -90, 32000, 32000, 0.025, "Right"));
			
			
			
			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, -85, 85000, 85000, 0.04, "Left"));	//44000
//			addSequential(new auto_wait(0.7));
			addSequential(new auto_clawClose());
			addSequential(new auto_claw60());
		
		addSequential(new auto_driveToAngleWithEncoder(-.6, 8, -178, 130000, 130000, 0.03, "Right"));
		addSequential(new auto_moveUpperPosition(2700,525));
		addParallel(new auto_intake_in(0.5, 0.2));
		addSequential(new auto_driveToAngleWithEncoder(-.6, 8, -178, 160000, 160000, 0.03, "Right"));
		addSequential(new auto_moveUpperPosition(2500,525));
		addSequential(new auto_driveToAngleWithEncoder(-.6, 8, -178, 230000, 230000, 0.03, "Right"));
		addSequential(new auto_brakeModeOn());
		
		//Score on side
		/*
		addSequential(new shifter_low());
		addSequential (new auto_gyroMMTurn(-90, 1));
		addParallel (new auto_driveForwardWithTime(0,1));
		addSequential(new auto_intakeOut(1, 1));
		addSequential (new auto_wait(1));
		addSequential (new auto_moveUpperPosition(2800, 525));
		addSequential (new auto_wait(1));
		addSequential (new auto_moveUpperPosition(2800, 80));

		addSequential(new auto_brakeModeOff());
		*/
			
		// **************************  RRR  **************************
		// Score in switch, get cube, score in scale.			
		} else if (ourSwitch.equals("R")  && scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
			addSequential(new commonCG_scoreRightSwitch());
			
			
			//grab Pyramid Cube From Right Switch
			addParallel(new commonCG_armToPickupFromFront());

			addSequential(new auto_wait(.25));

			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 27500, 27500, 0.04, "Right"));
			addSequential(new auto_clawOpen());

			addSequential(new auto_gyroMMTurn(-105, 0.9));
			addSequential(new auto_moveUpperPosition(3300, 45)); // 80 practice);
			addSequential(new auto_resetEncoder());

			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, -105, 22000, 22000, 0.025, "Right"));
			addParallel(new auto_intake_in(0.5, 1));
			addSequential(new auto_driveToAngleWithEncoder(-.7, 8, -85, 32000, 32000, 0.04, "Right"));
			
			
			addSequential(new auto_wait(.75));
			addSequential(new auto_clawClose());
			addSequential(new auto_claw60());
			addSequential(new auto_wait(.25));
			addSequential(new auto_moveUpperPosition(2700,45));
			
			addSequential(new auto_resetEncoder());
			addSequential(new auto_driveToAngleWithEncoder(.7, 8, -95, 40000, 40000, 0.025, "Right"));
			addParallel(new auto_intake_in(0.5, 1));
			addSequential(new auto_driveToAngleWithEncoder(.7, 8, -95, 85000, 85000, 0.025, "Right"));
			addSequential(new auto_driveToAngleWithEncoder(.7, 8, -2, 180000, 180000, 0.03, "Right"));
			addSequential(new auto_brakeModeOn());
			
			//didnt get any game data, so we run into switch and hope we guessed right
		} else {
			addSequential(new commonCG_rightSwitchDontScore());
		}
		
	
	}
}
