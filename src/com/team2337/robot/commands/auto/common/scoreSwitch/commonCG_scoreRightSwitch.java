package com.team2337.robot.commands.auto.common.scoreSwitch;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.auto.claw.auto_clawOpenWithCollision;
import com.team2337.robot.commands.auto.driveCommands.driveWithEncoder.auto_driveToAngleWithEncoder;
import com.team2337.robot.commands.auto.driveCommands.driveWithTime.auto_driveForwardWithTime;
import com.team2337.robot.commands.auto.intake.auto_intakeOut;
import com.team2337.robot.commands.auto.resetCommands.auto_resetEncoder;
import com.team2337.robot.commands.auto.upperPosition.auto_moveUpperPosition;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class commonCG_scoreRightSwitch extends CommandGroup {

	public commonCG_scoreRightSwitch() {
		addSequential(new auto_driveToAngleWithEncoder(.9, 4, -40, 46000, 46000, 0.0175)); // right was 44000 left was 28000 left was 22000 
		addSequential(new auto_driveToAngleWithEncoder(.9, 3, 0, 60000, 60000, 0.02));
		addSequential(new auto_moveUpperPosition(1800,60));  //-250,60
		addParallel(new auto_driveForwardWithTime(.3, 1));
		addSequential(new auto_clawOpenWithCollision(4));
		addSequential (new auto_intakeOut(.3,1));
		addSequential(new auto_resetEncoder());
	}
}
