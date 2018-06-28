package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.Robot;
import com.team2337.robot.commands.auto.auto_bigBrother_DoNothing;
import com.team2337.robot.commands.auto.auto_brakeModeOff;
import com.team2337.robot.commands.auto.auto_clawOpen;
import com.team2337.robot.commands.auto.auto_moveUpperPosition;
import com.team2337.robot.commands.auto.auto_wait;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_driverCubeLow extends CommandGroup {

	public CG_driverCubeLow() {
		requires(Robot.bigBrother);
//		addSequential(new auto_clawOpen());//Open Claw
		addSequential(new auto_moveUpperPosition(2900,525)); //50,386  ///Touch down 
		addSequential(new auto_wait(0.25)); //25
		addSequential(new auto_moveUpperPosition(2000,525));
		addSequential(new auto_wait(0.5));  //35
		addSequential(new auto_moveUpperPosition(800,410));
		addSequential(new auto_wait(0.5));  //25
		addSequential(new bigBrother_defensivePosHold());
	}
	
	

}
