package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.Robot;
import com.team2337.robot.commands.auto.auto_bigBrother_DoNothing;
import com.team2337.robot.commands.auto.auto_brakeModeOff;
import com.team2337.robot.commands.auto.auto_moveUpperPosition;
import com.team2337.robot.commands.auto.auto_wait;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_defenseMode extends CommandGroup {

	public CG_defenseMode() {
		requires(Robot.bigBrother);
		addSequential(new auto_moveUpperPosition(2850, 550)); // Move to 10
		addSequential(new auto_wait(1));
		addSequential(new auto_moveUpperPosition(2000, 550)); // Move to 10
		addSequential(new auto_wait(1));
		addSequential(new auto_moveUpperPosition(2000, 100)); // Move to 10
		addSequential(new auto_bigBrother_DoNothing());
	}

}
