package com.team2337.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Climber: DOWN - Moves the climber down
 * @category CLIMBER
 * @author Team2337 - EngiNERDs
 */
//TODO Add driver controller (if needed)
public class climber_joystickControl extends Command {
	public climber_joystickControl() {
		requires(Robot.climber);
	}
	
	protected void initialize() {}
	protected void execute() {}
	protected boolean isFinished() {return false;}
	protected void end() {}
	protected void interrupted() {this.end();}

}
