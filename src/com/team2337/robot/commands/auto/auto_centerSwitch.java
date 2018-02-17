package com.team2337.robot.commands.auto;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class auto_centerSwitch extends Command {
	String stuff;
	public auto_centerSwitch() {
		
	}
	
	protected void initialize() {
		String switcho = Robot.ourswitch;
		char scaleo = Robot.scale;
		char switchopp = Robot.oppswitch;
		stuff = "Our swtich: " + switcho + " Scale: " + scaleo + " Opp Switch: " + switchopp; 
		
	}

	protected void execute() {
		System.out.println(stuff);
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
