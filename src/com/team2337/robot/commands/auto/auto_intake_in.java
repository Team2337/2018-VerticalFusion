package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Intake: IN - Move the intake in
 * 
 * @category AUTO-INTAKE
 * @author Brendan F.
 */
public class auto_intake_in extends Command {
	private double power = .75;
	private double timeout;
	private boolean done = false;
	
	private int time = 0;

	/**
	 * @param power
	 * Percent of the power of the motors
	 * @param timeout
	 * The amount of time the command is allowedto run for until it is forced to terminate
	 */
	public auto_intake_in(double power, double timeout) {
		requires(Robot.intake);
		this.power = power;
		this.timeout = timeout;
	}

	protected void initialize() {
		setTimeout(timeout);
	}
	protected void execute() {
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
