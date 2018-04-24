package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_driveAroundNearSwitchRight extends CommandGroup {

	public commonCG_driveAroundNearSwitchRight() {
		addSequential(new auto_driveToAngleWithEncoder(-.6, 3, 0, 27000, 27000, 0.02));
		addSequential(new auto_resetEncoder());
		addSequential(new auto_driveToAngleWithEncoder(.6, 4, -35, 45000, 45000, 0.015));
		addSequential(new auto_driveToAngleWithEncoder(.6, 4, 0, 120000, 120000, 0.025));
		addSequential(new auto_brakeModeOn());
		addSequential(new auto_clawCGOpenClose());
		addParallel(new auto_moveUpperPosition(3300,45)); //100 practice bot
		addSequential(new auto_resetEncoder());
		addSequential(new auto_clawOpen());
		addParallel(new auto_driveToAngleWithEncoder(-.5, 8, -42, 18000, 18000, 0.04, true));
		addSequential(new auto_wait(0.8));
		addParallel(new auto_intake_in(1, 3));
		addSequential(new auto_wait(1));
		addSequential(new auto_clawClose());
		addSequential(new auto_claw60());
//		addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 9000, 9000, 0.04, true));
	}
}
