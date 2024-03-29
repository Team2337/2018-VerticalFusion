package com.team2337.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

/**
 * Lifter: JOYSTICKCONTROL - Moves the lift based on throttle stick
 * 
 * @category TROLLEY
 * @author - Bryce
 */
public class TEST_ONLY_Lift_ThrottleControl extends Command {
	
	boolean setPointSet = false;
	
	double liftThrottle;
	double stringPotValue;  
	
	static TalonSRX frontRight = RobotMap.lift_right;
	static VictorSPX frontLeft = RobotMap.lift_left;

	public TEST_ONLY_Lift_ThrottleControl() {
		requires(Robot.lift);
	}

	protected void initialize() {
		
		//TODO  CHECK-SET SOFTLIMITS IN SUBSYSTEM!!!!!!!!!!
	}

	protected void execute() {
		stringPotValue = (RobotMap.lift_right.getSelectedSensorPosition(0));
		
		SmartDashboard.putNumber("LiftStringPotValue,selected sensor", RobotMap.lift_right.getSelectedSensorPosition(0));
		
		liftThrottle = Robot.oi.operatorJoystick.getRawAxis(1);  //uses flight throttle to test
/*
    	if ((liftThrottle > -.2 ) && (liftThrottle < .2)) { 	//Dead band
    		
    		liftThrottle = 0;  
    		
    		if (!setPointSet) {
    			//Robot.trolley.setSetpoint(RobotMap.lift_right.getSelectedSensorPosition(0));
    			setPointSet = true; 
    		}
    		
    	} else {
    	*/
    		setPointSet = false;
     		Robot.lift.move(liftThrottle);  //run lift by %Vbus
     		SmartDashboard.putNumber("liftThrottle", RobotMap.lift_right.getMotorOutputPercent());
    		//}
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
