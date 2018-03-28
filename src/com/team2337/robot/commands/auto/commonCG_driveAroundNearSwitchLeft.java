package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_driveAroundNearSwitchLeft extends CommandGroup {

	public commonCG_driveAroundNearSwitchLeft() {
		addSequential(new auto_driveToAngleWithEncoder(-.6, 3, 0, 30000, 30000, 0.02));
		addSequential(new auto_resetEncoder());
		addSequential(new auto_driveToAngleWithEncoder(.6, 4, 50, 50000, 50000, 0.015));
		addSequential(new auto_driveToAngleWithEncoder(.6, 4, 0, 115000, 115000, 0.03));
		addSequential(new auto_brakeModeOn());
		addSequential(new auto_clawCGOpenClose());
		addParallel(new auto_moveUpperPosition(3300, 45)); //100 practice bot
		addSequential(new auto_resetEncoder());
		addSequential(new auto_clawOpen());
		addParallel(new auto_driveToAngleWithEncoder(-.5, 8, 24, 16000, 16000, 0.04, true));
		addSequential(new auto_wait(0.8));
		addParallel(new auto_intake_in(1, 3));
		addSequential(new auto_wait(1));
		addSequential(new auto_clawClose());
		addSequential(new auto_claw60());
		addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 9000, 9000, 0.04, true));
		
	}
}
