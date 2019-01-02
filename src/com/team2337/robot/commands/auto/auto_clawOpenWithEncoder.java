package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Opens the claw once the chassis passes a given encoder tick
 * @category AUTO-INTAKE
 * @author Brendan F.
 */
public class auto_clawOpenWithEncoder extends Command {
	private double power = 1;
	private double timeout, enc;
	private int time = 0;
	private boolean end = false;

	/**
	 * @param power Percent power of the motors (Values -1 -> 1)
	 * @param timeout Amount of time the command is allowed to run, inmilliseconds, if it hasn't ended by another means yet
	 * @param enc The chassis encoder value (full rotation = 0-4096)
	 */
	public auto_clawOpenWithEncoder(double power, double timeout,double enc) {
		requires(Robot.claw);
		this.power = power;
		this.timeout = timeout;
		this.enc = enc;
	}

	protected void initialize() {
		setTimeout(timeout);
	}
	
	protected void execute() {
		if(RobotMap.chassis_leftFront.getSelectedSensorPosition(0) > enc) {
		Robot.claw.open();

			time++;
	  if (time> 20) {
		  end=true;
	  }
		} 
	}
	protected boolean isFinished() {
		return isTimedOut() || end;
		}
	
	protected void end() {
		Robot.claw.give30Hugs();
		Robot.intake.stop();
	}
	
	protected void interrupted() {
		this.end();
	}
}
