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
 
		addSequential(new auto_driveToAngleWithEncoder(.9, 5, 40, 40000, 44000, 0.04));
		
		addSequential(new shifter_high());
		addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 40000, 69000, 0.02));
		
		
		addSequential(new auto_moveUpperPosition(-250,60));
		addParallel(new auto_driveForwardWithTime(.3, 1));
		addSequential(new claw_open());
		addParallel(new auto_intakeOut(0.5,1));
		
		

		addSequential(new auto_wait(1.5));
		addSequential(new auto_moveUpperPosition(50,386));
		

	}
}
