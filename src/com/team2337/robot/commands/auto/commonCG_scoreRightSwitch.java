package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_scoreRightSwitch extends CommandGroup {

	public commonCG_scoreRightSwitch() {
		addSequential(new auto_driveToAngleWithEncoder(.9, 4, -40, 44000, 44000, 0.0175)); // right was 44000 left was 28000 left was 22000 
		addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 60000, 60000, 0.02));
		addSequential(new auto_moveUpperPosition(1800,60));  //-250,60
		addParallel(new auto_driveForwardWithTime(.3, 1));
		addSequential(new auto_clawOpenWithCollision(4));
		addSequential (new auto_intakeOut(.3,1));
		addSequential(new auto_wait(1));
		addSequential(new auto_resetEncoder());
	}
}
