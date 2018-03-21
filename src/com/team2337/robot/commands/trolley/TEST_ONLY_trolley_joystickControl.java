package com.team2337.robot.commands.trolley;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
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
	double throttleJoyStick;
	static int stringPotValue;  
	
	static TalonSRX frontRight = RobotMap.trolley_right;
	static VictorSPX frontLeft = RobotMap.climb_motor;

	public TEST_ONLY_trolley_joystickControl() {
		requires(Robot.trolley);
	}

	protected void initialize() {
		
		//TODO  CHECK-SET SOFTLIMITS IN SUBSYSTEM!!!!!!!!!!
	}

	protected void execute() {
		stringPotValue = ((int) (RobotMap.trolley_right.getSelectedSensorPosition(0)));
		
		SmartDashboard.putNumber("TrolleyStringPotValue,selected sensor", RobotMap.trolley_right.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("trolleyOutputPercent", RobotMap.trolley_right.getMotorOutputPercent());
		SmartDashboard.putNumber("trolleyJoystickValue", Robot.oi.operatorJoystick.getRawAxis(2));
		SmartDashboard.putBoolean("troleySetPointSet", setPointSet);
		
    	trolleyJoystickY = Robot.oi.operatorJoystick.getRawAxis(2);  //uses flight joystick to test

    	if ((trolleyJoystickY > -.2 ) && (trolleyJoystickY < .2)) { 	//Dead band
    		
    		trolleyJoystickY = 0;  
    		
    		if (!setPointSet) {
    			Robot.trolley.setSetpoint(RobotMap.trolley_right.getSelectedSensorPosition(0));
    			setPointSet = true; 
    		}
    		
    	} else { 
    		setPointSet = false;
    		Robot.trolley.move(trolleyJoystickY);  //run in percentVbus mode
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
