package com.team2337.robot.commands.auto.common.arm_trolley;

import com.team2337.robot.commands.auto.auto_wait;
import com.team2337.robot.commands.auto.lift.auto_LiftUp;
import com.team2337.robot.commands.auto.upperPosition.auto_moveUpperPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_scorePositionFromPickUp extends CommandGroup{

	public commonCG_scorePositionFromPickUp() {
		addSequential (new auto_moveUpperPosition(2800, 500));
		addSequential (new auto_wait(0.5));
		addSequential (new auto_moveUpperPosition(2300, 500));
		addSequential (new auto_wait(0.5));
		addSequential (new auto_moveUpperPosition(2300, 500));
		addSequential (new auto_wait(1));
		addSequential (new auto_LiftUp(580)); //600 practice
	}
}
