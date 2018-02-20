package com.team2337.robot;

import com.team2337.fusion.controller.JoystickAnalogButton;
import com.team2337.fusion.controller.JoystickPOVButton;

import com.team2337.robot.commands.*;
import com.team2337.robot.commands.arm.*;
import com.team2337.robot.commands.auto.*;
import com.team2337.robot.commands.bigBrother.*;
import com.team2337.robot.commands.chassis.*;
import com.team2337.robot.commands.claw.*;
import com.team2337.robot.commands.climber.*;
import com.team2337.robot.commands.intake.*;
import com.team2337.robot.commands.led.*;
import com.team2337.robot.commands.lift.*;
import com.team2337.robot.commands.shifter.*;
import com.team2337.robot.commands.trolley.*;

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
	
	/*public static Joystick				operatorJoystick		= new Joystick(1);
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
	*/
	
	
	public static Joystick				operatorJoystick		= new Joystick(1);
	JoystickButton			operator_RightTrigger				= new JoystickButton(operatorJoystick, 1);	//Digital trigger on the back of the joystick
	JoystickButton			operator_StripedButton				= new JoystickButton(operatorJoystick, 2);	//The orange and black striped button on joystick
	JoystickButton			operator_RightKnucleButton			= new JoystickButton(operatorJoystick, 3);	//The button on the top-right of the joytstick
	JoystickButton			operator_L3							= new JoystickButton(operatorJoystick, 4);	//Button on the front right of the joystick
	
	JoystickButton			operator_ThrottleTopThumbButton		= new JoystickButton(operatorJoystick, 5);	//The highest button of the throttle's thumb buttons
	JoystickButton			operator_ThrottleMidThumbButton		= new JoystickButton(operatorJoystick, 6);	//The middle button of the throttle's thumb buttons
	JoystickButton			operator_ThrottleBottomThumbButton	= new JoystickButton(operatorJoystick, 7);	//The lowest button of the throttle's thumb buttons
	
	JoystickButton			operator_PalmButton					= new JoystickButton(operatorJoystick, 8);	//The button on the palmrest of the throttle
	JoystickButton			operator_TopIndexButton 			= new JoystickButton(operatorJoystick, 9);	//The higher button on the back right of the throttle
	JoystickButton			operator_BottomIndexButton			= new JoystickButton(operatorJoystick, 10);	//The lower button on the back right of the throttle
	
	//JoystickButton		operator_SE							= new JoystickButton(operatorJoystick, 11); //The "SE" button on the throttle
	//JoystickButton		operator_ST							= new JoystickButton(operatorJoystick, 12); //The "ST" button on the throttle
	
	JoystickPOVButton		operator_JoystickPOVUp				= new JoystickPOVButton(operatorJoystick, 0);
	JoystickPOVButton		operator_JoystickPOVUpRight			= new JoystickPOVButton(operatorJoystick, 45);
	JoystickPOVButton		operator_JoystickPOVRight			= new JoystickPOVButton(operatorJoystick, 90);
	JoystickPOVButton		operator_JoystickPOVDownRight		= new JoystickPOVButton(operatorJoystick, 135);
	JoystickPOVButton		operator_JoystickPOVDown			= new JoystickPOVButton(operatorJoystick, 180);
	JoystickPOVButton		operator_JoystickPOVDownLeft		= new JoystickPOVButton(operatorJoystick, 225);
	JoystickPOVButton		operator_JoystickPOVLeft			= new JoystickPOVButton(operatorJoystick, 270);
	JoystickPOVButton		operator_JoystickPOVUpLeft			= new JoystickPOVButton(operatorJoystick, 315);
	
	/*
		AXIS:
		#	Description		Direction   			Positive
		--	---------------	---------------------	--------
		0	Joystick tilt	Right/Left				Right
		1	Joystick tilt	Forward/back			Back
		2	Throttle tilt	Forward/Back			Back
		3	Joystick rotate	Right/Left (Rotation)	Right
		4	Throttle rocker	Right/Left (Rocker)		Right
	 */
	
	
	public static Joystick				operatorControls		= new Joystick(4);
	/*
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
     * OperatorControl
	 */
	
	public OI() {
		
		/* ====== DRIVER JOYSTICK ===== */
		
		driver_GreenA			.whenPressed(new DoNothing()); //.whenPressed(new trolley_setPID(0.7));
		driver_RedB				.whenPressed(new DoNothing()); //.whenPressed(new trolley_setPID(0.6));
		driver_BlueX			.whenPressed(new DoNothing()); //.whenPressed(new trolley_setPID(0.1)); 
		driver_YellowY			.whenPressed(new DoNothing()); //.whenPressed(new trolley_stopPID());
		
		driver_BumperLeft		.whenPressed(new DoNothing());
		driver_BumperRight		.whenPressed(new DoNothing());
		
		driver_Back				.whileHeld(new DoNothing()); 
		driver_Start			.whenPressed(new auto_gyroMMTurn());
		
		driver_LeftStick		.whenPressed(new DoNothing()); 
		driver_RightStick		.whenPressed(new DoNothing()); 
		
		//driver_TriggerLeft		.whileHeld(new intake_out(1));
		//driver_TriggerRight		.whileHeld(new intake_in(1));
		
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
	    /*
		operator_GreenA			.whenPressed(new DoNothing()); //.whenPressed(new trolley_setPID(0.7));
		operator_RedB			.whenPressed(new DoNothing()); //.whenPressed(new trolley_setPID(0.9));
		operator_BlueX			.whenPressed(new DoNothing()); //.whenPressed(new trolley_setPID(1.1));
		operator_YellowY		.whenPressed(new DoNothing()); //.whenPressed(new trolley_stopPID());
		
		operator_BumperLeft		.whenPressed(new claw_close());
		operator_BumperRight	.whenPressed(new claw_open());
		
		operator_Back			.whileHeld(new claw_giveLessHugs()); 
		operator_Start			.whileHeld(new claw_giveMoreHugs());
		
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
		*/
	    
	    //Operator 
	    operator_RightTrigger			       .whileHeld(new intake_in(1));
	    operator_StripedButton			       .whenPressed(new DoNothing());
	    operator_RightKnucleButton		       .whileHeld(new intake_out(1));
	    operator_L3						       .whenPressed(new DoNothing());
	                                           
	    operator_ThrottleTopThumbButton		   .whenPressed(new liftLevelAdjuster(3));
	    operator_ThrottleMidThumbButton		   .whenPressed(new liftLevelAdjuster(2));
	    operator_ThrottleBottomThumbButton	   .whenPressed(new liftLevelAdjuster(1));
	                                           
	    operator_PalmButton				       .whenPressed(new DoNothing());
	    operator_TopIndexButton 		       .whenPressed(new DoNothing());
	    operator_BottomIndexButton		       .whenPressed(new DoNothing());

	    //operator_SE						   .whenPressed(new DoNothing());
	    //operator_ST						   .whenPressed(new DoNothing());
	                                          
	    operator_JoystickPOVUp			       .whenPressed(new claw_open());
	    operator_JoystickPOVUpRight		       .whenPressed(new claw_open());
	    operator_JoystickPOVUpLeft		       .whenPressed(new claw_open());
	    
	    operator_JoystickPOVDownRight	       .whenPressed(new claw_close());
	    operator_JoystickPOVDown		       .whenPressed(new claw_close());
	    operator_JoystickPOVDownLeft	       .whenPressed(new claw_close());
	    
	    operator_JoystickPOVRight		       .whenPressed(new DoNothing());
	    operator_JoystickPOVLeft		       .whenPressed(new DoNothing());
	    
	    ////////////////////////////////////
		
		
		/* ===== DRIVER STATION CONTROLS ===== 
		
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
		*/
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


