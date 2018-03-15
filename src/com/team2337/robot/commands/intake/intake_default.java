package com.team2337.robot.commands.intake;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class intake_default extends Command {
	int num = 0;
	public intake_default() {
		requires(Robot.intake);
	}

	protected void initialize() {

	}

	protected void execute() {
		//System.out.println(num);
		if (RobotMap.firstIntake) {
			if (!Robot.intake.hasCrate()) {
				if (num == 2) {
					Robot.claw.give60Hugs();
				}
				if (num == 10) {
					Robot.claw.open();
				}
				if (num == 20) {
					Robot.claw.give30Hugs();
				}
				if (num == 30) {
					Robot.claw.close();
				}
				if(num >= 31) {
					RobotMap.firstIntake = false;
				}
				num++;
			}
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		num = 0;
	}

	protected void interrupted() {
		this.end();
	}
}
