/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot.commands.lifter;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.subsystems.Arm;
import com.team2337.robot.subsystems.Lifter;

/**
 * Lifter: JOYSTICKCONTROL - Moves the lifter based joystick
 * 
 * @category LIFTER
 * @author - Bryce
 */
public class lifter_joystickControl2 extends Command {
	public boolean setPointSet = false;
	public static boolean isAtTop = false;
	boolean isAtBottom = false; 
	public static double potValue;
	double liftJoystickY;
	public static int stringPotValue;  
	
	public static VictorSPX frontRight = RobotMap.lift_rightFront;
	public static VictorSPX frontLeft = RobotMap.lift_leftFront;
	public static TalonSRX backRight = RobotMap.lift_leftBack;
	
	
	public lifter_joystickControl2() {
		requires(Robot.lifter);
	}

	protected void initialize() {
		isAtTop = false;
		setPointSet = false; 
		isAtBottom = false;
    	Robot.lifter.disable();
    	Lifter.setSoftLimits(2, 0);
	}

	protected void execute() {
		stringPotValue = ((int) (RobotMap.lift_stringPot.pidGet()));
		
		SmartDashboard.putBoolean("liftSetSetpoint", setPointSet);
		//SmartDashboard.putNumber("PIDLiftPosition", stringPotValue);
		SmartDashboard.putNumber("stringPotPosition", potValue);
		SmartDashboard.putNumber("LiftJoystickValue", liftJoystickY);
		SmartDashboard.putBoolean("isAtBottom?", isAtBottom);
		SmartDashboard.putNumber("StringPotValue", RobotMap.lift_stringPot.pidGet());
		
		liftJoystickY = Robot.oi.operatorJoystick.getRawAxis(1);

		potValue = stringPotValue;
		
    	if ((liftJoystickY > -.2 ) && (liftJoystickY < .2)) { 	//Dead band
    		
    		liftJoystickY = 0;  
    		
    		if (!setPointSet) {
    			Robot.lifter.enable();
    			Robot.lifter.setSetpoint(RobotMap.lift_rightFront.getSelectedSensorPosition(0));
    			
    			SmartDashboard.putNumber("PIDSetPositionLIFTER", Robot.lifter.getPosition());
    
    			setPointSet = true; 
    		}
    	} else {				
    		Robot.lifter.disable(); 
    		if  ((liftJoystickY > .1) ) {
     			RobotMap.lift_rightFront.set(ControlMode.PercentOutput, liftJoystickY);
    			//RobotMap.lift_leftFront.set(ControlMode.PercentOutput, -liftJoystickY);
    			
    		} 
    		else if (liftJoystickY < -.1) {
    			RobotMap.lift_rightFront.set(ControlMode.PercentOutput, liftJoystickY);
    			//RobotMap.lift_leftFront.set(ControlMode.PercentOutput, -liftJoystickY);
    		}
    		else {
    			RobotMap.lift_rightFront.set(ControlMode.PercentOutput, 0);
    			//RobotMap.lift_leftFront.set(ControlMode.PercentOutput, 0);
    			
    		}
    		setPointSet = false;
    	}	// End Deadband
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
