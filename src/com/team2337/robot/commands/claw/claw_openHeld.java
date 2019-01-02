package com.team2337.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Opens the claw while the button is held 
 * 
 * @category CLAW
 * @author Brendan F. 
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
		
		if((Robot.intake.bothSensors() && !OI.operatorControls.getRawButton(Robot.oi.blueSwitch)) && (Robot.arm.getPosition() > Robot.arm.forwardClampLimit)) {
			if(Robot.lift.getPosition() > 200) {
				
			} else if (Robot.trolley.getPosition() > 430) {
			} else {
			Robot.claw.close();
			Robot.claw.give60Hugs();
			done = true;
			}
		}  
		
	}
	@Override
	protected boolean isFinished() {
		return done;
	}
	@Override
	protected void end() {
		done = false;
		Robot.claw.give60Hugs();
	}
	@Override
	protected void interrupted() {
		this.end();
	}
}
