package com.team2337.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;
import com.team2337.robot.subsystems.Arm;

/**
 * arm: JOYSTICKCONTROL - Moves the arm based joystick
 * 
 * @category arm
 * @author - Bryce
 */
public class TEST_ONLY_arm_joystickControl extends Command {

	boolean setPointSet = false;
	double armPositionValue = 0;
	double armPositionEncoder;
	double stringPot;
	boolean isAtTop;
	
	private static final double deadband = 0.2;
	
	private static final int forwardBottomSL = 1;
	private static final int forwardTopSL = 968;
	private static final int reverseTopSL = 1080;
	private static final int reverseBottomSL = 2560;
	
	private static final double stringpotTopPos = 1.0;
	private static final double stringpotMidPos = 0.7;

	double armJoystickX;

	public TEST_ONLY_arm_joystickControl() {
		requires(Robot.arm);
	}

	protected void initialize() {
		setPointSet = false;
		Robot.arm.disable();
		Robot.arm.setSoftLimits(forwardTopSL, forwardBottomSL);
	}

	protected void execute() {
		armPositionEncoder = RobotMap.arm_right.getSelectedSensorPosition(0);

		SmartDashboard.putBoolean("armSetSetpoint", setPointSet);
		SmartDashboard.putNumber("PIDArmPosition", RobotMap.arm_right.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("selectedSensorPosition", armPositionEncoder);
		SmartDashboard.putNumber("ArmJoystickValue", armJoystickX);

		armJoystickX = -Robot.oi.operatorJoystick.getRawAxis(1);  //uses flightStick to Test

		stringPot = (RobotMap.lift_right.getSelectedSensorPosition(0));  /// absolute???? removed

		if ((armJoystickX > -deadband) && (armJoystickX < deadband)) {

			armJoystickX = 0;

			if (!setPointSet) {
				Robot.arm.enable();
				Robot.arm.setSetpoint(RobotMap.arm_right.getSelectedSensorPosition(0));

				SmartDashboard.putNumber("PIDSetPosition", Robot.arm.getPosition());

				setPointSet = true;
			}
		} else {
			Robot.arm.disable();
			setPointSet = false;
			
			SmartDashboard.putNumber("StringPotValueInArm", stringPot);
									
				if (stringPot > stringpotTopPos) {
					Robot.arm.setSoftLimits(reverseBottomSL, forwardBottomSL);
				} else if (stringPot > stringpotMidPos) {
					if (armPositionEncoder > reverseTopSL) {
						Robot.arm.setSoftLimits(reverseBottomSL, reverseTopSL);
					} else if (armPositionEncoder < forwardTopSL) {
						Robot.arm.setSoftLimits(forwardTopSL, forwardBottomSL);
					}
				}else if (stringPot < stringpotMidPos) {
					if (armPositionEncoder > reverseTopSL) {
						Robot.arm.setSoftLimits(reverseBottomSL, reverseTopSL);
					} else if (armPositionEncoder < forwardTopSL) {
						Robot.arm.setSoftLimits(forwardTopSL, forwardBottomSL);
					}
				}
			
				Robot.arm.moveArm(armJoystickX);

		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {
		this.end();
	}
}
