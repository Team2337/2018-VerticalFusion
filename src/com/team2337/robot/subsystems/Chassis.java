package com.team2337.robot.subsystems;
import com.team2337.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2337.robot.commands.chassis.chassis_drive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The main chasiss runtime
 * 
 * @category CHASSIS
 * @author Team2337 - EngiNERDs
 */
public class Chassis extends Subsystem {

	public void initDefaultCommand() {
		setDefaultCommand(new chassis_drive());
	}
	public void setMotionMagicTurn(double targetPos) {
		RobotMap.chassis_rightFront.set(ControlMode.MotionMagic, targetPos);
		RobotMap.chassis_leftFront.set(ControlMode.MotionMagic, targetPos);
		
	}
	public void changeFollowerToVbus(double demand0){
		
		RobotMap.chassis_leftMid.set(ControlMode.PercentOutput, demand0);
		RobotMap.chassis_rightMid.set(ControlMode.PercentOutput, demand0);
		RobotMap.chassis_leftRear.set(ControlMode.PercentOutput, demand0);
		RobotMap.chassis_rightRear.set(ControlMode.PercentOutput, demand0);

	}
	public void changeVbusToFollower(double demand0) {
	
		RobotMap.chassis_rightFront.set(ControlMode.PercentOutput, demand0);
		RobotMap.chassis_leftFront.set(ControlMode.PercentOutput, demand0);
		RobotMap.chassis_leftMid.follow(RobotMap.chassis_leftFront);
		RobotMap.chassis_rightMid.follow(RobotMap.chassis_rightFront);
		RobotMap.chassis_leftRear.follow(RobotMap.chassis_leftFront);
		RobotMap.chassis_rightRear.follow(RobotMap.chassis_rightFront);
		
	}
	/**
	 * Sets the mode of the CANTalons to Brake or Coast
	 * @param mode true = brake, false = coast
	 */
	public void setBrakeMode(NeutralMode mode) {
		RobotMap.chassis_rightFront.setNeutralMode(mode);
		RobotMap.chassis_leftFront.setNeutralMode(mode);
	}
}
