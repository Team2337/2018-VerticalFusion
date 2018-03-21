package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_armToPickupFromFront extends CommandGroup {

	public commonCG_armToPickupFromFront() {
		addSequential(new auto_moveUpperPosition(2127,500));  //50,386  ///Touch down
		addSequential(new auto_wait(0.175));
		addSequential(new auto_moveUpperPosition(2900,500));
		addSequential(new auto_wait(0.35));
		addSequential(new auto_moveUpperPosition(2900,100));
		addSequential(new auto_wait(0.25));
	}
}
