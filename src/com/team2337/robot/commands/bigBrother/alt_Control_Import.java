package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.OI;
import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Control the Lift-Trolley-Arm based on throttle/joystick input
 */
public class alt_Control_Import extends Command {

	double points[][] = Robot.bigBrother.points;

	double trolleySetPoint;
	double armSetPoint;
	double armAdjPos;
	double armAdjNeg;
	double trolleyAdj = 2; // TODO determne rightamount
	double armEncoder;

	double throttleToggle;
	double trolleyStick;
	double throttleStick;
	double throttleValue;

	/*
	 * Columns 0: trolley set points 1: lift set points A 2: lift set points B
	 * 3: lift set points C 4: arm set points 5: arm forward soft limits 6: arm
	 * reverse soft limits 7: arm positive adjustment 8: arm negative adjustment
	 */

	int trolleySetPoints = 0;
	int liftSetPointsA = 1;
	int liftSetPointsB = 2;
	int liftSetPointsC = 3;
	int armSetPoints = 4;
	int armForwardSoftLimits = 5;
	int armReverseSoftLimits = 6;
	int armPositiveAdj = 7;
	int armNegativeAdj = 8;
	
	int compTrolleyRestPoint = 61;
	int practiceTrolleyRestPoint = 61;

	boolean sameSide = true;

	public alt_Control_Import() {
		requires(Robot.bigBrother);
	}

	protected void initialize() {

	}

	protected void execute() {

		// liftPot = RobotMap.lift_right.getSelectedSensorPosition(0);

		// Read Joystick and Throttle Input
		trolleyStick = OI.operatorJoystick.getRawAxis(1);
		throttleStick = -OI.operatorJoystick.getRawAxis(2);
		throttleValue = (throttleStick * 10) + 10;

		throttleToggle = OI.operatorJoystick.getRawAxis(4);

		// Read values from array based on Throttle input
		trolleySetPoint = (points[(int) throttleValue][trolleySetPoints]);
		armSetPoint = ((double) points[(int) throttleValue][armSetPoints]);
		armAdjPos = (points[(int) throttleValue][armPositiveAdj]);
		armAdjNeg = (points[(int) throttleValue][armNegativeAdj]);

		// Trolley adjustment by joystick

		// Adjust trolley setpoint based on joystick input
		if (Math.abs(trolleyStick) > 0.1) {
			trolleySetPoint = trolleySetPoint + (trolleyStick * trolleyAdj);
		}

		// Arm adjustment by toggle

		// Adjust arm set point based on throttle toggle input. Different
		// adjustments
		// for positive and negative toggle positions.
		if ((throttleToggle) > 0.1) {
			armSetPoint = armSetPoint + (throttleToggle * armAdjPos);
		} else if (throttleToggle < -0.1) {
			armSetPoint = armSetPoint + (throttleToggle * armAdjNeg);
		}

		// Arm and Trolley set point override:

		// If arm needs to change sides:
		// Override trolley set point to the top position, and
		// override arm position to stay on same side until trolley gets to top.
		armEncoder = RobotMap.arm_right.getSelectedSensorPosition(0);

		if ((!Robot.arm.sameSide(armEncoder, armSetPoint))) {
			trolleySetPoint = points[10][trolleySetPoints];

			if (Robot.trolley.getPosition() < Robot.trolley.trolleyPassover) {
				if (armEncoder >= Robot.arm.centerPosition) {
					armSetPoint = points[10][armSetPoints];
				} 
				else if (armEncoder <= Robot.arm.centerPosition) {
					armSetPoint = points[11][armSetPoints];
				}
				sameSide = false;
			}
		} else
			sameSide = true;

		// SET ALL SETPOINTS

		// Disabled after Autonomous until throttleToggle activated.
		if (RobotMap.disabledAtEndOfAuto) {
			SmartDashboard.putBoolean("Disabled: endOfAuto - checking throttle toggle", RobotMap.disabledAtEndOfAuto);
			if (Math.abs(throttleToggle) > 0.9) {
				RobotMap.disabledAtEndOfAuto = false;
				SmartDashboard.putBoolean("Disabled: endOfAuto - checking throttle toggle", RobotMap.disabledAtEndOfAuto);
			}
		}

		if (!RobotMap.disabledAtEndOfAuto) { // Disable until throttleToggle activated (see above)									

			// Set Set points, suspend PID if at pickup position
			if ((throttleToggle > 0.9) && (throttleStick > 0.9) && (Robot.arm.armIsLevel())) {
				Robot.arm.stop();
				Robot.lift.stop();		//keeping lift in here for pickup position protection, unless it hops to much....
			} else {
				Robot.arm.setSetpoint(armSetPoint);
				Robot.lift.setSetpoint(points[(int) throttleValue][Robot.lift.levelOfLift]);
			}
			// Set trolley set point, suspend PID if at bottom position.
			if (RobotMap.trolley_right.getSelectedSensorPosition(0) < practiceTrolleyRestPoint && trolleySetPoint < practiceTrolleyRestPoint) {
				Robot.trolley.stop();
			} else {
				Robot.trolley.setSetpoint(trolleySetPoint);
			}

		}

		// Print Debug info to SmartDashboard if turned on in RobotMap.
		if (RobotMap.alt_ControlDebug) {
			SmartDashboard.putBoolean("endOFAuto - debug", RobotMap.disabledAtEndOfAuto);
			SmartDashboard.putBoolean("sameSide", Robot.arm.sameSide(armEncoder, armSetPoint));
			SmartDashboard.putNumber("trolleyStick", trolleyStick);
			SmartDashboard.putNumber("TrolleySetPoint", Robot.trolley.getSetpoint());
			SmartDashboard.putNumber("TrolleyPosition", RobotMap.trolley_right.getSelectedSensorPosition(0));
			SmartDashboard.putNumber("throttleValue", throttleValue);
			SmartDashboard.putNumber("ArmSetPosition", armSetPoint);
			// SmartDashboard.putNumber("trolleyOutputPercent",
			// RobotMap.trolley_right.getMotorOutputPercent());
			SmartDashboard.putNumber("trolleyOutputXXXXXXXX", RobotMap.trolley_right.getMotorOutputPercent());
			SmartDashboard.putBoolean("XXXsameSideXXX", sameSide);
			SmartDashboard.putNumber("LIFTSetPointXXXXXX", Robot.lift.getSetpoint());
			SmartDashboard.putNumber("liftArray SetPoint", points[(int) throttleValue][Robot.lift.levelOfLift]);

			// SmartDashboard.putNumber("TrolleyArrayValue", points[(int)
			// throttleValue][trolleySetPoints]);
			// this may be setting the array to the throttle, then getting to
			// the logic
			// above
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
