package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_scoreLeftSwitch extends CommandGroup {

	public commonCG_scoreLeftSwitch() {
		addSequential(new auto_driveToAngleWithEncoder(.9, 4, 40, 50000, 50000, 0.0175));// speed was .9 
		addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 60000, 60000, 0.02));
		addSequential(new auto_moveUpperPosition(1800,60));  //-250,60  ///Score into Switch position
		addParallel(new auto_driveForwardWithTime(.3, 1));
		addSequential(new auto_clawOpenWithCollision(4));
		addSequential (new auto_intakeOut(.3,1));
		addSequential(new auto_resetEncoder());
	}
}
