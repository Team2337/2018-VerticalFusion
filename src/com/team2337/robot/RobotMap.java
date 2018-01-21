package com.team2337.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.fusion.drive.*;
import com.team2337.fusion.vision.VisionProcessing;
import com.team2337.robot.subsystems.Lifter;
import com.team2337.robot.Constants;

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
	
	public static TalonSRX chassis_leftFront;
	public static TalonSRX chassis_leftMid;
	public static TalonSRX chassis_leftRear;
	
	public static TalonSRX chassis_rightFront;
	public static TalonSRX chassis_rightMid;
	public static TalonSRX chassis_rightRear;
	
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
	public static TalonSRX arm_left;
	public static TalonSRX arm_right;
	
	//Claw
	public static Solenoid claw_left;
	public static Solenoid claw_right;
	
	//Climber 
	public static TalonSRX climber_left;
	public static TalonSRX climber_right;
	
	//Shifter 
	public static Solenoid shifter_left;
	public static Solenoid shifter_right;
	
	//LEDs
	public static Solenoid led_info;
	
	public static VisionProcessing vision;
	
	public static void init () {
			/*
			 * Drive Left
			 */
			chassis_leftFront = new TalonSRX(8); //13
			chassis_leftMid = new TalonSRX(14); //14
			chassis_leftRear = new TalonSRX(15); //15
			
			chassis_leftFront.setInverted(true); 
			chassis_leftMid.follow(chassis_leftFront);
			chassis_leftMid.setInverted(true);
			chassis_leftRear.follow(chassis_leftFront);
			chassis_leftRear.setInverted(true);
		
			/*
			 * Drive Right 
			 */
			chassis_rightFront = new TalonSRX(7); //0
			chassis_rightMid = new TalonSRX(1); //1
			chassis_rightRear = new TalonSRX(2); //2
			
	
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
		
		lift_leftBack = new TalonSRX(4); //4
		lift_leftBack.follow(lift_leftFront);
		
		lift_rightFront = new TalonSRX(5); // 5
		lift_rightFront.setInverted(true);
		
		lift_rightBack = new TalonSRX(6); // 6
		lift_rightBack.follow(lift_rightFront);
		lift_rightFront.setInverted(true);
		
		lift_potentiometer = new AnalogPotentiometer(2, 10.0, 0.068);
	
		/*
		 * Intake
		 */
		intake_left = new TalonSRX(12); //6
		intake_left.setInverted(true);
		intake_right = new TalonSRX(11); //5

		/*
		 * Ejector
		 */
		ejector_push = new Solenoid(0,1); //1,0
		
		/*
		 * Arm
		 */
		arm_left = new TalonSRX(0);  //7
		arm_right = new TalonSRX(13); //8
		arm_right.setInverted(true);
		
		/*
		 * Claw
		 */
		claw_left = new Solenoid(0,2);
		claw_right = new Solenoid(0,3);
		
		/*
		 * Climber
		 */
		climber_left = new TalonSRX(9); //9
	    climber_right = new TalonSRX(10); //
		
	    /*
	     * Shifter
	     */
	    shifter_left = new Solenoid(1,3); //1,0
	    shifter_right = new Solenoid(1,4); //1,0
		
	    /*
	     * LED
	     */
	    led_info = new Solenoid(1,5);
	    
	    
	    
	  
	    /*
	     * VisionProcessing for PixyCam 
	     */
	    
		vision = new VisionProcessing("GRIP/vision");
		vision.setCameraVerticalOffset(Constants.TargetingCamera_VerticalOffset); //Offset from front of robot
		vision.setCameraHorizontalOffset(Constants.TargetingCamera_HorizontalOffset); //Offset from front of robot
		vision.setCameraWidth(Constants.TargetingCamera_CameraWidth); //Camera's width
		vision.setObjectHeight(Constants.TargetingCamera_ObjectHeight);  //In inches, height of the tap from the ground (eg Gear);
		vision.setWidthBetweenTarget(Constants.TargetingCamera_WidthBetweenTarget); //Amount in inches of how far apart the two CONSTANTStours are (or retoreflective tap)
		vision.setAngleConstant(Constants.TargetingCamera_AngleConstant);
		vision.setCenterConstant(Constants.TargetingCamera_CenterConstant);
		vision.setDistances(Constants.TargetingCamera_DistanceInchesMin, Constants.TargetingCamera_DistanceInchesMax);
		vision.setAreas(Constants.TargetingCamera_AreaMin, Constants.TargetingCamera_AreaMax);
		vision.setDegreePerPixel(Constants.TargetingCamera_PixelDegree);
		vision.setRevolutionPerDegree(Constants.TargetingCamera_RevDegree);
	}
}
