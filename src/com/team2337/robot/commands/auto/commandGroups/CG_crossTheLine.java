package com.team2337.robot.commands.auto.commandGroups;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.auto.auto_bigBrother_DoNothing;
import com.team2337.robot.commands.auto.auto_wait;
import com.team2337.robot.commands.auto.common.arm_trolley.commonCG_armToPickupFromFront;
import com.team2337.robot.commands.auto.driveCommands.brakeMode.auto_brakeModeOff;
import com.team2337.robot.commands.auto.driveCommands.brakeMode.auto_brakeModeOn;
import com.team2337.robot.commands.auto.driveCommands.driveWithEncoder.auto_driveToAngleWithEncoder;
import com.team2337.robot.commands.auto.upperPosition.auto_holdUpperPosition;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_crossTheLine extends CommandGroup {

	public CG_crossTheLine() {	
	
			addParallel(new auto_bigBrother_DoNothing());
			addSequential(new auto_holdUpperPosition(0.1));
			//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!

			addSequential(new auto_driveToAngleWithEncoder(.7, 4, 0, 65000, 65000, 0.04));// speed was .9 
			addSequential (new auto_brakeModeOn());
			addSequential(new auto_wait(1));
			addSequential(new commonCG_armToPickupFromFront());
			addSequential (new auto_brakeModeOff());
		
	
	}
}
