package com.team2337.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.fusion.drive.*;
import com.team2337.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * Organized by most important 
 * 
 * @author Team2337 - EngiNERDs
 */
public class RobotMap {
	
	public static NerdyDrive drive;
	
	//Lift 
	public static TalonSRX lift_leftFront;
	public static TalonSRX lift_rightFront;
	public static TalonSRX lift_leftBack;
	public static TalonSRX lift_rightBack;
	public static AnalogPotentiometer lift_potentiometer;  //Use for string pot
	

	//Intake 
	public static TalonSRX intake_left;
	public static TalonSRX intake_right;
	
	//Ejector 
	public static Solenoid ejector_push;
	
	//Extender 
	public static Solenoid extender_left;
	public static Solenoid extender_right;
	
	//Climber 
	public static TalonSRX climber_left;
	public static TalonSRX climber_right;
	
	//Shifter 
	public static Solenoid shifter_left;
	public static Solenoid shifter_right;
	
	//LEDs
	public static Solenoid led_info;
	
	public static void init () {
			/*
			 * Drive Left
			 */
			TalonSRX chassis_leftFront = new TalonSRX(13); //13
			TalonSRX chassis_leftMid = new TalonSRX(14); //14
			TalonSRX chassis_leftRear = new TalonSRX(15); //15
			
			chassis_leftFront.setInverted(true); 
			chassis_leftMid.follow(chassis_leftFront);
			chassis_leftMid.setInverted(true);
			chassis_leftRear.follow(chassis_leftFront);
			chassis_leftRear.setInverted(true);
		
			/*
			 * Drive Right 
			 */
			TalonSRX chassis_rightFront = new TalonSRX(0); //0
			TalonSRX chassis_rightMid = new TalonSRX(1); //1
			TalonSRX chassis_rightRear = new TalonSRX(2); //2
			
	
			chassis_rightMid.follow(chassis_rightFront);
			chassis_rightRear.follow(chassis_rightFront);
	
			/*
			 * NerdyDrive Instance
			 */
			drive = new NerdyDrive(chassis_leftFront, chassis_rightFront);
			drive.setSensitivity(0.5);
			
		
		/*
		 * Lift
		 */
		lift_leftFront = new TalonSRX(3); //3
		
		lift_leftBack = new TalonSRX(4); //3
		lift_leftBack.follow(lift_leftFront);
		
		lift_rightFront = new TalonSRX(5); // 4
		lift_rightFront.setInverted(true);
		
		lift_rightBack = new TalonSRX(6); // 4
		lift_rightBack.follow(lift_rightFront);
		lift_rightFront.setInverted(true);
		
		lift_potentiometer = new AnalogPotentiometer(2, 10.0, 0.068);
	
		/*
		 * Intake
		 */
		intake_left = new TalonSRX(11); //6
		intake_left.setInverted(true);
		intake_right = new TalonSRX(10); //5

		/*
		 * Ejector
		 */
		ejector_push = new Solenoid(1,0); //1,0
		
		/*
		 * Extender
		 */
		extender_left = new Solenoid(1,1); //1,1
		extender_right = new Solenoid(1,2); //1,1
		
		/*
		 * Climber
		 */
		climber_left = new TalonSRX(7); //7
	    climber_right = new TalonSRX(8); //8
		
	    /*
	     * Shifter
	     */
	    shifter_left = new Solenoid(1,3); //1,0
	    shifter_right = new Solenoid(1,4); //1,0
		
	    /*
	     * LED
	     */
	    led_info = new Solenoid(1,5);
	}
}
