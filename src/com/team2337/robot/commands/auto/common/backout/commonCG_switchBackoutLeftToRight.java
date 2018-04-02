package com.team2337.robot.commands.auto.common.backout;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.auto.claw.auto_clawCGOpenClose;
import com.team2337.robot.commands.auto.driveCommands.brakeMode.auto_brakeModeOn;
import com.team2337.robot.commands.auto.driveCommands.driveWithEncoder.auto_driveToAngleWithEncoder;
import com.team2337.robot.commands.auto.motionMagic.auto_gyroMMTurn;
import com.team2337.robot.commands.auto.resetCommands.auto_resetEncoder;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_switchBackoutLeftToRight extends CommandGroup {

	public commonCG_switchBackoutLeftToRight() {
		addSequential(new auto_resetEncoder());
		addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 41000, 41000, 0.04));
		
		addSequential(new auto_gyroMMTurn(-75, 0.75));
		addSequential(new auto_driveToAngleWithEncoder(.7, 8, -75, 101000, 101000, 0.03, true));
		addSequential(new auto_driveToAngleWithEncoder(.5, 8, 0, 120000, 120000, 0.02));
		addSequential(new auto_brakeModeOn());
		addSequential(new auto_clawCGOpenClose());

	}
}
