package com.team2337.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.followers.EncoderFollower;

public class auto_followTraj extends Command {
	
	public auto_followTraj() {
		int encoder_position;
	}
	
	protected void initialize() {
		EncoderFollower left = new EncoderFollower(modifier.getLeftTrajectory());
		EncoderFollower right = new EncoderFollower(modifier.getRightTrajectory());
		
		
		int encoder_position;
		double wheel_diameter;
		// Encoder Position is the current, cumulative position of your encoder. If you're using an SRX, this will be the
		// 'getEncPosition' function.
		// 1000 is the amount of encoder ticks per full revolution
		// Wheel Diameter is the diameter of your wheels (or pulley for a track system) in meters
		left.configureEncoder(encoder_position, 1000, wheel_diameter);
	}
	
	protected void execute() {
		
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {

	}

	protected void interrupted() {
		this.end();
	}

}
