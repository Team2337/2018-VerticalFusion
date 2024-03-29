package com.team2337.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Intake: IN - Move the intake in
 * 
 * @category INTAKE
 * @author Brendan F.
 */
public class intake_in extends Command {
	private double power = .75;
	
	
	private int time = 0;
	public intake_in(double power) {
		requires(Robot.intake);
		this.power = power;
	}

	protected void initialize() {
		
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
		} else {
			Robot.intake.moveIn(this.power);
		}
		
	}
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.intake.stop();
	}

	protected void interrupted() {
		this.end();
	}
}
