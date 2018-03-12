package com.team2337.robot.commands.climber;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class climber_ejector extends Command{
	
	public climber_ejector() {
		requires(Robot.climber);
	}

	protected void initialize() {
		Robot.climber.hookerEject();
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
