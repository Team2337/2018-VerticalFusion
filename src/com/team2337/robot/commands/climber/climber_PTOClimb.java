package com.team2337.robot.commands.climber;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * @category CLIMBER
 * @author Bryce
 */
public class climber_PTOClimb extends Command {
	public climber_PTOClimb() {
		requires(Robot.climber);
	}
	protected void initialize() {
		Robot.climber.PTOClimb();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
