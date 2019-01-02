package com.team2337.robot.commands.chassis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.RobotMap.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team2337.fusion.drive.*;

/**
 * Chassis: DRIVE - The drive for the chassis thats runs arcade drive
 * 
 * @category CHASSIS
 * @author Team2337 - EngiNERDs
 */
public class chassis_accelTest extends Command {
	private Joystick driverJoystick = Robot.oi.driverJoystick;
	public boolean collisionDetected;
	double currentJerkX, curr_world_linear_accel_x, curr_world_linear_accel_y, currentJerkY, last_world_linear_accel_x, last_world_linear_accel_y;
	final static double kCollisionThreshold_DeltaG = 0.5f;
	
	public chassis_accelTest() {
		requires(Robot.chassis);
	}

	protected void initialize() {

	}

	protected void execute() {
        collisionDetected = false;
        
        curr_world_linear_accel_x = RobotMap.accel.getX();
        currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
        last_world_linear_accel_x = curr_world_linear_accel_x;
        curr_world_linear_accel_y = RobotMap.accel.getY();
        currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
        last_world_linear_accel_y = curr_world_linear_accel_y;
        
        if ( ( Math.abs(currentJerkX) > kCollisionThreshold_DeltaG ) ||
             ( Math.abs(currentJerkY) > kCollisionThreshold_DeltaG) ) {
            collisionDetected = true;
        }
        SmartDashboard.putBoolean("CollisionDetected", collisionDetected);
        SmartDashboard.putNumber("currentJerkX", currentJerkX);
        SmartDashboard.putNumber("currentJerkY", currentJerkY);
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
