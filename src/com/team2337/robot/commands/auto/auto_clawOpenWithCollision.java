package com.team2337.robot.commands.auto;


import edu.wpi.first.wpilibj.command.Command;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Opens the claw when the accelerometer senses a force of 0.9 G forces being put on the robot (when we run into the switch)
 * @category AUTO-CLAW
 * @author Bryce G., Sean L.
 */
public class auto_clawOpenWithCollision extends Command {
	public boolean collisionDetected;
	double currentJerkX, curr_world_linear_accel_x, curr_world_linear_accel_y, currentJerkY, last_world_linear_accel_x, last_world_linear_accel_y, timeout;
	final static double kCollisionThreshold_DeltaG = 0.9f;
	
	/**
	 * @param timeout The amount of time the command is allowed to run, in milliseconds, if it never ends by any other means
	 */
	public auto_clawOpenWithCollision(double timeout) {
		this.timeout = timeout;
		requires(Robot.claw);
		
	}
	@Override
	protected void initialize() {
		setTimeout(timeout);
		collisionDetected = false;
		last_world_linear_accel_x = RobotMap.accel.getX();
		last_world_linear_accel_y = RobotMap.accel.getY();
	}
	@Override
	protected void execute() {
		
        
        curr_world_linear_accel_x = RobotMap.accel.getX();
        currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
        last_world_linear_accel_x = curr_world_linear_accel_x;
        curr_world_linear_accel_y = RobotMap.accel.getY();
        currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
        last_world_linear_accel_y = curr_world_linear_accel_y;
        
        if ( ( Math.abs(currentJerkX) > kCollisionThreshold_DeltaG ) ||
             ( Math.abs(currentJerkY) > kCollisionThreshold_DeltaG) ) {
        	Robot.claw.open();
        	 collisionDetected = true;
        }
        //SmartDashboard.putBoolean("CollisionDetected", collisionDetected);
       // SmartDashboard.putNumber("currentJerkX", currentJerkX);
       // SmartDashboard.putNumber("currentJerkY", currentJerkY);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut() || collisionDetected;
	}
	@Override
	protected void end() {
		collisionDetected = false;
		Robot.claw.open();
	}
	@Override
	protected void interrupted() {
		this.end();
	}
}
