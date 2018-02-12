package com.team2337.robot.commands.trolley;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Lifter: JOYSTICKCONTROL - Moves the trolley based on flight joystick
 * 
 * @category TROLLEY
 * @author - Bryce
 */
public class TEST_ONLY_trolley_joystickControl extends Command {
	
	boolean setPointSet = false;
	
	double trolleyJoystickY;
	static int stringPotValue;  
	
	static TalonSRX frontRight = RobotMap.trolley_right;
	static TalonSRX frontLeft = RobotMap.trolley_left;

	public TEST_ONLY_trolley_joystickControl() {
		requires(Robot.trolley);
	}

	protected void initialize() {
		
		//TODO  CHECK-SET SOFTLIMITS IN SUBSYSTEM!!!!!!!!!!
	}

	protected void execute() {
		stringPotValue = ((int) (RobotMap.trolley_right.getSelectedSensorPosition(0)));
		
		SmartDashboard.putNumber("TrolleyStringPotValue,selected sensor", RobotMap.trolley_right.getSelectedSensorPosition(0));
		
    	trolleyJoystickY = Robot.oi.operatorJoystick.getRawAxis(1);  //uses flight joystick to test

    	if ((trolleyJoystickY > -.2 ) && (trolleyJoystickY < .2)) { 	//Dead band
    		
    		trolleyJoystickY = 0;  
    		
    		if (!setPointSet) {
    			//Robot.trolley.enable();
    			Robot.trolley.setSetpoint(RobotMap.trolley_right.getSelectedSensorPosition(0));
    			setPointSet = true; 
    		}
    		
    	} else {				
    		//Robot.trolley.disable(); 
    		setPointSet = false;
     		RobotMap.trolley_right.set(ControlMode.PercentOutput, trolleyJoystickY);
    		}
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
