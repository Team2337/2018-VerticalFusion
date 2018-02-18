package com.team2337.robot;

import com.team2337.robot.subsystems.Chassis;
import com.team2337.robot.subsystems.Claw;
import com.team2337.robot.subsystems.Climber;
import com.team2337.robot.subsystems.Ejector;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.team2337.fusion.led.Color;
import com.team2337.fusion.wrappers.command.auto.AutoCommandManager;
import com.team2337.robot.commands.DoNothing;
import com.team2337.robot.commands.auto.*;
import com.team2337.robot.subsystems.Arm;
import com.team2337.robot.subsystems.Intake;
import com.team2337.robot.subsystems.LED;
import com.team2337.robot.subsystems.Lifter;
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
	public static Ejector ejector;
	public static Arm arm;
	public static Intake intake;
	public static LED led;
	public static Lifter lifter;
	public static Shifter shifter;
	public static Climber climber;
	public static Claw claw;
	public static OI oi;
	public static char ourswitch, scale, oppswitch;

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
		lifter = new Lifter();
		intake = new Intake();
		ejector = new Ejector();
		arm = new Arm();
		climber = new Climber();
		shifter = new Shifter();
		led = new LED();
		claw = new Claw();

		// Include the Operator Interface
		oi = new OI();

		AutoCommandManager.getInstance().init();
		// Also include the Auton Chooser
		autonchooser.addDefault("Cross the Line", new DoNothing());
		autonchooser.addObject("Center Switch", new auto_centerSwitch());
		autonchooser.addObject("Do Nothing", new DoNothing());
		autonchooser.addObject("Right Switch", new DoNothing());
		autonchooser.addObject("Right Scale", new DoNothing());
		autonchooser.addObject("Right Switch Scale", new DoNothing());
		autonchooser.addObject("Right Scale Switch", new DoNothing());
		autonchooser.addObject("Right Switch No cross", new DoNothing());
		autonchooser.addObject("Right Scale No cross", new DoNothing());
		autonchooser.addObject("Make them cry with leds", new auto_ActionColorExample());
		SmartDashboard.putData("Auto mode", autonchooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		this.allInit();
		RobotMap.blinkin.setColor(Color.OFF);
		AutoCommandManager.getInstance().disable();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		this.allPeriodic();
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
		AutoCommandManager.getInstance().auton();
		this.allInit();
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
		this.allInit();
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
		
		
		SmartDashboard.putNumber("pot", RobotMap.lift_potentiometer.get());
		SmartDashboard.putNumber("SetPoint", Robot.lifter.getSetpoint());
		SmartDashboard.putBoolean("atSetPoint?", com.team2337.robot.commands.lifter.lifter_joystickControl.isAtTop);
		SmartDashboard.putNumber("potValue", com.team2337.robot.commands.lifter.lifter_joystickControl.potValue);
		SmartDashboard.putNumber("armAnglePosition", Arm.armAngle);
		SmartDashboard.putNumber("armEncoderPosition", RobotMap.arm_right.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("armValue", com.team2337.robot.commands.arm.arm_increaseAngle.armPosition);
		SmartDashboard.putNumber("rightFront", RobotMap.lift_rightFront.getMotorOutputPercent());
		SmartDashboard.putNumber("leftFront", RobotMap.lift_leftFront.getMotorOutputPercent());
		// SmartDashboard.putNumber("armPositionValue",
		// com.team2337.robot.commands.arm.arm_joystickControl.armPositionValue);

		SmartDashboard.putNumber("centerX", RobotMap.vision.getRevAngle());
		//
		System.out.print(RobotMap.vision.getRevAngle());
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
		
	}	
	
	
}
