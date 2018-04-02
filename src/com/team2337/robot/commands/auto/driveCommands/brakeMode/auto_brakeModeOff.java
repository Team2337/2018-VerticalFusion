package com.team2337.robot.commands.auto.driveCommands.brakeMode;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class auto_brakeModeOff extends Command {
	public auto_brakeModeOff() {
		requires(Robot.chassis);
	}

	@Override
	protected void initialize() {
		RobotMap.chassis_rightFront.setNeutralMode(NeutralMode.Coast);
		RobotMap.chassis_leftFront.setNeutralMode(NeutralMode.Coast);
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		this.end();
	}

}
