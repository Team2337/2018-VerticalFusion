package com.team2337.robot;

import com.team2337.fusion.controller.JoystickAnalogButton;
import com.team2337.fusion.controller.JoystickPOVButton;

import com.team2337.robot.commands.*;
import com.team2337.robot.commands.PTO.PTO_Climb;
import com.team2337.robot.commands.PTO.PTO_Lift;
import com.team2337.robot.commands.arm.*;
import com.team2337.robot.commands.auto.*;
import com.team2337.robot.commands.bigBrother.*;
import com.team2337.robot.commands.chassis.*;
import com.team2337.robot.commands.claw.*;
import com.team2337.robot.commands.climbWinch.climbWinch_driveVertical;
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

	public final int yellowSwitch = 2;
	public final int blackSwitch  = 3;
	public final int blueSwitch	  = 4;
	public final int blueButton   = 8;
	
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
	
	JoystickButton			operator_SE							= new JoystickButton(operatorJoystick, 11); //The "SE" button on the throttle
	JoystickButton			operator_ST							= new JoystickButton(operatorJoystick, 12); //The "ST" button on the throttle
	
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
	
	
	public static Joystick				operatorControls		= new Joystick(2);
	JoystickButton			operatorInt_GreenButton				= new JoystickButton(operatorControls, 19);
	JoystickButton			operatorInt_RedButton				= new JoystickButton(operatorControls, 20);
    JoystickButton 			operatorInt_ClearSwitch				= new JoystickButton(operatorControls, 15);
	JoystickButton 			operatorInt_YellowSwitch			= new JoystickButton(operatorControls, yellowSwitch);
	JoystickButton 			operatorInt_BlackSwitch				= new JoystickButton(operatorControls, blackSwitch);
	JoystickButton 			operatorInt_BlueSwitch				= new JoystickButton(operatorControls, blueSwitch);
	JoystickButton 			operatorInt_WhiteButton				= new JoystickButton(operatorControls, 14);
	JoystickButton 			operatorInt_YellowButton			= new JoystickButton(operatorControls, 6);
	JoystickButton 			operatorInt_BlueButton				= new JoystickButton(operatorControls, blueButton);
	JoystickButton 			operatorInt_BlackButton 			= new JoystickButton(operatorControls, 7);
     /* OperatorControl*/
	
	public OI() {
		
		/* ====== DRIVER JOYSTICK ===== */
		
		driver_GreenA			.whileHeld(new climbWinch_driveVertical(1));
		driver_RedB				.whenPressed(new DoNothing()); 
		driver_BlueX			.whenPressed(new DoNothing());  
		driver_YellowY			.whileHeld(new lift_climb()); 
		
		driver_BumperLeft		.whileHeld(new shifter_low());
		driver_BumperLeft		.whenReleased(new shifter_high());
		driver_BumperRight	    .whenPressed(new shifter_high());

		
		driver_Back				.whenPressed(new DoNothing()); 
		driver_Start			.whenPressed(new DoNothing()); 
		
		driver_LeftStick		.whenPressed(new DoNothing()); 
		driver_RightStick		.whenPressed(new DoNothing()); 
		
		driver_TriggerLeft		.whileHeld(new DoNothing());
		driver_TriggerRight		.whileHeld(new DoNothing());
		
		driver_POVUp			.whenPressed(new DoNothing());  
		//driver_POVUpRight		.whenPressed(new _DoNothing()); 
	    driver_POVRight			.whenPressed(new DoNothing()); 
	   	//driver_POVDownRight	.whenPressed(new _DoNothing()); 
	    driver_POVDown			.whenPressed(new DoNothing()); 
	   	//driver_POVDownLeft	.whenPressed(new _DoNothing()); 
	    driver_POVLeft			.whenPressed(new DoNothing()); 
	   	//driver_POVUpLeft		.whenPressed(new _DoNothing()); 
	    
	    //////////////////////////////////
	    
   
	    operator_RightTrigger			       .whileHeld(new intake_in(1));
	    operator_StripedButton			       .whenPressed(new claw_CGOpen());
	    operator_StripedButton				   .whenReleased(new claw_CGClose());
	    operator_RightKnucleButton		       .whileHeld(new intake_out(1));
	    operator_RightKnucleButton		       .whenReleased(new auto_clawCGOpenClose());
	    operator_L3						       .whileHeld(new intake_out(0.6));
	    operator_L3						       .whenReleased(new auto_clawCGOpenClose());
	                                           
	    operator_ThrottleTopThumbButton		   .whenPressed(new liftLevelAdjuster(3));
	    operator_ThrottleMidThumbButton		   .whenPressed(new liftLevelAdjuster(2));
	    operator_ThrottleBottomThumbButton	   .whenPressed(new liftLevelAdjuster(1));
	                                           
	    operator_PalmButton				       .whenPressed(new liftLevelAdjuster(11));
	    operator_TopIndexButton				   .whenPressed(new DoNothing());
	    operator_BottomIndexButton		       .whenPressed(new liftLevelAdjuster(12));

//	    operator_SE						 	   .whileHeld(new climbWinch_driveVertical(1));     
//	    operator_ST						  	   .whenPressed(new climber_ejector());
	    
	    operator_SE						 	   .whileHeld(new CG_defenseMode()); 
	    operator_ST						  	   .whenPressed(new CG_returnToALTControl());  
	    
	    operator_JoystickPOVUp			       .whenPressed(new claw_CGOpenNoSensor());	//previously: claw give 60 
	    operator_JoystickPOVUp				   .whenReleased(new claw_CGCloseNoSensor());
	    operator_JoystickPOVUpRight		       .whenPressed(new claw_CGOpenNoSensor());
	    operator_JoystickPOVUpRight			   .whenReleased(new claw_CGCloseNoSensor());
	    operator_JoystickPOVUpLeft		       .whenPressed(new claw_CGOpenNoSensor());
	    operator_JoystickPOVUpLeft			   .whenReleased(new claw_CGCloseNoSensor());
	    
	    operator_JoystickPOVDownRight	       .whenPressed(new DoNothing());
	    operator_JoystickPOVDown		       .whenPressed(new DoNothing());
	    operator_JoystickPOVDownLeft	       .whenPressed(new DoNothing());
	    
	    operator_JoystickPOVRight		       .whenPressed(new DoNothing());
	    operator_JoystickPOVLeft		       .whenPressed(new DoNothing());
	    
	    ////////////////////////////////////
		
		
		/* ===== DRIVER STATION CONTROLS ===== */
		
//		operatorInt_GreenButton	.whenPressed(new _DoNothing());
//		operatorInt_RedButton	.whenPressed(new _DoNothing());
		
//		operatorInt_ClearSwitch	.whenPressed(new DoNothing());
	    operatorInt_YellowSwitch.whenPressed(new DoNothing());		//this is being used in ALTControl to disable arm
	    operatorInt_BlackSwitch	.whenPressed(new DoNothing());		//this is being used in ALTControl to disable trolley
		operatorInt_BlueSwitch	.whenPressed(new DoNothing());		//this is being used in ALTControl to disable lift
		
//		operatorInt_BlackButton	.whenPressed(new PTO_Climb());
//		operatorInt_BlueButton	.whileHeld(new lift_climb());
//		operatorInt_YellowButton.whenPressed(new PTO_Lift());
//		operatorInt_WhiteButton	.whenPressed(new DoNothing());
		
		operatorInt_BlackButton .whenPressed(new CG_returnToALTControl());
		operatorInt_YellowButton.whenPressed(new CG_defenseMode());
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


