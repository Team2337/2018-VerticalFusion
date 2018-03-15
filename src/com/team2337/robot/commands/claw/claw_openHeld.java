package com.team2337.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.OI;
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
		
/*
 		if(Robot.intake.hasCrate() && !OI.operatorControls.getRawButton(Robot.oi.blueSwitch)) {
			time++;
			
		} else {
			time = 0;
		}
		if (time > 7) {
			if (Robot.lift.getPosition() > 300) {
				time = 0;
			} else {
			Robot.claw.close();
			done = true;
			}
		}
		*/
		if(Robot.intake.bothSensors() && !OI.operatorControls.getRawButton(Robot.oi.blueSwitch) && Robot.arm.getPosition() > Robot.arm.forwardClampLimit) {
			Robot.claw.close();
			Robot.claw.give60Hugs();
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
