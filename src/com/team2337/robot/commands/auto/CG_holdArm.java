package com.team2337.robot.commands.auto;

import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_holdArm extends CommandGroup {
	public CG_holdArm() {
		
		addParallel(new auto_bigBrother_DoNothing());
		
		addSequential(new auto_holdUpperPosition(0.1));
		//addSequential(new auto_moveUpperPosition(-600,386));
		//addSequential(new auto_wait(0.25));
 
		addSequential(new auto_driveToAngleWithEncoder(.5, 5, 35, 40000, 55708, 0.04));
		
		addSequential(new shifter_high());
		addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 40000, 80000, 0.02));
		
		
		addSequential(new auto_moveUpperPosition(-100,60));
		addSequential(new auto_intakeOut(1,1));
		
		//addSequential(new claw_open());
		addSequential(new auto_wait(1.5));
		addSequential(new auto_moveUpperPosition(50,386));
		

	}
}
