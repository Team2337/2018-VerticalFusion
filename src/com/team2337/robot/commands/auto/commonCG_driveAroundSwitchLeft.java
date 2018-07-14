package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_driveAroundSwitchLeft extends CommandGroup {

	public commonCG_driveAroundSwitchLeft() {
		addSequential(new auto_resetEncoder());
		addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 42000, 42000, 0.04));
		
		addSequential(new auto_gyroMMTurn(70, 0.75));
		addSequential(new auto_driveToAngleWithEncoder(.7, 8, 70, 103000, 103000, 0.025, true));
		//Changed dist from 107000 to 102000 to compensate for the worn treads
		addSequential(new auto_driveToAngleWithEncoder(.6, 8, 0, 190000, 190000, 0.01)); //187
		addSequential(new auto_brakeModeOn());
		addSequential(new auto_clawCGOpenClose());
		addParallel(new auto_moveUpperPosition(3300,45)); //80 practice 
		addSequential(new auto_resetEncoder());
		addSequential(new auto_clawOpen());
		addSequential(new auto_gyroMMTurn(45, 0.75));
		addParallel(new auto_driveToAngleWithEncoder(-.5, 8, 45, 17000, 17000, 0.04, true));
		addSequential(new auto_wait(0.8));
		addParallel(new auto_intake_in(1, 3));
		addSequential(new auto_wait(1));
		addSequential(new auto_clawClose());
		addSequential(new auto_claw60());
//		addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 9000, 9000, 0.04, true));

	}
}
