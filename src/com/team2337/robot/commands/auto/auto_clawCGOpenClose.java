package com.team2337.robot.commands.auto;

import com.team2337.fusion.wrappers.commands.Action;
import com.team2337.robot.commands.claw.claw_give30psi;
import com.team2337.robot.commands.claw.claw_give60psi;
import com.team2337.robot.commands.claw.claw_open;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Fusion Command Group
 * Opens the claw to release pressure and then close it with less grip
 * @category AUTO-CLAW
 * @author Brendan F. 
 */
public class auto_clawCGOpenClose extends Action {
	public auto_clawCGOpenClose() {
		addSequential(new claw_give60psi());
		addWait(0.05);
		addSequential(new claw_open());
		addWait(0.1);
		addSequential(new claw_give30psi());
		addWait(0.05);
		addSequential(new auto_clawClose());
		}
}
