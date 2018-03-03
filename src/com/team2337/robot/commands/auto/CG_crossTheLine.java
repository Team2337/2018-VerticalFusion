package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_crossTheLine extends CommandGroup {

	String ourSwitch, scale;
	public CG_crossTheLine(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
	
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!

			addSequential(new auto_driveToAngleWithEncoder(.5, 4, 0, 92400, 92400, 0.04));// speed was .9 
			addSequential(new auto_wait(1.5));
			addSequential(new auto_moveUpperPosition(2127,550)); ///Touch down
			addSequential(new auto_wait(4));
			addSequential(new auto_moveUpperPosition(2700,550)); // Move to 10
			addSequential(new auto_wait(1.5));
			addSequential(new auto_moveUpperPosition(2700,100)); // Move to 0
			
	
		
	
	}
}
