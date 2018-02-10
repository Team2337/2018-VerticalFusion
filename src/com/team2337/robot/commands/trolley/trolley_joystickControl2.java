package com.team2337.robot.commands.trolley;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.subsystems.Arm;
import com.team2337.robot.subsystems.Trolley;

/**
 * Lifter: JOYSTICKCONTROL - Moves the trolley based joystick
 * 
 * @category TROLLEY
 * @author - Bryce
 */
public class trolley_joystickControl2 extends Command {
	public boolean setPointSet = false;
	public static boolean isAtTop = false;
	boolean isAtBottom = false; 
	public static double potValue;
	double trolleyJoystickY;
	public static int stringPotValue;  
	
	public static TalonSRX frontRight = RobotMap.trolley_right;
	public static TalonSRX frontLeft = RobotMap.trolley_left;

	
	public trolley_joystickControl2() {
		requires(Robot.trolley);
	}

	protected void initialize() {
		isAtTop = false;
		setPointSet = false; 
		isAtBottom = false;
    	//Robot.trolley.disable();
    	Trolley.setSoftLimits(2, 0);
	}

	protected void execute() {
		stringPotValue = ((int) (RobotMap.trolley_right.getSelectedSensorPosition(0)));
		
		SmartDashboard.putBoolean("trolleySetSetpoint", setPointSet);
		//SmartDashboard.putNumber("PIDLiftPosition", stringPotValue);
		SmartDashboard.putNumber("Trolley stringPotPosition", potValue);
		SmartDashboard.putNumber("TrolleyJoystickValue", trolleyJoystickY);
		SmartDashboard.putBoolean("Trolley isAtBottom?", isAtBottom);
		SmartDashboard.putNumber("TrolleyStringPotValue,selected sensor", RobotMap.trolley_right.getSelectedSensorPosition(0));
		
    	trolleyJoystickY = Robot.oi.operatorJoystick.getRawAxis(1);    //////correct axix???????
    	//TODO

		potValue = stringPotValue;
		
    	if ((trolleyJoystickY > -.2 ) && (trolleyJoystickY < .2)) { 	//Dead band
    		
    		trolleyJoystickY = 0;  
    		
    		if (!setPointSet) {
    			//Robot.trolley.enable();
    			Robot.trolley.setPosition(RobotMap.trolley_right.getSelectedSensorPosition(0));
    			
    			SmartDashboard.putNumber("PIDSetPositionLIFTER", Robot.trolley.getPosition());
    
    			setPointSet = true; 
    		}
    	} else {				
    		//Robot.trolley.disable(); 
    		if  ((trolleyJoystickY > .1) ) {
     			RobotMap.trolley_right.set(ControlMode.PercentOutput, trolleyJoystickY);
    			//RobotMap.lift_leftFront.set(ControlMode.PercentOutput, -liftJoystickY);
    			
    		} 
    		else if (trolleyJoystickY < -.1) {
    			RobotMap.trolley_right.set(ControlMode.PercentOutput, trolleyJoystickY);
    			//RobotMap.lift_leftFront.set(ControlMode.PercentOutput, -liftJoystickY);
    		}
    		else {
    			RobotMap.trolley_right.set(ControlMode.PercentOutput, 0);
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
