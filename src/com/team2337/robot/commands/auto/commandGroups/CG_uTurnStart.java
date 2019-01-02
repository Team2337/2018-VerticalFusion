package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_uTurnStart extends CommandGroup {

	String ourSwitch, scale;
	public CG_uTurnStart(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		if (ourSwitch.equals("L") ||ourSwitch.equals("l")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			//addSequential(new auto_UTurnLeftRobin(.8, 5, 10)); //speed, timeout, radius: inches
			addSequential(new auto_UTurn(.1, -.8, 5, -175));
			//addSequential(new auto_wait(1));
			addSequential(new auto_driveToAngleWithTime(-.6, 1.2, -180));
			addSequential(new auto_driveToAngleWithTime(-1, .5, -180));
			
			
		} else {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential(new auto_UTurn(-0.8, .1, 5, 175));
			addSequential(new auto_driveToAngleWithTime(-.6, 1.2, 180));
			
		}
		
	
	}
}
