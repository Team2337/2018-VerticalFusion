package com.team2337.robot.commands.PTO;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * @category CLIMBER
 * @author Bryce
 */
public class PTO_Climb extends Command {
	public PTO_Climb() {
		requires(Robot.pto);
	}
	protected void initialize() {
		Robot.pto.PTOClimb();
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
