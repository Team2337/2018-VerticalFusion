package com.team2337.robot;

import com.team2337.robot.subsystems.Chassis;
import com.team2337.robot.subsystems.Claw;
import com.team2337.robot.subsystems.Climber;
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
	public static Climber climber;
	public static Claw claw;
	public static BigBrother bigBrother;
	public static Lift lift;
	public static OI oi;
	public static Pigeon gyro;
	public static String ourswitch = "q";
	public static String scale = "q";
	public static String oppswitch = "q";

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
		String mac = Address.getInstance().getMAC();
		if (mac.equals("00-80-2F-17-89-85")) {
			System.out.println("TestBoard");
			isComp = false;
		} else if(mac.equals("00-80-2F-17-E5-D2")) {
			System.out.println("PracticeBot");
			isComp = false;
		} else {
			System.out.println("CompBot");
			isComp = true;
		}
		
		// Initialize all of the Robots Mappings
		RobotMap.init();
		// Also start the camera(s)
		RobotMap.startCamera();

		// Reference all of the subsystems

		chassis = new Chassis();
		trolley = new Trolley();
		intake = new Intake();
		arm = new Arm();
		climber = new Climber();
		shifter = new Shifter();
		led = new LED();
		claw = new Claw();
		lift = new Lift();
		bigBrother = new BigBrother();		
		gyro = new Pigeon();
		oi = new OI();

		AutoCommandManager.getInstance().init();
		AutoCommandManager.getInstance().setBlinkin(RobotMap.blinkin);

		// Also include the Auton Chooser
		Robot.gyro.resetPidgey();
		Robot.chassis.resetEncoders();
		// RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, 0);  //replaced by above method
		// RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, 0);

		//autonchooser.addObject("Cross the Line", "CrossLine");
		autonchooser.addDefault("Center Switch", "CenterSwitch");
		autonchooser.addObject("Center Switch then Scale", "CenterSwitchScale");
		autonchooser.addObject("Do Nothing", "DoNothing");
		autonchooser.addObject("Scale From Left", "ScaleLeft");
		autonchooser.addObject("Scale From Right", "ScaleRight");
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
		Pigeon.pidgey.setFusedHeading(0.0, 10);
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

		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();

		ourswitch = gameData.substring(0, 1);
		scale = gameData.substring(1, 2);
		oppswitch = gameData.substring(2, 3);

		String selected = autonchooser.getSelected();

		switch (selected) {
		case "CenterSwitch":
			m_autonomousCommand = new CG_centerSwitch(ourswitch, scale);
			break;
		case "CenterSwitchScale":
			m_autonomousCommand = new CG_centerSwitchThenScale(ourswitch, scale);
			break;			
		case "CrossLine":
			m_autonomousCommand = new CG_crossTheLine(ourswitch, scale);
			break;
		case "DoNothing":
			m_autonomousCommand = new CG_autoDoNothing(ourswitch, scale);
			break;
		case "ScaleLeft":
			m_autonomousCommand = new CG_scaleFromLeft(ourswitch, scale);
			break;
		case "ScaleRight":
			m_autonomousCommand = new CG_scaleFromRight(ourswitch, scale);
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
	//	new claw_CGClose();
		/*
		claw.give60Hugs();
		new auto_wait(0.5);
		claw.give30Hugs();
		claw.close();
		*/

		Robot.chassis.setBrakeMode(NeutralMode.Coast);

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
			SmartDashboard.putNumber("claw pressure", (RobotMap.clawPressure.getValue() / 21.37));
	
			SmartDashboard.putData("Auto mode", autonchooser);
			SmartDashboard.putNumber("LeftEncoder", RobotMap.chassis_leftFront.getSelectedSensorPosition(0));
			SmartDashboard.putNumber("RightEncoder", RobotMap.chassis_rightFront.getSelectedSensorPosition(0));
			SmartDashboard.putNumber("LIFTStringPot - 43", RobotMap.lift_right.getSelectedSensorPosition(0));
			SmartDashboard.putNumber("lift Level", Robot.lift.levelOfLift);
			SmartDashboard.putNumber("TrolleyPosition - 454", RobotMap.trolley_right.getSelectedSensorPosition(0));
			SmartDashboard.putNumber("armEncoderPositionPWM - 2300ish", RobotMap.arm_right.getSensorCollection().getPulseWidthPosition());
			SmartDashboard.putBoolean("Line Reader", RobotMap.lineReader.get());
			SmartDashboard.putNumber("centerX", RobotMap.vision.getRevAngle());
			SmartDashboard.putBoolean("intake?", RobotMap.firstIntake);
			SmartDashboard.putString("IntakeCommand", Robot.intake.getCurrentCommandName());
			SmartDashboard.putString("Claw Command", Robot.claw.getCurrentCommandName());
		}
	}

}
