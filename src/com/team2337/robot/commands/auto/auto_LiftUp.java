package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the lift up to the desired position
 * 
 * @category AUTO-LIFT
 * @author Bryce G.
 */
public class auto_LiftUp extends Command{
	
	double lift, liftEnd;
	boolean liftDone = false;

	/**
	 * @param lift
	 * Set point of the lift  
	 */
	public auto_LiftUp(double lift) {
		this.lift = lift;
		liftDone = true;
	}
	
	/**
	 * @param lift
	 * Desired position of the lift
	 * @param liftEnd
	 * Position of the lift when the command will end
	 */
	public auto_LiftUp(double lift, double liftEnd) {
		this.lift = lift;
		this.liftEnd = liftEnd;
	}

	protected void initialize() {
		Robot.lift.setSetpoint(lift);
	}

	protected void execute() {
		if(Robot.lift.getPosition() > liftEnd) {
			liftDone = true;
		}
	}

	protected boolean isFinished() {
		return liftDone;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
