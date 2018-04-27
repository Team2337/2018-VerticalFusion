package com.team2337.robot;

import com.team2337.robot.subsystems.Chassis;
import com.team2337.robot.subsystems.Claw;
import com.team2337.robot.subsystems.ClimbPiston;
import com.team2337.robot.subsystems.ClimbWinch;

import java.io.IOException;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2337.fusion.address.Address;
import com.team2337.fusion.gyro.Pigeon;
import com.team2337.fusion.wrappers.auto.AutoCommandManager;
import com.team2337.robot.commands.DoNothing;
import com.team2337.robot.commands.auto.*;
import com.team2337.robot.subsystems.Arm;
import com.team2337.robot.subsystems.BigBrother;
import com.team2337.robot.subsystems.Intake;
import com.team2337.robot.subsystems.LED;
import com.team2337.robot.subsystems.Lift;
import com.team2337.robot.subsystems.PTO;
import com.team2337.robot.subsystems.PixyVision;
import com.team2337.robot.subsystems.Trolley;
import com.team2337.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Chassis chassis;
	public static Arm arm;
	public static Intake intake;
	public static LED led;
	public static Trolley trolley;
	public static Shifter shifter;
	public static ClimbPiston climber;
	public static ClimbWinch climbWinch;
	public static Claw claw;
	public static BigBrother bigBrother;
	public static Lift lift;
	public static PTO pto;
	public static OI oi;
	public static Pigeon gyro;
	public static PixyVision pixy;
	public static String ourswitch = "q";
	public static String scale = "q";
	public static String oppswitch = "q";
	public static String gameData = null;

	public static boolean isComp = false;
	// public static char ourswitch, scale, oppswitch;

	Command m_autonomousCommand;
	SendableChooser<String> autonchooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		String mac;
		mac = Address.getInstance().getMAC();
		/*
		if (mac.equals("00:80:2F:17:89:85")) {
			System.out.println("TestBoard " + mac);
			isComp = false;
		} else if(mac.equals("00:80:2F:19:21:E1")) {
			System.out.println("PracticeBot " + mac);
			isComp = false;
		} else {  //00-80-2F-17-E5-D2
			System.out.println("CompBot " + mac);
			isComp = true;
		}
		*/
		isComp = true;
		
		// Initialize all of the Robots Mappings
		RobotMap.init();
		// Also start the camera(s)
		//RobotMap.startCamera();

		// Reference all of the subsystems

		chassis = new Chassis();
		trolley = new Trolley();
		intake = new Intake();
		arm = new Arm();
		climber = new ClimbPiston();
		climbWinch = new ClimbWinch();
		shifter = new Shifter();
		led = new LED();
		claw = new Claw();
		lift = new Lift();
		bigBrother = new BigBrother();	
		pto = new PTO();
		gyro = new Pigeon();
		pixy = new PixyVision();
		oi = new OI();

		System.out.println("Disabled Game Data Values: " + gameData + "\nOurSwitch: " + ourswitch + "   Scale: " + scale + "   OppSwitch: " + oppswitch);
		
		AutoCommandManager.getInstance().init();
		AutoCommandManager.getInstance().setBlinkin(RobotMap.blinkin);

		// Also include the Auton Chooser
		Robot.gyro.resetPidgey();
		Robot.chassis.resetEncoders();
		Robot.pto.PTOLift();

		// RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, 0);  //replaced by above method
		// RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, 0);

		autonchooser.addObject("Do Nothing", "DoNothing");
		autonchooser.addObject("Cross the Line", "CrossLine");
		autonchooser.addDefault("Center Switch", "CenterSwitch");
		autonchooser.addObject("Center Switch Then Go Around", "SwitchThenGoAround");
		
//		autonchooser.addObject("Scale From Left", "ScaleLeft");
//		autonchooser.addObject("Scale From Right", "ScaleRight");
		autonchooser.addObject("Scale From Left Score On Side", "ScaleLeftSide");
		autonchooser.addObject("Scale From Right Score On Side", "ScaleRightSide");
		autonchooser.addObject("*Scale Greedy Right", "ScaleRightSideMutli");
		autonchooser.addObject("*Scale Greedy Left Red", "ScaleLeftSideMutli");
		autonchooser.addObject("*Scale Greedy Left Blue", "ScaleLeftSideMutliBlue");
//		autonchooser.addObject("*Scale Left Only with Switches", "ScaleLeftSideMutliWithSwitch");
		autonchooser.addObject("*Partner Scale On Our Left", "PartnerScaleOnOurLeft");
		autonchooser.addObject("*Partner Scale On Our Right", "PartnerScaleOnOurRight");
		autonchooser.addObject("*Favor Opponents Scale", "FavorOpponentsScale");
		autonchooser.addObject("*Favor Our Scale Solo", "FavorOurScaleSolo");
		autonchooser.addObject("*Bees Match", "CG_beesMatch");
		autonchooser.addObject("*Triple Left Scale Single Right","CG_scaleFromLeftMultiCubeFarRightScale");
		autonchooser.addObject("*Switch From Center Pyramid Cube to Scale", "CG_pyramid");
//		autonchooser.addObject("LiftUpperPosition", "LiftUpperPosition");
//		autonchooser.addObject("PRACTICE CenterSwitchRight", "CenterSwitchRight");
//		autonchooser.addObject("PRACTICE CenterSwitchLeft", "CenterSwitchLeft");
	
		//autonchooser.addObject("TESTUTurn", "UTurn");
		//autonchooser.addObject("TESTLineRead", "line");
		

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		AutoCommandManager.getInstance().disable();
		Pigeon.pidgey.addYaw(0.0, 10);
		RobotMap.disabledAtEndOfAuto = true;
		this.allInit();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		this.allPeriodic();

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		AutoCommandManager.getInstance().auton();
		Robot.gyro.resetPidgey();
		RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, 0);
		RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, 0);
		Robot.trolley.maxSpeedUp = 0.5;

		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		System.out.println("*************************Game Data from FMS: " + gameData + "*************************************");
		System.out.println("*************************Game Data from FMS: " + "***************************************");
		System.out.println("Game Data from FMS: ");
		
		
		ourswitch = gameData.substring(0, 1);
		scale = gameData.substring(1, 2);
		oppswitch = gameData.substring(2, 3);

		String selected = autonchooser.getSelected();

		switch (selected) {
		case "CenterSwitch":
			m_autonomousCommand = new CG_centerSwitch(ourswitch, scale);
			break;
		case "CenterSwitchScaleOurSide":
			m_autonomousCommand = new CG_centerSwitchThenScaleOurSide(ourswitch, scale);
			break;
		case "CenterSwitchScaleTheirSide":
			m_autonomousCommand = new CG_centerSwitchThenScaleTheirSide(ourswitch, scale);
			break;
		case "CrossLine":
			m_autonomousCommand = new CG_crossTheLine();
			break;
		case "DoNothing":
			m_autonomousCommand = new CG_autoDoNothing(ourswitch, scale);
			break;
		case "ScaleLeftSideMutli":
			m_autonomousCommand = new CG_scaleFromLeftMultiCubeFast(ourswitch, scale);//CG_scaleFromLeftMultiCube2(ourswitch, scale);
			break;
		case "ScaleLeftSideMutliBlue":
			m_autonomousCommand = new CG_scaleFromLeftMultiCubeFastBlue(ourswitch, scale);//CG_scaleFromLeftMultiCube2(ourswitch, scale);
			break;
		case "ScaleRightSideMutli":
			m_autonomousCommand = new CG_scaleFromRightMultiCubeFast(ourswitch, scale);
			break;
		case "ScaleLeftSideMutliWithSwitch":
			m_autonomousCommand = new CG_scaleFromLeftMultiCubeTwoSwitch(ourswitch, scale);
			break;
		case "ScaleLeftSide":
			m_autonomousCommand = new CG_scaleFromLeftScoreOnSide(ourswitch, scale);
			break;
		case "ScaleRightSide":
			m_autonomousCommand = new CG_scaleFromRightScoreOnSide(ourswitch, scale);
			break;
		case "LiftUpperPosition":
			m_autonomousCommand = new CG_scorePosition();
			break;
		case "CenterSwitchLeft":
			m_autonomousCommand = new CG_centerSwitchLeft(ourswitch, scale);
			break;
		case "CenterSwitchRight":
			m_autonomousCommand = new CG_centerSwitchRight(ourswitch, scale);
			break;
		case "PartnerScaleOnOurLeft":
			m_autonomousCommand = new CG_autoPartnerScaleOnOurLeft(ourswitch, scale);
			break;
		case "PartnerScaleOnOurRight":
			m_autonomousCommand = new CG_autoPartnerScaleOnOurRight(ourswitch, scale);
			break;
		case "FavorOpponentsScale":
			m_autonomousCommand = new CG_autoFavorOpponentsScale(ourswitch, scale);
			break;
		case "FavorOurScaleSolo":
			m_autonomousCommand = new CG_autoFavorOurScaleSolo(ourswitch, scale);
			break;
		case "SwitchThenGoAround":
			m_autonomousCommand = new CG_centerSwitchThenGoAroundSwitch(ourswitch, scale);
			break;
		case "CG_beesMatch":
			m_autonomousCommand = new CG_beesMatch(ourswitch, scale);
			break;
		case "CG_scaleFromLeftMultiCubeFarRightScale":
			m_autonomousCommand = new CG_scaleFromLeftMultiCubeFarRightScale(ourswitch, scale);
			break;
		case "CG_pyramid":
			m_autonomousCommand = new CG_autoFavorOurScaleSoloFromPyramid(ourswitch, scale);
			break;
		default:
			m_autonomousCommand = new CG_autoDoNothing(ourswitch, scale);
			break;
			
			
		}

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}

		this.allInit();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		this.allPeriodic();
	}
	@Override
	public void teleopInit() {
		AutoCommandManager.getInstance().teleop();
		this.allInit();                                  // not used right now?
		lift.levelOfLift = 1;
		RobotMap.disabledAtEndOfAuto = true;
		Robot.bigBrother.holdAltControl();
		Robot.chassis.setBrakeMode(NeutralMode.Coast);
		Robot.trolley.maxSpeedUp = 1;

		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		this.allPeriodic();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		this.allPeriodic();
	}

	//////////////////////////////////////////////

	/**
	 * This method is called once during all modes when selected (disabled,
	 * auton, teleop and test)
	 */
	public void allInit() {

	}

	/**
	 * This method is called periodically during all modes (disabled, auton,
	 * teleop and test)
	 */
	public void allPeriodic() {

		Robot.led.initDefaultCommand();

		if (RobotMap.robot_AllPeriodicDebug) {
			SmartDashboard.putNumber("claw pressure", (int) (RobotMap.clawPressure.getValue() / 21.37));
			SmartDashboard.putData("Auto mode", autonchooser);
			SmartDashboard.putNumber("LeftEncoder", RobotMap.chassis_leftFront.getSelectedSensorPosition(0));
			SmartDashboard.putNumber("RightEncoder", RobotMap.chassis_rightFront.getSelectedSensorPosition(0));
			SmartDashboard.putNumber("LIFTStringPot - 43", RobotMap.lift_right.getSelectedSensorPosition(0));
			SmartDashboard.putNumber("lift Level", Robot.lift.levelOfLift);
			SmartDashboard.putNumber("TrolleyPosition - 454", RobotMap.trolley_right.getSelectedSensorPosition(0));
			SmartDashboard.putNumber("armEncoderPositionPWM - 2300ish", RobotMap.arm_right.getSensorCollection().getPulseWidthPosition());
			SmartDashboard.putNumber("armEncoderPosition - 2300ish", RobotMap.arm_right.getSelectedSensorPosition(0));
			SmartDashboard.putBoolean("Line Reader", RobotMap.lineReader.get());
			//SmartDashboard.putNumber("centerX", RobotMap.vision.getRevAngle());
			SmartDashboard.putBoolean("claw Pressure", RobotMap.clawPressureDash);
			SmartDashboard.putBoolean("Crate Sensor Red", RobotMap.crateSensorLeft.get());
			SmartDashboard.putBoolean("Crate Sensor Green", RobotMap.crateSensorRight.get());
			//SmartDashboard.putNumber("Navx Yaw", RobotMap.navx_gyro.getYaw());
		}
		//SmartDashboard.putString("intake Command", Robot.intake.getCurrentCommandName());
//		SmartDashboard.putBoolean("first intake", RobotMap.firstIntake);
		//SmartDashboard.putString("Pixy Command", Robot.pixy.getCurrentCommandName());
//		SmartDashboard.putNumber("Pixy Xx Value", Robot.pixy.xx);
		//SmartDashboard.putNumber("Pixy Yy Value", Robot.pixy.yy);
		SmartDashboard.putBoolean("brokenArm", RobotMap.brokenArm);
		
		//System.out.println(gyro.gyrofusionStatus.bIsValid);
	}

}
