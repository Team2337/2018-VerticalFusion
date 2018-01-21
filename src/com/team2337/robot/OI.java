package com.team2337.robot;

import com.team2337.fusion.controller.JoystickAnalogButton;
import com.team2337.fusion.controller.JoystickPOVButton;

import com.team2337.robot.commands.*;
import com.team2337.robot.commands.arm.arm_decreaseAngle;
import com.team2337.robot.commands.arm.arm_increaseAngle;
import com.team2337.robot.commands.ejector.*;
import com.team2337.robot.commands.intake.*;
import com.team2337.robot.commands.shifter.*;
import com.team2337.robot.commands.lifter.lifter_setPID;
import com.team2337.robot.commands.lifter.lifter_stopPID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID.RumbleType; 


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	/*
	 * DriverJoystick
	 */
	public static Joystick				driverJoystick			= new Joystick(0);
	
	JoystickButton			driver_GreenA			= new JoystickButton(driverJoystick, 1);
	JoystickButton			driver_RedB				= new JoystickButton(driverJoystick, 2);
	JoystickButton			driver_BlueX			= new JoystickButton(driverJoystick, 3);
	JoystickButton			driver_YellowY			= new JoystickButton(driverJoystick, 4);
	JoystickButton			driver_BumperLeft		= new JoystickButton(driverJoystick, 5);
	JoystickButton			driver_BumperRight 		= new JoystickButton(driverJoystick, 6);
	JoystickButton			driver_Back				= new JoystickButton(driverJoystick, 7);
	JoystickButton			driver_Start			= new JoystickButton(driverJoystick, 8);
	JoystickButton			driver_LeftStick		= new JoystickButton(driverJoystick, 9);
	JoystickButton			driver_RightStick		= new JoystickButton(driverJoystick, 10);
	JoystickAnalogButton	driver_TriggerLeft		= new JoystickAnalogButton(driverJoystick, 2);
	JoystickAnalogButton	driver_TriggerRight		= new JoystickAnalogButton(driverJoystick, 3);
	JoystickPOVButton		driver_POVUp			= new JoystickPOVButton(driverJoystick, 0);
	JoystickPOVButton		driver_POVUpRight		= new JoystickPOVButton(driverJoystick, 45);
	JoystickPOVButton		driver_POVRight			= new JoystickPOVButton(driverJoystick, 90);
	JoystickPOVButton		driver_POVDownRight		= new JoystickPOVButton(driverJoystick, 135);
	JoystickPOVButton		driver_POVDown			= new JoystickPOVButton(driverJoystick, 180);
	JoystickPOVButton		driver_POVDownLeft		= new JoystickPOVButton(driverJoystick, 225);
	JoystickPOVButton		driver_POVLeft			= new JoystickPOVButton(driverJoystick, 270);
	JoystickPOVButton		driver_POVUpLeft		= new JoystickPOVButton(driverJoystick, 315);
	
	/*
	 * OperatorJoystick
	 */
	public static Joystick				operatorJoystick		= new Joystick(1);
	JoystickButton			operator_GreenA			= new JoystickButton(operatorJoystick, 1);
	JoystickButton			operator_RedB			= new JoystickButton(operatorJoystick, 2);
	JoystickButton			operator_BlueX			= new JoystickButton(operatorJoystick, 3);
	JoystickButton			operator_YellowY		= new JoystickButton(operatorJoystick, 4);
	JoystickButton			operator_BumperLeft		= new JoystickButton(operatorJoystick, 5);
	JoystickButton			operator_BumperRight 	= new JoystickButton(operatorJoystick, 6);
	JoystickButton			operator_Back			= new JoystickButton(operatorJoystick, 7);
	JoystickButton			operator_Start			= new JoystickButton(operatorJoystick, 8);
	JoystickButton			operator_LeftStick		= new JoystickButton(operatorJoystick, 9);
	JoystickButton			operator_RightStick		= new JoystickButton(operatorJoystick, 10);
	JoystickAnalogButton	operator_TriggerLeft	= new JoystickAnalogButton(operatorJoystick, 2);
	JoystickAnalogButton	operator_TriggerRight	= new JoystickAnalogButton(operatorJoystick, 3);
	JoystickPOVButton		operator_POVUp			= new JoystickPOVButton(operatorJoystick, 0);
	JoystickPOVButton		operator_POVUpRight		= new JoystickPOVButton(operatorJoystick, 45);
	JoystickPOVButton		operator_POVRight		= new JoystickPOVButton(operatorJoystick, 90);
	JoystickPOVButton		operator_POVDownRight	= new JoystickPOVButton(operatorJoystick, 135);
	JoystickPOVButton		operator_POVDown		= new JoystickPOVButton(operatorJoystick, 180);
	JoystickPOVButton		operator_POVDownLeft	= new JoystickPOVButton(operatorJoystick, 225);
	JoystickPOVButton		operator_POVLeft		= new JoystickPOVButton(operatorJoystick, 270);
	JoystickPOVButton		operator_POVUpLeft		= new JoystickPOVButton(operatorJoystick, 315);
	

	/*
	 * OperatorControl
	 */
	public static Joystick				operatorControls		= new Joystick(2);

	JoystickButton			operatorInt_GreenButton				= new JoystickButton(operatorJoystick, 19);
	JoystickButton			operatorInt_RedButton				= new JoystickButton(operatorJoystick, 20);
    JoystickButton 			operatorInt_ClearSwitch				= new JoystickButton(operatorJoystick, 15);
	JoystickButton 			operatorInt_YellowSwitch			= new JoystickButton(operatorJoystick, 18);
	JoystickButton 			operatorInt_BlackSwitch				= new JoystickButton(operatorJoystick, 17);
	JoystickButton 			operatorInt_BlueSwitch				= new JoystickButton(operatorJoystick, 16);
	JoystickButton 			operatorInt_WhiteButton				= new JoystickButton(operatorJoystick, 14);
	JoystickButton 			operatorInt_YellowButton			= new JoystickButton(operatorJoystick, 13);
	JoystickButton 			operatorInt_BlackButton 			= new JoystickButton(operatorJoystick, 11);
	JoystickButton 			operatorInt_BlueButton				= new JoystickButton(operatorJoystick, 12);
    
	
	public OI() {
		
		/* ====== DRIVER JOYSTICK ===== */
		
		driver_GreenA			.whenPressed(new lifter_setPID(0.7));
		driver_RedB				.whenPressed(new lifter_setPID(0.6));
		driver_BlueX			.whenPressed(new lifter_setPID(0.1)); 
		driver_YellowY			.whenPressed(new lifter_stopPID());
		
		driver_BumperLeft		.whenPressed(new DoNothing());
		driver_BumperRight		.whenPressed(new DoNothing());
		
		driver_Back				.whileHeld(new DoNothing()); 
		driver_Start			.whileHeld(new DoNothing());
		
		driver_LeftStick		.whenPressed(new DoNothing()); 
		driver_RightStick		.whenPressed(new DoNothing()); 
		
		driver_TriggerLeft		.whileHeld(new intake_in(0.5));
		driver_TriggerRight		.whileHeld(new intake_out(0.5));
		
		driver_POVUp			.whenPressed(new DoNothing());  
		//driver_POVUpRight		.whenPressed(new _DoNothing()); 
	    driver_POVRight			.whenPressed(new DoNothing()); 
	   	//driver_POVDownRight	.whenPressed(new _DoNothing()); 
	    driver_POVDown			.whenPressed(new DoNothing()); 
	   	//driver_POVDownLeft	.whenPressed(new _DoNothing()); 
	    driver_POVLeft			.whenPressed(new DoNothing()); 
	   	//driver_POVUpLeft		.whenPressed(new _DoNothing()); 
	    
	    //////////////////////////////////
	    
	    
		/* ====== OPERATOR JOYSTICK ===== */
	    
		operator_GreenA			.whenPressed(new lifter_setPID(0.7));
		operator_RedB			.whenPressed(new lifter_setPID(0.9));
		operator_BlueX			.whenPressed(new lifter_setPID(1.1));
		operator_YellowY		.whenPressed(new lifter_stopPID());
		
		operator_BumperLeft		.whileHeld(new arm_increaseAngle());
		operator_BumperRight	.whileHeld(new arm_decreaseAngle());
		
		operator_Back			.whenPressed(new DoNothing());
		operator_Start			.whenPressed(new DoNothing());
		
		operator_LeftStick		.whenPressed(new DoNothing());
		operator_RightStick		.whenPressed(new DoNothing());
		
		operator_TriggerLeft	.whileHeld(new DoNothing());
		operator_TriggerRight	.whileHeld(new DoNothing());
		
		operator_POVUp			.whenPressed(new DoNothing());
		//operator_POVUpRight	.whenPressed(new _DoNothing());
		operator_POVRight		.whenPressed(new DoNothing());
		//operator_POVDownRight	.whenPressed(new _DoNothing());
	    operator_POVDown	    .whenPressed(new DoNothing());
		//operator_POVDownLeft  .whenPressed(new _DoNothing());
		operator_POVLeft	    .whenPressed(new DoNothing());
		//operator_POVUpLeft	.whenPressed(new _DoNothing());
		
		////////////////////////////////////
		
		
		/* ===== DRIVER STATION CONTROLS ===== */
		
		//operatorInt_GreenButton	.whenPressed(new _DoNothing());
		//operatorInt_RedButton	.whenPressed(new _DoNothing());
		
		operatorInt_ClearSwitch	.whenPressed(new DoNothing());
		operatorInt_BlueSwitch	.whenPressed(new DoNothing());
		operatorInt_BlackSwitch	.whenPressed(new DoNothing());
		operatorInt_YellowSwitch.whenPressed(new DoNothing());
		
		operatorInt_BlackButton	.whenPressed(new DoNothing());
		operatorInt_BlueButton	.whenPressed(new DoNothing());
		operatorInt_YellowButton.whenPressed(new DoNothing());
		operatorInt_WhiteButton	.whenPressed(new DoNothing());
		
		///////////////////////////////////////// 
	}

	
	public Joystick getDriverJoystick() {
		return driverJoystick;
	}
	
	public Joystick getOperatorJoystick() {
		return operatorJoystick;
	}
	
	public Joystick getOperatorControls() {
		return operatorControls;
	}
	
	/** 
	   * <p style="color:blue;"><strong>Function enables/disables controller vibration.</strong></p> 
	   * <p style="color:blue;"><i>Call with Robot.OI.rumble(OnOff)</i></p> 
	   * @author SomeNerd 
	   * @param joy Joystick to vibrate (EX: Robot.OI.driverJoystick)
	   * @param intensity Intensity of the vibration from 0 to 1 (EX: 0.75)
	   */ 
	
	public void rumble(Joystick joy, double intensity) {
		joy.setRumble(RumbleType.kLeftRumble, intensity);
		joy.setRumble(RumbleType.kRightRumble, intensity);
	}
}


