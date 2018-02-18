package com.team2337.robot;

import com.team2337.robot.subsystems.Chassis;
import com.team2337.robot.subsystems.Claw;
import com.team2337.robot.subsystems.Climber;
import com.ctre.phoenix.ParamEnum;
import com.team2337.fusion.gyro.Pigeon;
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
	public static char scale, oppswitch;
	//public static char ourswitch, scale, oppswitch;

	Command m_autonomousCommand;
	SendableChooser<Command> autonchooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {

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
		bigBrother = new BigBrother();
		lift = new Lift();
		gyro = new Pigeon();
		oi = new OI();

		// Also include the Auton Chooser
		Robot.gyro.resetPidgey();
		RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, 0);
    	
    	 autonchooser.addDefault("Cross the Line", new CG_holdArm());
		 autonchooser.addObject("Center Switch", new auto_centerSwitch());
		 autonchooser.addObject("Do Nothing", new DoNothing());
		 autonchooser.addObject("Right Switch", new DoNothing());
		 autonchooser.addObject("Right Scale", new DoNothing());
		 autonchooser.addObject("Right Switch Scale", new DoNothing());
		 autonchooser.addObject("Right Scale Switch", new DoNothing());
		 autonchooser.addObject("Right Switch No cross", new DoNothing());
		 autonchooser.addObject("Right Scale No cross", new DoNothing());
		 autonchooser.addObject("Make them cry", new DoNothing());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Pigeon.pidgey.setFusedHeading(0.0, 10);
		this.allInit();
	}

	@Override
	public void disabledPeriodic() {
		SmartDashboard.putNumber("trolleyJoystickValue", Robot.oi.operatorJoystick.getRawAxis(2));
		Scheduler.getInstance().run();
		this.allPeriodic();
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		//TODO
		//ourswitch = gameData.charAt(0);
		//scale = gameData.charAt(1);
		//oppswitch = gameData.charAt(2);
		

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Robot.gyro.resetPidgey();
		RobotMap.chassis_leftFront.setSelectedSensorPosition(0, 0, 0);
		RobotMap.chassis_rightFront.setSelectedSensorPosition(0, 0, 0);
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		ourswitch = gameData.substring(0,1);
		scale = gameData.charAt(1);
		oppswitch = gameData.charAt(2);
		
		 autonchooser.addDefault("Cross the Line", new CG_holdArm());
		 autonchooser.addObject("Center Switch", new auto_centerSwitch());
		 autonchooser.addObject("Do Nothing", new DoNothing());
		 autonchooser.addObject("Right Switch", new DoNothing());
		 autonchooser.addObject("Right Scale", new DoNothing());
		 autonchooser.addObject("Right Switch Scale", new DoNothing());
		 autonchooser.addObject("Right Scale Switch", new DoNothing());
		 autonchooser.addObject("Right Switch No cross", new DoNothing());
		 autonchooser.addObject("Right Scale No cross", new DoNothing());
		 autonchooser.addObject("Make them cry", new DoNothing());

		m_autonomousCommand = autonchooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
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
		this.allInit();
		RobotMap.endOfAuto = true;
		claw.close();
		
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


		SmartDashboard.putNumber("centerX", RobotMap.vision.getRevAngle());

		//System.out.print(RobotMap.vision.getRevAngle());
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
	 * This method is called once during all modes when selected (disabled, auton, teleop and test)
	 */
	public void allInit() {
		
	}
	/**
	 * This method is called periodically during all modes (disabled, auton, teleop and test)
	 */
	public void allPeriodic() {
		SmartDashboard.putData("Auto mode", autonchooser);
		SmartDashboard.putData("autoArmCommand", bigBrother);
		//SmartDashboard.putData("arugnygfuynfgrt6gfsd", arm.getCurrentCommand());
		SmartDashboard.putString("getCommandtyg", bigBrother.getCurrentCommandName());
		SmartDashboard.putNumber("ArmPValue", RobotMap.arm_right.configGetParameter(ParamEnum.eProfileParamSlot_P, 0, 0));
		SmartDashboard.putNumber("LeftEncoder", RobotMap.chassis_leftFront.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("RightEncoder", RobotMap.chassis_rightFront.getSelectedSensorPosition(0));
		
	}	
	
	
}
