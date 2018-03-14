package com.team2337.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team2337.fusion.drive.*;
import com.team2337.fusion.led.BlinkIn;
import com.team2337.fusion.vision.VisionProcessing;
import com.team2337.robot.Constants;
import com.team2337.robot.commands.auto.profiles.MotionProfile;
import com.team2337.robot.commands.auto.profiles.MotionProfileManager;

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

	public static TalonSRX chassis_leftFront;
	public static VictorSPX chassis_leftMid;
	public static VictorSPX chassis_leftRear;

	public static TalonSRX chassis_rightFront;
	public static VictorSPX chassis_rightMid;
	public static VictorSPX chassis_rightRear;
	
	public static TalonSRX intake_left;

	public static NerdyDrive drive;

	public static DigitalInput crateSensor;
	public static DigitalInput lineReader;
	// LEDs
	public static BlinkIn blinkin;

	public static Solenoid led_info;
	public static Accelerometer accel;
	
	public static MotionProfileManager motionManager;
	

	//*********************************************************************************************************
	//Debug
	public static Boolean alt_ControlDebug = false;
	public static Boolean chassisDebug = true;
	public static Boolean intakeDebug = false;
	public static Boolean robot_AllPeriodicDebug = true;
	public static Boolean clawPressureDash = true;
	
	//Public Variables
	public static Boolean disabledAtEndOfAuto = true;  		//Also set to true in Robot.TeleOpInit & Robot.disabledInint
	public static Boolean firstIntake = true;
	
	//CAN Ports
	private final static int chassisRightFront  = 0; //15
	private final static int chassisRightMid    = 1; //14
	private final static int chassisRightRear   = 2; //13
	private final static int climberRight       = 3; //9??  PTO off of lift
	private final static int trolleyRight       = 4;
	private final static int intakeRight        = 5;
	private final static int intakeLeft         = 6;
	private final static int armRight           = 7;
	private final static int liftRight          = 8; //11
	private final static int liftLeft           = 9; //12
	private final static int armLeft            = 10; //8
	private final static int trolleyLeft        = 11; //3
	private final static int climberLeft        = 12; //10;??  PTO off of lift
	private final static int chassisLeftRear    = 13; //2
	private final static int chassisLeftMid     = 14; //1
	private final static int chassisLeftFront   = 15; //0
	
	//Pnuematics
	//PCMs
	private final static int PCM_0 		   = 0;
	private final static int PDP_0		   = 0;
	
	//Ports
	private final static int clawHugger    = 2;
	private final static int clawClaw      = 3;
	private final static int shifterLeft   = 4;
	private final static int shifterRight  = 5;
	private final static int ledInfo       = 6;
	private final static int climb 		   = 7; //TBD
	
	public static void init() {
		
		/*
		 * Drive Left
		 */
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
		
		
		chassis_rightFront.configVoltageCompSaturation(12, 0);
		chassis_rightFront.enableVoltageCompensation(true);
		
		chassis_leftFront.configVoltageCompSaturation(12, 0);
		chassis_leftFront.enableVoltageCompensation(true);
		
		
		intake_left = new TalonSRX(intakeLeft);
	
			/*
			 * NerdyDrive Instance
			 */
			drive = new NerdyDrive(chassis_leftFront, chassis_rightFront);

		
		crateSensor = new DigitalInput(0);
		lineReader = new DigitalInput(1);


		/*
		 * VisionProcessing for PixyCam
		 */
		led_info = new Solenoid(PCM_0, ledInfo);

		
		/*
		 * BlinkIn 
		 * VisionProcessing for ?
		 */
		blinkin = new BlinkIn(0);
		
		//pdp = new PowerDistributionPanel(0);
		
		accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
		
		//motionManager = new MotionProfileManager(chassis_rightFront, 0, MotionProfile.Points,MotionProfile.kNumPoints);

}
}
