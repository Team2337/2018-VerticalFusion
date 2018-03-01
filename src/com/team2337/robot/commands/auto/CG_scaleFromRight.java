package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scaleFromRight extends CommandGroup {

	String ourSwitch, scale;
	public CG_scaleFromRight(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		if (scale.equals("L") ||scale.equals("l")) {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential (new auto_driveToAngleWithEncoder(.5,10,0,201188,201188,.13));
			addSequential (new auto_brakeModeOn());
			addSequential (new auto_gyroMMTurn(-90));
			
		} else {
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
			addSequential (new auto_driveToAngleWithEncoder(.5,10,0,201188,201188,.13));
			addSequential (new auto_brakeModeOn());
			addSequential (new auto_gyroMMTurn(90));
			
		}
		
	
	}
}
