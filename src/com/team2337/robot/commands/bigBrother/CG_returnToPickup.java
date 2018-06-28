package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.Robot;
import com.team2337.robot.commands.auto.auto_armMoveWithFinish;
import com.team2337.robot.commands.auto.auto_bigBrother_DoNothing;
import com.team2337.robot.commands.auto.auto_brakeModeOff;
import com.team2337.robot.commands.auto.auto_moveUpperPosition;
import com.team2337.robot.commands.auto.auto_moveUpperPositionWithIsFinished;
import com.team2337.robot.commands.auto.auto_wait;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_returnToPickup extends CommandGroup {

	public CG_returnToPickup() {
		requires(Robot.bigBrother);
	addSequential(new auto_armMoveWithFinish(2000,1900));
	addSequential(new auto_moveUpperPositionWithIsFinished(2000,550,500)); ///Touch down
	addSequential(new auto_moveUpperPosition(2900,550)); // Move to 10
	addSequential(new auto_wait(.5));
	addSequential(new auto_moveUpperPosition(2900,60)); // Move to 0
	addSequential(new auto_wait(.5));
	addSequential(new auto_moveUpperPosition(3300,60)); // Move to 0
	}

}
