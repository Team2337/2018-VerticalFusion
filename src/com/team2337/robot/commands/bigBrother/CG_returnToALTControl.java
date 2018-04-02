package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.Robot;
import com.team2337.robot.commands.auto.auto_bigBrother_DoNothing;
import com.team2337.robot.commands.auto.auto_wait;
import com.team2337.robot.commands.auto.driveCommands.brakeMode.auto_brakeModeOff;
import com.team2337.robot.commands.auto.upperPosition.auto_moveUpperPosition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_returnToALTControl extends CommandGroup {

	public CG_returnToALTControl() {
		requires(Robot.bigBrother);
	addSequential(new auto_moveUpperPosition(2127,550)); ///Touch down
	addSequential(new auto_wait(1));
	addSequential(new auto_moveUpperPosition(2700,550)); // Move to 10
	addSequential(new auto_wait(.5));
	addSequential(new auto_moveUpperPosition(2700,60)); // Move to 0
	}

}
