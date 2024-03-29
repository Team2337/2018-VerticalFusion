package com.team2337.robot;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.team2337.fusion.drive.*;
import com.team2337.fusion.led.BlinkIn;
import com.team2337.fusion.vision.VisionProcessing;
import com.team2337.robot.subsystems.Arm;
import com.team2337.robot.subsystems.Trolley;
import com.team2337.robot.Constants;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.hal.EncoderJNI;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
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

	
	/************************************/
	/* -------------------------------- */
	/* --- Motor & Sensor Variables --- */
	/* -------------------------------- */
	/************************************/

	/* --- Drive --- */
	public static TalonSRX chassis_leftFront;
	public static VictorSPX chassis_leftMid;
	public static VictorSPX chassis_leftRear;

	public static TalonSRX chassis_rightFront;
	public static VictorSPX chassis_rightMid;
	public static VictorSPX chassis_rightRear;

	public static NerdyDrive drive;
	
	/* --- Lift --- */
	public static VictorSPX lift_left;
	public static TalonSRX lift_right;
	
	/* --- Trolley --- */
	public static TalonSRX trolley_right;
	
	// Use for string pot  ***** in talon *****
	//public static AnalogPotentiometer lift_potentiometer; 
	//public static AnalogInput lift_stringPot;

	/* --- Intake --- */
	public static TalonSRX intake_left;
	public static VictorSPX intake_right;
	public static DigitalInput crateSensorLeft;
	public static DigitalInput crateSensorRight;

	/* --- Arm --- */
	public static VictorSPX arm_left;
	public static TalonSRX arm_right;

	public static Encoder enc;

	/* --- Claw --- */
	public static AnalogInput clawPressure;
	public static Solenoid claw_hugger;
	public static Solenoid claw_claw;

	/* --- Climber --- */
	public static Solenoid climb_ejector;
	public static VictorSPX climb_motor;
	
	public static Solenoid PTO;
	public static Solenoid PTO0;

	/* --- Shifter --- */
	public static Solenoid shifter_left;

	/* --- LEDs --- */
	public static BlinkIn blinkin;
	
	/* --- Vision --- */
	public static Solenoid led_info;

	public static VisionProcessing vision;
	
	public static DigitalInput lineReader;
	
	public static UsbCamera camera;
	
	public static Accelerometer accel;
	
	/*********************/
	/* ----------------- */
	/* --- Variables --- */
	/* ----------------- */
	/*********************/

	/* --- Debug --- */
	public static Boolean alt_ControlDebug = false;
	public static Boolean chassisDebug = false;
	public static Boolean intakeDebug = false;
	public static Boolean climbDebug = false;
	public static Boolean robot_AllPeriodicDebug = true;
	public static Boolean clawPressureDash = true;
	public static Boolean crateBypass = false;
	
	/* --- Public --- */
	public static boolean brokenArmPos = false;
	public static boolean brokenArmNeg = false;
	public static boolean brokenArm = false;
	public static Boolean disabledAtEndOfAuto = true;  		//Also set to true in Robot.TeleOpInit & Robot.disabledInint
	public static Boolean firstIntake = false;
	
	/* --- CAN Ports --- */
	private final static int chassisRightFront  = 0; 
	private final static int chassisRightMid    = 1; 
	private final static int chassisRightRear   = 2; 

	private final static int trolleyRight       = 4;
	private final static int intakeRight        = 5;
	private final static int intakeLeft         = 6;
	private final static int armRight           = 7;
	private final static int liftRight          = 8; 
	private final static int liftLeft           = 9; 
	private final static int armLeft            = 10; 
	private final static int climbMotor         = 11; 
	private final static int chassisLeftRear    = 13; 
	private final static int chassisLeftMid     = 14; 
	private final static int chassisLeftFront   = 15; 
	
	//Pnuematics
	/* --- PCMs --- */
	private final static int PCM_0 		   = 0;
	private final static int PDP_0		   = 0;
	
	/* --- Ports --- */
	private final static int clawHugger    = 2;
	private final static int clawClaw      = 3;
	private final static int shifterLeft   = 6;
	private final static int climbEjector  = 5;
	//private final static int ledInfo       = 6;
	private final static int climb 		   = 7; 
	
	public static void init() {
		
		/****************************************/
		/* ------------------------------------ */
		/* --- Motor & Sensor Initalizatoin --- */
		/* ------------------------------------ */
		/****************************************/

		/* --- Drive Left --- */
		chassis_leftFront = new TalonSRX(chassisLeftFront);
		chassis_leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		chassis_leftFront.setSensorPhase(false);

		chassis_leftMid = new VictorSPX(chassisLeftMid);
		chassis_leftRear = new VictorSPX(chassisLeftRear);

		chassis_leftFront.setInverted(false);
		chassis_leftMid.setInverted(false);
		chassis_leftRear.setInverted(false);

		chassis_leftRear.follow(chassis_leftFront);
		chassis_leftMid.follow(chassis_leftFront);

		/* --- Drive Right --- */
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
		
		
		chassis_rightFront.configVoltageCompSaturation(12, 0);
		chassis_rightFront.enableVoltageCompensation(true);
		
		chassis_leftFront.configVoltageCompSaturation(12, 0);
		chassis_leftFront.enableVoltageCompensation(true);
	
		/* --- Nerdy Drive --- */
		drive = new NerdyDrive(chassis_leftFront, chassis_rightFront);
			
		
		/* --- Lift --- */
		lift_right = new TalonSRX(liftRight); // 5
		lift_right.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0); //string pot
		lift_right.setSensorPhase(false);
		lift_right.setInverted(false);
		lift_right.setStatusFramePeriod(0, 0, 0);
		lift_right.setNeutralMode(NeutralMode.Brake);
		
		lift_left = new VictorSPX(liftLeft); // 6
		lift_left.follow(lift_right);
		lift_left.setInverted(true);
		lift_left.setNeutralMode(NeutralMode.Brake);
		
		lift_right.configForwardSoftLimitEnable(false, 0);
		lift_left.configForwardSoftLimitEnable(false, 0);

		lift_right.configReverseSoftLimitEnable(false, 0);
		lift_left.configReverseSoftLimitEnable(false, 0);
		
		
		/* --- Trolley --- */
		trolley_right = new TalonSRX(trolleyRight); // 4
		trolley_right.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);  //string pot
		trolley_right.setSensorPhase(false);//set false for comp
		trolley_right.setInverted(false);//set false for comp
		trolley_right.setStatusFramePeriod(0, 0, 0);
		trolley_right.setNeutralMode(NeutralMode.Brake);
		trolley_right.configForwardSoftLimitEnable(false, 0);
		trolley_right.configReverseSoftLimitEnable(false, 0);


		/* --- Intake --- */
		intake_left = new TalonSRX(intakeLeft);
		intake_left.setInverted(true);
		
		intake_right = new VictorSPX(intakeRight);
		intake_right.setInverted(false);
		
		crateSensorLeft = new DigitalInput(0);
		lineReader = new DigitalInput(1);
		crateSensorRight = new DigitalInput(2);


		/* --- arm --- */
		arm_right = new TalonSRX(armRight);
		arm_right.setInverted(false);
		arm_right.setNeutralMode(NeutralMode.Brake);
		arm_right.configClosedloopRamp(0.25, 0);
		
		arm_left = new VictorSPX(armLeft);
		arm_left.setInverted(true);		
		arm_left.follow(arm_right);
		arm_left.setNeutralMode(NeutralMode.Brake);
		arm_left.configClosedloopRamp(0.25, 0);

		arm_right.setStatusFramePeriod(0, 0, 0);
		//arm_right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		arm_right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		arm_right.setSensorPhase(false);

		arm_right.configForwardSoftLimitEnable(true, 0);
		arm_left.configForwardSoftLimitEnable(false, 0);

		arm_right.configReverseSoftLimitEnable(true, 0);
		arm_left.configReverseSoftLimitEnable(false, 0);
		
		arm_right.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 10);
		

		/* --- Claw --- */
		clawPressure = new AnalogInput(0);
		claw_hugger = new Solenoid(PCM_0, clawHugger);
		claw_claw = new Solenoid(PCM_0, clawClaw);


		/* --- CLimber --- */
		climb_motor = new VictorSPX(climbMotor); // 11
		climb_motor.setInverted(false);
		climb_motor.setNeutralMode(NeutralMode.Brake);
		
		PTO = new Solenoid(PCM_0, climb);
		PTO0 = new Solenoid(PCM_0, 0);


		/* --- Shifter --- */
		shifter_left = new Solenoid(PCM_0, shifterLeft); // 1,0
		climb_ejector = new Solenoid(PCM_0, climbEjector); // 1,0


		/* --- Blinkin --- */
		blinkin = new BlinkIn(0);
		
		
		/* --- Accelerometer --- */
		accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
	}

	/**
	 * Starts the camera for driver vision
	 * @author Team2337 - EngiNERDs
	 */
	public static void startCamera() {
		try {
			camera = CameraServer.getInstance().startAutomaticCapture("cam0", "/dev/video0");
			camera.setPixelFormat(PixelFormat.kYUYV);
			camera.setResolution(544, 288);
			camera.setFPS(30);
		} catch (Exception e) {
			DriverStation.reportWarning("[Camera] Could not start the camera!", true);
		}
	}
}
