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
		addSequential(new auto_moveUpperPosition(2900,525)); //50,386  ///Touch down 
		addSequential(new auto_wait(0.25));
		addSequential(new auto_moveUpperPosition(2127,525));
		addSequential(new auto_wait(0.35));
		addSequential(new auto_moveUpperPosition(2127,80));
		addSequential(new auto_wait(0.25));
		addSequential(new bigBrother_defensivePosHold());
	}
	
	

}
