package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_grabPyramidCubeFromLeftSwitch extends CommandGroup {

	public commonCG_grabPyramidCubeFromLeftSwitch() {
		addParallel(new commonCG_armToPickupFromFront());
		
		addSequential(new auto_wait(.25));
		
		addSequential(new auto_resetEncoder());
		addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 23500, 23500, 0.04, "Left"));
		addSequential(new auto_clawOpen());
		
		addSequential(new auto_gyroMMTurn(105, 0.9));
		addSequential(new auto_moveUpperPosition(3300,45)); // 80 practice);
		addSequential(new auto_resetEncoder());
		
		addSequential(new auto_driveToAngleWithEncoder(-.7, 8, 105, 20000, 20000, 0.025, "Left"));
		addSequential(new auto_driveToAngleWithEncoder(-.7, 8, 85, 32000, 32000, 0.04, "Left"));
		
}
}
