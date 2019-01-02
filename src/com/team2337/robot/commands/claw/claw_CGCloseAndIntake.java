package com.team2337.robot.commands.claw;

import com.team2337.fusion.wrappers.commands.Action;
import com.team2337.robot.RobotMap;
import com.team2337.robot.commands.auto.auto_intake_in;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Fusion Command Group
 * Closes the claw at 60 psi, and runs the intake inwards to bring the cube in with a firm grip
 * 
 * @category CLAW
 * @author Brendan F.
 */
public class claw_CGCloseAndIntake extends Action {
	public claw_CGCloseAndIntake() {
		addSequential(new claw_give60psi());
		addWait(0.25);
		addParallel(new claw_close());
		addSequential(new auto_intake_in(.80, 5.0));//Run at 5 second at full power
	}
}
