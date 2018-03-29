package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_switchBackoutLeftToLeft extends CommandGroup {

	public commonCG_switchBackoutLeftToLeft() {
		addSequential(new auto_driveToAngleWithEncoder(-.6, 3, 0, 30000, 30000, 0.02));
		addSequential(new auto_resetEncoder());
		addSequential(new auto_driveToAngleWithEncoder(.6, 4, 50, 50000, 50000, 0.015));
		addSequential(new auto_driveToAngleWithEncoder(.5, 4, 0, 70000, 70000, 0.03));
		addSequential(new auto_brakeModeOn());
		addSequential(new auto_clawCGOpenClose());

	}
}
