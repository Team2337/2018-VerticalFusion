package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scaleFromLeftScoreOnSide extends CommandGroup {

	String ourSwitch, scale;
	public CG_scaleFromLeftScoreOnSide(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// Score Scale, get cube, Score Scale, get cube,  Score Scale
				if  (ourSwitch.equals("L") && scale.equals("L")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					addSequential (new auto_driveToAngleWithEncoder(.7,10,0,80000,80000,.13));
					addParallel (new auto_moveUpperPosition(2127, 500));
					addSequential (new auto_driveToAngleWithEncoder(.6,10,0,140000,140000,.13));
					addParallel (new auto_moveUpperPosition(2300, 500));
					addSequential (new auto_driveToAngleWithEncoderandLine(.5,10,0,160000,160000,.13)); 
					addSequential (new auto_brakeModeOn());
					addSequential (new auto_moveUpperPosition(2300, 500));
					addSequential (new auto_wait(1));
					addSequential (new auto_LiftUp(600));
					
					addSequential(new shifter_low());
//						addSequential (new auto_MMdrive(0));
					addSequential (new auto_gyroMMTurn(-90, 1));
					addParallel (new auto_driveForwardWithTime(0,1));
					addSequential (new auto_moveUpperPosition(1850, 500));
					addSequential (new auto_wait(0.5));
					addSequential(new auto_intakeOut(0.8,2));
					addSequential (new auto_clawOpen());
					addSequential (new auto_moveUpperPosition(2800, 500));
					addSequential (new auto_wait(1));
					addSequential (new auto_LiftDown(100, 120));
					addSequential (new auto_moveUpperPosition(2800, 80));
//					addSequential (new auto_gyroMMTurn(0, 1));
//					addSequential (new auto_resetEncoder());
//					addSequential (new auto_driveToAngleWithEncoder(.5,10,-90,5000,5000,.13));
//					addSequential (new auto_moveUpperPosition(1850, 500));
//					addSequential (new auto_wait(0.5));
//					addSequential (new auto_clawOpen());
					
					
					
				//Switch is NOT on our side scale is  LRL
				} else if (ourSwitch.equals("L")  && scale.equals("R")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					
					
					
				//Switch is on our side scale is NOT  RLR
				} else if (ourSwitch.equals("R")  && scale.equals("L")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					
					
					
					
				//Switch and scale are on our side.  RRR	
				} else {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!

					
				}
		//Switch and scale are NOT on our side  LLL
		
		
	
	}
}
