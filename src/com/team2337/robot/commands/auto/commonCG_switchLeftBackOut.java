package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_switchLeftBackOut extends CommandGroup {

	public commonCG_switchLeftBackOut() {
		addSequential(new auto_resetEncoder());
		addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 41000, 41000, 0.04));
		
		addSequential(new auto_gyroMMTurn(-75, 0.75));
		addSequential(new auto_driveToAngleWithEncoder(.7, 8, -75, 101000, 101000, 0.03, true));
		addSequential(new auto_driveToAngleWithEncoder(.6, 8, 0, 195000, 195000, 0.02));
		addSequential(new auto_brakeModeOn());
		addSequential(new auto_clawCGOpenClose());
		addParallel(new auto_moveUpperPosition(3300,45)); //80 practice
		addSequential(new auto_resetEncoder());

	}
}
