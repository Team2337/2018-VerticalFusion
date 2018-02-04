package com.team2337.robot.commands.chassis;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * MoveForward - Move the robot forward for specified time
 * @category CHASSSIS AUTON
 * @author Jennah
 */
public class chassis_moveForward extends Command {
	private double time = 10;
    public chassis_moveForward(double time) {
    	requires(Robot.chassis);
    	this.time = time;
    }

    protected void initialize() {
    	setTimeout(this.time);
    	//RobotMap.drive.arcadeDrive(1, 0, true);
    }
    protected void execute() {}
    protected boolean isFinished() { return isTimedOut();}
    protected void end() {
    	//RobotMap.drive.arcadeDrive(0, 0, true);
    }
    protected void interrupted() {this.end();}
}
