package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_grabPyramidCubeFromRightSwitch extends CommandGroup {

	public commonCG_grabPyramidCubeFromRightSwitch() {
		addParallel(new commonCG_armToPickupFromFront());

		addSequential(new auto_wait(.25));

		addSequential(new auto_resetEncoder());
		addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 27500, 27500, 0.04, "Right"));
		addSequential(new auto_clawOpen());

		addSequential(new auto_gyroMMTurn(-105, 0.9));
		addSequential(new auto_moveUpperPosition(3300, 45)); // 80 practice);
		addSequential(new auto_resetEncoder());

		addSequential(new auto_driveToAngleWithEncoder(-.7, 8, -105, 22000, 22000, 0.025, "Right"));
		addParallel(new auto_intake_in(0.5, 1));
		addSequential(new auto_driveToAngleWithEncoder(-.7, 8, -90, 32000, 32000, 0.025, "Right"));
	}
}
