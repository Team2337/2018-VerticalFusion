package com.team2337.robot.commands.auto.common.aroundSwitch;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.auto.auto_wait;
import com.team2337.robot.commands.auto.claw.auto_claw60;
import com.team2337.robot.commands.auto.claw.auto_clawCGOpenClose;
import com.team2337.robot.commands.auto.claw.auto_clawClose;
import com.team2337.robot.commands.auto.claw.auto_clawOpen;
import com.team2337.robot.commands.auto.driveCommands.brakeMode.auto_brakeModeOn;
import com.team2337.robot.commands.auto.driveCommands.driveWithEncoder.auto_driveToAngleWithEncoder;
import com.team2337.robot.commands.auto.intake.auto_intake_in;
import com.team2337.robot.commands.auto.motionMagic.auto_gyroMMTurn;
import com.team2337.robot.commands.auto.resetCommands.auto_resetEncoder;
import com.team2337.robot.commands.auto.upperPosition.auto_moveUpperPosition;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_driveAroundSwitchRight extends CommandGroup {

	public commonCG_driveAroundSwitchRight() {
		addSequential(new auto_resetEncoder());
		addSequential(new auto_driveToAngleWithEncoder(-.5, 3, 0, 42000, 42000, 0.04));
		
		addSequential(new auto_gyroMMTurn(-75, 0.75));
		addSequential(new auto_driveToAngleWithEncoder(.7, 8, -75, 101000, 101000, 0.025, true));
		addSequential(new auto_driveToAngleWithEncoder(.6, 8, 0, 210000, 210000, 0.01));
		addSequential(new auto_brakeModeOn());
		addSequential(new auto_clawCGOpenClose());
		addParallel(new auto_moveUpperPosition(3300,45)); // 80 practice
		addSequential(new auto_resetEncoder());
		addSequential(new auto_clawOpen());
		addParallel(new auto_driveToAngleWithEncoder(-.5, 8, -50, 16000, 16000, 0.04, true));
		addSequential(new auto_wait(0.8));
		addParallel(new auto_intake_in(1, 3));
		addSequential(new auto_wait(1));
		addSequential(new auto_clawClose());
		addSequential(new auto_claw60());
		addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 9000, 9000, 0.04, true));

	}
}
