package com.team2337.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Intake: IN - Move the intake in and rotate the cube
 * 
 * @category INTAKE
 * @author Brendan F.
 */
public class intake_inRotate extends Command {
	private double power = .75;
	
	
	private int time = 0;
	/**
	 * @param power Percent of the power of the motors (Values of 0-1)
	 */
	public intake_inRotate(double power) {
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
			if(RobotMap.crateSensorLeft.get()) {
				Robot.intake.rotateRight(power);
			} else if(RobotMap.crateSensorRight.get()) {
				Robot.intake.rotateLeft(power);
			} else {
			Robot.intake.moveIn(power);
			}
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
