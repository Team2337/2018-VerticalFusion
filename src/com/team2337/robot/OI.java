/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot;

import com.team2337.fusion.controller.JoystickAnalogButton;
import com.team2337.fusion.controller.JoystickPOVButton;
import com.team2337.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID.RumbleType; 
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.
	
	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	
	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());
	
	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());
	
	// Start the command when the button is released  and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	/////CONSTANTS//////
	//static double exampleConstant = constantsFile.KConstantname;
	
	
	
	////////////////////
	
	public static Joystick improvedJoystick;
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
	
	Joystick				operatorJoystick		= new Joystick(1);
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
	
	//Driver Station
	
	//Ask about the use of a third controller and if it's possible, re-map the operator interface controls onto the corrent controller
	Joystick				operatorControls		= new Joystick(2);
	
	//Ask about these buttons (which appeared to be mapped to enabling and disabling the robot last year)
	//What where their IDs and what did they do?
	//JoystickButton			operatorInt_GreenButton				= new JoystickButton(operatorJoystick, 19);
	//JoystickButton			operatorInt_RedButton				= new JoystickButton(operatorJoystick, 20);
    JoystickButton 			operatorInt_ClearSwitch				= new JoystickButton(operatorJoystick, 15);
	JoystickButton 			operatorInt_YellowSwitch			= new JoystickButton(operatorJoystick, 18);
	JoystickButton 			operatorInt_BlackSwitch				= new JoystickButton(operatorJoystick, 17);
	JoystickButton 			operatorInt_BlueSwitch				= new JoystickButton(operatorJoystick, 16);
	JoystickButton 			operatorInt_WhiteButton				= new JoystickButton(operatorJoystick, 14);
	JoystickButton 			operatorInt_YellowButton			= new JoystickButton(operatorJoystick, 13);
	JoystickButton 			operatorInt_BlackButton 			= new JoystickButton(operatorJoystick, 11);
	JoystickButton 			operatorInt_BlueButton				= new JoystickButton(operatorJoystick, 12);
    
    //////////////////////////////////////
    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
    //////////////////////////////////////
	
	public OI() {
		
		/* ====== DRIVER JOYSTICK ===== */
		
		driver_GreenA			.whenPressed(new _DoNothing());
		driver_RedB				.whenPressed(new _DoNothing());
		driver_BlueX			.whenPressed(new _DoNothing()); 
		driver_YellowY			.whenPressed(new _DoNothing());
		
		driver_BumperLeft		.whenPressed(new _DoNothing());
		driver_BumperRight		.whenPressed(new _DoNothing());
		
		driver_Back				.whileHeld(new _DoNothing()); 
		driver_Start			.whileHeld(new _DoNothing());
		
		driver_LeftStick		.whenPressed(new _DoNothing()); 
		driver_RightStick		.whenPressed(new _DoNothing()); 
		
		driver_TriggerLeft		.whileHeld(new _DoNothing());
		driver_TriggerRight		.whileHeld(new _DoNothing());
		
		driver_POVUp			.whenPressed(new _DoNothing());  
		//driver_POVUpRight		.whenPressed(new _DoNothing()); 
	    driver_POVRight			.whenPressed(new _DoNothing()); 
	   	//driver_POVDownRight	.whenPressed(new _DoNothing()); 
	    driver_POVDown			.whenPressed(new _DoNothing()); 
	   	//driver_POVDownLeft	.whenPressed(new _DoNothing()); 
	    driver_POVLeft			.whenPressed(new _DoNothing()); 
	   	//driver_POVUpLeft		.whenPressed(new _DoNothing()); 
	    
	    //////////////////////////////////
	    
	    
		/* ====== OPERATOR JOYSTICK ===== */
	    
		operator_GreenA			.whenPressed(new _DoNothing());
		operator_RedB			.whenPressed(new _DoNothing());
		operator_BlueX			.whenPressed(new _DoNothing());
		operator_YellowY		.whenPressed(new _DoNothing());
		
		operator_BumperLeft		.whenPressed(new _DoNothing());
		operator_BumperRight	.whenPressed(new _DoNothing());
		
		operator_Back			.whenPressed(new _DoNothing());
		operator_Start			.whenPressed(new _DoNothing());
		
		operator_LeftStick		.whenPressed(new _DoNothing());
		operator_RightStick		.whenPressed(new _DoNothing());
		
		operator_TriggerLeft	.whileHeld(new _DoNothing());
		operator_TriggerRight	.whileHeld(new _DoNothing());
		
		operator_POVUp			.whenPressed(new _DoNothing());
		//operator_POVUpRight	.whenPressed(new _DoNothing());
		operator_POVRight		.whenPressed(new _DoNothing());
		//operator_POVDownRight	.whenPressed(new _DoNothing());
	    operator_POVDown	    .whenPressed(new _DoNothing());
		//operator_POVDownLeft  .whenPressed(new _DoNothing());
		operator_POVLeft	    .whenPressed(new _DoNothing());
		//operator_POVUpLeft	.whenPressed(new _DoNothing());
		
		////////////////////////////////////
		
		
		/* ===== DRIVER STATION CONTROLS ===== */
		
		//operatorInt_GreenButton	.whenPressed(new _DoNothing());
		//operatorInt_RedButton	.whenPressed(new _DoNothing());
		
		operatorInt_ClearSwitch	.whenPressed(new _DoNothing());
		operatorInt_BlueSwitch	.whenPressed(new _DoNothing());
		operatorInt_BlackSwitch	.whenPressed(new _DoNothing());
		operatorInt_YellowSwitch.whenPressed(new _DoNothing());
		
		operatorInt_BlackButton	.whenPressed(new _DoNothing());
		operatorInt_BlueButton	.whenPressed(new _DoNothing());
		operatorInt_YellowButton.whenPressed(new _DoNothing());
		operatorInt_WhiteButton	.whenPressed(new _DoNothing());
		
		/////////////////////////////////////////
		
	}
	
    //////////////////////////////////////
    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
    //////////////////////////////////////
	
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


