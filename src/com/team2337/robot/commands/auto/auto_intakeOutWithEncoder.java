package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Intake: OUT - Move the intake out
 * @category AUTO-INTAKE
 * @author Brendan F.
 */
public class auto_intakeOutWithEncoder extends Command {
	private double power = 1;
	private double timeout, enc;
	private int time = 0;
	private boolean end = false;

	/**
	 * @param speed
	 * Percent of power output on the motor
	 * @param timeout
	 * The amount of time the command is allowed the run until it is forced to terminate
	 * @param enc
	 * The position of the chassis in which the intake will shoot out the cube
	 */
	public auto_intakeOutWithEncoder(double power, double timeout,double enc) {
		requires(Robot.intake);
		this.power = power;
		this.timeout = timeout;
		this.enc = enc;
	}

	protected void initialize() {
		setTimeout(timeout);
	}
	
	protected void execute() {
		if(RobotMap.chassis_leftFront.getSelectedSensorPosition(0) > enc) {
		Robot.intake.moveOut(this.power);

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
