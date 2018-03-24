package com.team2337.robot.commands.pixy;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class pixy_Monitor extends Command {

	public pixy_Monitor() {
		requires(Robot.pixy);
	}

	protected void initialize() {

	}

	protected void execute() {
		Robot.pixy.testPixy1();
		SmartDashboard.putNumber("Pixy Xx Value", Robot.pixy.xx);
		SmartDashboard.putNumber("Pixy Yy Value", Robot.pixy.yy);
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
