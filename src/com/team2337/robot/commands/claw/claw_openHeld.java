package com.team2337.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Claw: OPEN - Opens the claw
 * @category CLAW
 * @author Brendan
 */
public class claw_openHeld extends Command {
	private int time;
	private boolean done;
	public claw_openHeld() {
		requires(Robot.claw);
	}
	@Override
	protected void initialize() {
		done = false;
	}
	@Override
	protected void execute() {
		if(Robot.intake.hasCrate()) {
			time++;
		} else {
			time = 0;
		}
		if (time > 30) {
			Robot.claw.close();
			done = true;
		}
	}
	@Override
	protected boolean isFinished() {
		return done;
	}
	@Override
	protected void end() {
		Robot.claw.give60Hugs();
	}
	@Override
	protected void interrupted() {
		this.end();
	}
}
