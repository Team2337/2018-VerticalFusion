package com.team2337.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team2337.fusion.drive.*;
import com.team2337.fusion.vision.VisionProcessing;
import com.team2337.robot.subsystems.Arm;
import com.team2337.robot.subsystems.Trolley;
import com.team2337.robot.Constants;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.hal.EncoderJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	public static VictorSPX chassis_leftMid;
	public static VictorSPX chassis_leftRear;

	public static TalonSRX chassis_rightFront;
	public static VictorSPX chassis_rightMid;
	public static VictorSPX chassis_rightRear;

	public static NerdyDrive drive;
	
	// Lift
	public static TalonSRX lift_left;
	public static TalonSRX lift_right;
	
	//Trolley ******
	public static TalonSRX trolley_left;
	public static TalonSRX trolley_right;
	
	//public static AnalogPotentiometer lift_potentiometer; // Use for string pot  ***** in talon *****
	//public static AnalogInput lift_stringPot;

	// Intake
	public static TalonSRX intake_left;
	public static VictorSPX intake_right;
	//public static TalonSRX intake_right;

	// Ejector
	public static Solenoid ejector_push;    //** not needed?

	// Arm
	public static TalonSRX arm_left;
	public static TalonSRX arm_right;

	public static Encoder enc;
	// public static double encoderValue = enc.get();

	// Claw
	public static Solenoid claw_hugger;
	public static Solenoid claw_claw;

	// Climber
	public static TalonSRX climber_left;
	public static TalonSRX climber_right;

	// Shifter
	public static Solenoid shifter_left;
	public static Solenoid shifter_right;

	// LEDs
	public static Solenoid led_info;

	
	public static DigitalInput crateSensor;
	public static VisionProcessing vision;
	
	
	public static UsbCamera camera;
	
	//Debug
	public static Boolean alt_ControlDebug = true;
	public static Boolean chassisDebug = false;
	
	//Public Variables
	public static Boolean endOfAuto = true;  //Also set to true in Robot.TeleOpInit
	
	//CAN Ports
	static int chassisRightFront  = 0;
	static int chassisRightMid    = 1;
	static int chassisRightRear   = 2;
	static int trolleyLeft        = 3;
	static int trolleyRight       = 4;
	static int intakeRight        = 5;
	static int intakeLeft         = 6;
	static int armRight           = 7;
	static int armLeft            = 8;
	static int climberRight       = 9;
	static int climberLeft        = 10;
	static int liftRight          = 11;
	static int liftLeft           = 12;
	static int chassisLeftRear    = 13;
	static int chassisLeftMid     = 14;
	static int chassisLeftFront   = 15;
	
	//Pnuematics
	//PCMs
	static int PCM_0 = 0;
	//Ports
	static int clawHugger    = 2;
	static int clawClaw      = 3;
	static int shifterLeft   = 4;
	static int shifterRight  = 5;
	static int ledInfo       = 6;
	
	public static void init() {
		
		/*
		 * z Drive Left
		 */
		chassis_leftFront = new TalonSRX(chassisLeftFront);
		chassis_leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		chassis_leftFront.setSensorPhase(false);

		chassis_leftMid = new VictorSPX(chassisLeftMid);
		chassis_leftRear = new VictorSPX(chassisLeftRear);

		chassis_leftFront.setInverted(true);
		chassis_leftMid.setInverted(true);
		chassis_leftRear.setInverted(true);

		chassis_leftRear.follow(chassis_leftFront);
		chassis_leftMid.follow(chassis_leftFront);

		/*
		 * Drive Right
		 */
		chassis_rightFront = new TalonSRX(chassisRightFront);
		chassis_rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		chassis_rightFront.setSensorPhase(false);

		chassis_rightMid = new VictorSPX(chassisRightMid); 
		chassis_rightRear = new VictorSPX(chassisRightRear); 

		chassis_rightFront.setInverted(true);
		chassis_rightMid.setInverted(true);
		chassis_rightRear.setInverted(true);

		chassis_rightMid.follow(chassis_rightFront);
		chassis_rightRear.follow(chassis_rightFront);

	
			/*
			 * NerdyDrive Instance
			 */
			drive = new NerdyDrive(chassis_leftFront, chassis_rightFront);
			
		/*
		 * Lift    //***********************************************
		 */

		lift_right = new TalonSRX(liftRight); // 5
		lift_right.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0); //string pot
		lift_right.setSensorPhase(false);
		lift_right.setInverted(true);
		lift_right.setStatusFramePeriod(0, 0, 0);
		
		lift_left = new TalonSRX(liftLeft); // 6
		lift_left.follow(lift_right);
		lift_left.setInverted(true);
		
		trolley_right = new TalonSRX(trolleyRight); // 3
		trolley_right.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);  //string pot
		trolley_right.setSensorPhase(false);
		trolley_right.setInverted(true);
		trolley_right.setStatusFramePeriod(0, 0, 0);

		trolley_left = new TalonSRX(trolleyLeft); // 4
		trolley_left.follow(trolley_right);
		trolley_left.setInverted(true);

		lift_right.configForwardSoftLimitEnable(false, 0);
		lift_left.configForwardSoftLimitEnable(false, 0);

		lift_right.configReverseSoftLimitEnable(false, 0);
		lift_left.configReverseSoftLimitEnable(false, 0);

		trolley_right.configForwardSoftLimitEnable(false, 0);
		trolley_left.configForwardSoftLimitEnable(false, 0);

		trolley_right.configReverseSoftLimitEnable(false, 0);
		trolley_left.configReverseSoftLimitEnable(false, 0);
	

		/*
		 * Intake
		 */
		intake_left = new TalonSRX(intakeLeft); // 6
		intake_left.setInverted(true);
		
		intake_right = new VictorSPX(intakeRight); // 5
		intake_right.setInverted(false);
		
		crateSensor = new DigitalInput(0);
		
		/*
		 * Arm
		 */
		arm_right = new TalonSRX(armRight); // 7
		arm_right.setInverted(false);
		arm_left = new TalonSRX(armLeft); // 8
		arm_left.setInverted(true);
		arm_left.follow(arm_right);
		arm_left.setNeutralMode(NeutralMode.Brake);

		arm_right.setStatusFramePeriod(0, 0, 0);
		arm_right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		arm_right.setSensorPhase(false);
		arm_right.setNeutralMode(NeutralMode.Brake);

		arm_right.configForwardSoftLimitEnable(true, 0);
		arm_left.configForwardSoftLimitEnable(false, 0);


		arm_right.configReverseSoftLimitEnable(true, 0);
		arm_left.configReverseSoftLimitEnable(false, 0);
		
		/*
		 * Claw
		 */
		claw_hugger = new Solenoid(PCM_0, clawHugger);
		claw_claw = new Solenoid(PCM_0, clawClaw);

		/*
		 * Climber
		 */
		climber_left = new TalonSRX(climberLeft); // 10
		climber_right = new TalonSRX(climberRight); // 9

		/*
		 * Shifter
		 */
		shifter_left = new Solenoid(PCM_0, shifterLeft); // 1,0
		shifter_right = new Solenoid(PCM_0, shifterRight); // 1,0

		/*
		 * LED
		 */
		led_info = new Solenoid(PCM_0, ledInfo);

		/*
		 * VisionProcessing for PixyCam
		 */

		vision = new VisionProcessing("GRIP/vision");
		vision.setCameraVerticalOffset(Constants.TargetingCamera_VerticalOffset); // Offset from front of robot
		vision.setCameraHorizontalOffset(Constants.TargetingCamera_HorizontalOffset); // Offset from front of robot
		vision.setCameraWidth(Constants.TargetingCamera_CameraWidth); // Camera's width
		vision.setObjectHeight(Constants.TargetingCamera_ObjectHeight); // In inches, height of the tap from the ground
																		// (eg Gear);
		vision.setWidthBetweenTarget(Constants.TargetingCamera_WidthBetweenTarget); // Amount in inches of how far apart
																					// the two CONSTANTStours are (or
																					// retoreflective tap)
		vision.setAngleConstant(Constants.TargetingCamera_AngleConstant);
		vision.setCenterConstant(Constants.TargetingCamera_CenterConstant);
		vision.setDistances(Constants.TargetingCamera_DistanceInchesMin, Constants.TargetingCamera_DistanceInchesMax);
		vision.setAreas(Constants.TargetingCamera_AreaMin, Constants.TargetingCamera_AreaMax);
		vision.setDegreePerPixel(Constants.TargetingCamera_PixelDegree);
		vision.setRevolutionPerDegree(Constants.TargetingCamera_RevDegree);
	}
	public static void startCamera() {
		try {
			camera = CameraServer.getInstance().startAutomaticCapture("cam0", "/dev/video0");
			camera.setFPS(30);
			camera.setResolution(544, 288);
			camera.setPixelFormat(PixelFormat.kYUYV);
		} catch (Exception e) {
			DriverStation.reportWarning("[Camera] Could not start the camera!", true);
		}
	}
}
