package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_centerSwitch extends CommandGroup {

	String ourSwitch, scale;
	public CG_centerSwitch(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		if (ourSwitch.equals("L") ||ourSwitch.equals("l")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			///////////////addSequential(new auto_moveUpperPosition(-600,386));
			////////////addSequential(new auto_wait(0.25));
			addSequential(new auto_driveToAngleWithEncoder(.5, 5, 40, 28000, 44000, 0.04));// speed was .9 
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 65000, 69000, 0.02));
			//addSequential(new auto_moveUpperPosition(-250,60));  //-250,60
			addParallel(new auto_driveForwardWithTime(.3, 1));
			addSequential(new claw_open());
			addParallel(new auto_intakeOut(0.5,1));
			addSequential(new auto_wait(1.5));
			//addSequential(new auto_moveUpperPosition(50,386));  //50,386
			
		} else {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential(new shifter_low());
			addSequential(new auto_driveToAngleWithEncoder(.5, 5, -40, 28000, 44000, 0.04));
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 65000, 69000, 0.02));
			//addSequential(new auto_moveUpperPosition(-250,60));  //-250,60
			addParallel(new auto_driveForwardWithTime(.3, 1));
			addSequential(new claw_open());
			addParallel(new auto_intakeOut(0.5,1));
			addSequential(new auto_wait(1.5));
			//addSequential(new auto_moveUpperPosition(50,386));   //50,386
			
		}
		
	
	}
}
