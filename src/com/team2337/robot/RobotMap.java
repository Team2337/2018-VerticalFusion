/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2337.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2337.fusion.drive.*;


import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static Solenoid ejector_push;
	public static NerdyDrive drive;
	public static void init () {
		ejector_push = new Solenoid(0,1);
		
		
		TalonSRX chassis_leftFront = new TalonSRX(13);
		TalonSRX chassis_leftMid = new TalonSRX(14);
		TalonSRX chassis_leftRear = new TalonSRX(15);
		
		chassis_leftFront.setInverted(true);
		chassis_leftMid.follow(chassis_leftFront);
		chassis_leftMid.setInverted(true);
		chassis_leftRear.follow(chassis_leftFront);
		chassis_leftRear.setInverted(true);
		
		TalonSRX chassis_rightFront = new TalonSRX(0);
		TalonSRX chassis_rightMid = new TalonSRX(1);
		TalonSRX chassis_rightRear = new TalonSRX(2);
		

		chassis_rightMid.follow(chassis_rightFront);
		chassis_rightRear.follow(chassis_rightFront);

		drive = new NerdyDrive(chassis_leftFront, chassis_rightFront);
		drive.setSensitivity(0.5);
	}
}
