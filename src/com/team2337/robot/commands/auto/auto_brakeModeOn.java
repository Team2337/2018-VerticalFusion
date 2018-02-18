package com.team2337.robot.commands.auto;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class auto_brakeModeOn extends Command {
	String ourSwitch;
	public auto_brakeModeOn(String ourSwitch) {
		this.ourSwitch = ourSwitch;
		requires(Robot.chassis);
	}

	@Override
	protected void initialize() {
		System.out.println(ourSwitch);
		RobotMap.chassis_rightFront.setNeutralMode(NeutralMode.Brake);
		RobotMap.chassis_leftFront.setNeutralMode(NeutralMode.Brake);
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
