package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class auto_moveUpperPositionWithIsFinished extends Command {
	double arm,trolley,trolleyEnd;
	boolean trolleyDone = false;
	
	/**
	 * Allows for the arm and trolley setpoints to be set, and then immediately terminate the command
	 * @param arm
	 * Arm setpoint 
	 * @param trolley
	 * Trolley setpoint 
	 */
	public auto_moveUpperPositionWithIsFinished(double arm, double trolley) {
		requires(Robot.arm);
		this.arm = arm;
		this.trolley = trolley;
		trolleyDone = true;
	}
	
	/**
	 * The arm setpoint is set as normal, while the trolley setpoint is set, but does NOT end the command until the trolley has reached the given end point
	 * @param arm
	 * @param trolley
	 * @param trolleyEnd
	 * End point determining if the command can terminate based on trolley position 
	 */
	public auto_moveUpperPositionWithIsFinished(double arm, double trolley, double trolleyEnd) {
		requires(Robot.arm);
		this.arm = arm;
		this.trolley = trolley;
		this.trolleyEnd = trolleyEnd;
	}
	@Override
	protected void initialize() {
		Robot.arm.setSetpoint(arm);
		Robot.trolley.setSetpoint(trolley);
	}

	protected void execute() {
		if(Robot.trolley.getPosition() > trolleyEnd) {
			trolleyDone = true;
		}
	}

	protected boolean isFinished() {
		return trolleyDone;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}

}
