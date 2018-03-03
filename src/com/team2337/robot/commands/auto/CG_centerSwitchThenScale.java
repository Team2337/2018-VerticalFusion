package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_centerSwitchThenScale extends CommandGroup {

	String ourSwitch, scale;
	public CG_centerSwitchThenScale(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// **************************  LLL  **************************
		// Score in switch, get cube, score in scale.
		if  (ourSwitch.equals("L") && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential(new auto_driveToAngleWithEncoder(.9, 4, 40, 28000, 44000, 0.04));// speed was .9 
			addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 65000, 69000, 0.02));
			addSequential(new auto_moveUpperPosition(1900,60));  //-250,60  ///Score into Switch position
			addParallel(new auto_driveForwardWithTime(.3, 1));
			addSequential(new claw_open());
			addParallel(new auto_intakeOut(0.5,1));
			addSequential(new auto_wait(1.5));
			addSequential(new auto_moveUpperPosition(2127,550));  //50,386  ///Touch down
			addSequential(new auto_wait(4));
			addSequential(new auto_moveUpperPosition(2700,550)); // Move to 10
			addSequential(new auto_wait(1.5));
			addSequential(new auto_moveUpperPosition(2700,100)); // Move to 0
			
			
			
		// **************************  LRL  **************************
		// Score in switch, get cube drive to center.	
		} else if (ourSwitch.equals("L")  && scale.equals("R")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential(new auto_driveToAngleWithEncoder(.9, 4, 40, 28000, 44000, 0.04));// speed was .9 
			addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 65000, 69000, 0.02));
			addSequential(new auto_moveUpperPosition(1900,60));  //-250,60  ///Score into Switch position
			addParallel(new auto_driveForwardWithTime(.3, 1));
			addSequential(new claw_open());
			addParallel(new auto_intakeOut(0.5,1));
			addSequential(new auto_wait(1.5));
			addSequential(new auto_moveUpperPosition(2127,550));  //50,386  ///Touch down
			addSequential(new auto_wait(4));
			addSequential(new auto_moveUpperPosition(2700,550));
			addSequential(new auto_wait(1.5));
			addSequential(new auto_moveUpperPosition(2700,100));
			
			
			
		// **************************  RLR  **************************
		// Score in switch, get cube drive to center.		
		} else if (ourSwitch.equals("R")  && scale.equals("L")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!		
			addSequential(new auto_driveToAngleWithEncoder(.9, 4, -40, 18000, 44000, 0.04)); // right was 44000 left was 28000 left was 22000 
			addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 65000, 69000, 0.02));
			addSequential(new auto_moveUpperPosition(1900,60));  //-250,60
			addParallel(new auto_driveForwardWithTime(.3, 1));
			addSequential(new claw_open());
			addParallel(new auto_intakeOut(0.5,1));
			addSequential(new auto_wait(1.5));
			addSequential(new auto_moveUpperPosition(2127,550));   //50,386
			addSequential(new auto_wait(4));
			addSequential(new auto_moveUpperPosition(2700,550));
			addSequential(new auto_wait(1.5));
			addSequential(new auto_moveUpperPosition(2700,100));
			
			
		// **************************  RRR  **************************
		// Score in switch, get cube, score in scale.			
		} else {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential(new auto_driveToAngleWithEncoder(.9, 4, -40, 18000, 44000, 0.04)); // right was 44000 left was 28000 left was 22000 
			addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 65000, 69000, 0.02));
			addSequential(new auto_moveUpperPosition(1900,60));  //-250,60
			addParallel(new auto_driveForwardWithTime(.3, 1));
			addSequential(new claw_open());
			addParallel(new auto_intakeOut(0.5,1));
			addSequential(new auto_wait(1.5));
			addSequential(new auto_moveUpperPosition(2127,550));   //50,386
			addSequential(new auto_wait(4));
			addSequential(new auto_moveUpperPosition(2700,550));
			addSequential(new auto_wait(1.5));
			addSequential(new auto_moveUpperPosition(2700,100));
			
		}
		
	
	}
}
