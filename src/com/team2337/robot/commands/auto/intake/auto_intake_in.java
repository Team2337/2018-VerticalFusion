package com.team2337.robot.commands.auto.intake;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Intake: IN - Move the intake in
 * 
 * @category INTAKE
 * @author Brendan
 */
public class auto_intake_in extends Command {
	private double power = .75;
	private double timeout;
	private boolean done = false;
	
	private int time = 0;
	public auto_intake_in(double power, double timeout) {
		requires(Robot.intake);
		this.power = power;
		this.timeout = timeout;
	}

	protected void initialize() {
		setTimeout(timeout);
	}
	protected void execute() {
		/*
		if(Robot.intake.bothSensors() && !OI.operatorControls.getRawButton(Robot.oi.blueSwitch)) {
			time++;
		} else {
			time = 0;
		}
		if (time > 100) {
			Robot.intake.stop();
			Robot.claw.give60Hugs();
		} else {
			Robot.intake.moveIn(this.power);
			//Robot.claw.give30Hugs();
		}
		*/
		if(Robot.intake.bothSensors() && !OI.operatorControls.getRawButton(Robot.oi.blueSwitch)) {
			Robot.intake.stop();
			Robot.claw.give60Hugs();
			done = true;
		} else {
			Robot.intake.moveIn(this.power);
		}
		
	}
	protected boolean isFinished() {
		return isTimedOut() || done;
	}
	
	protected void end() {
		Robot.intake.stop();
		done = false;
	}

	protected void interrupted() {
		this.end();
	}
}
