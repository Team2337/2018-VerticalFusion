package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_driveAroundNearSwitchLeft extends CommandGroup {

	public commonCG_driveAroundNearSwitchLeft() {
		addSequential(new auto_resetEncoder());
		addSequential(new auto_driveToAngleWithEncoder(.5, 4, 50, 38000, 38000, 0.015));
		addSequential(new auto_driveToAngleWithEncoder(.5, 4, 0, 105000, 105000, 0.03));
		
		addSequential(new auto_brakeModeOn());
		addSequential(new auto_clawCGOpenClose());
		addParallel(new auto_moveUpperPosition(3300,100));
		addSequential(new auto_resetEncoder());
		addParallel(new auto_driveToAngleWithEncoder(-.5, 8, 35, 16000, 16000, 0.04, true));
		addSequential(new auto_wait(1.2));
		addParallel(new auto_intake_in(1, 3));
		
	}
}
