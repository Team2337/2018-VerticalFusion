package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.Robot;

public class setPointsTestPickUpOnly {
	static int armForwardSoftLimit = Robot.arm.forwardSoftLimit;
	static int armReverseSoftLimit = Robot.arm.reverseSoftLimit;
	static int armForwardCarry = Robot.arm.forwardCarry;
	
	static int liftLowPoint = Robot.lift.practiceLiftRestPoint;
	static int liftYellowHigh = Robot.lift.liftYellowHigh;
	static int liftRedHigh = Robot.lift.liftRedHigh;
	
	static int arm95Carry = (armForwardCarry-718);
	static int arm95Minus100 = arm95Carry - 100;
	static int arm95Minus200 = arm95Carry - 200;
	
	public static double points[][] = new double[][] {
		
	//trolley set points
		/* 
		 * Columns
		 * 0: trolley set points
		 * 1: lift set points A
		 * 2: lift set points B
		 * 3: lift set points C
		 * 4: arm set points
		 * 5: arm forward soft limits
		 * 6: arm reverse soft limits
		 * 7: arm positive adjustment 
		 * 8: arm negative adjustment
		 */
		
		{110,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,475,0,},
		{128,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,475,0,},
		{198,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,475,0,},
		{268,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,475,0,},
		{338,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,475,0,},
		{408,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,475,0,},
		{478,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,475,0,},
		{545,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,475,0,},
		{545,liftLowPoint,126,225,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,475,0,},
		{545,liftLowPoint,207,405,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,475,0,},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,50,50,}, 
		
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Carry,armForwardSoftLimit,armReverseSoftLimit,0,0,},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Carry,armForwardSoftLimit,armReverseSoftLimit,0,0,},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Carry,armForwardSoftLimit,armReverseSoftLimit,0,0,},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,0,0,},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,0,0,},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,0,0,},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,0,0,},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus200,armForwardSoftLimit,armReverseSoftLimit,0,0,},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus200,armForwardSoftLimit,armReverseSoftLimit,0,0,},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus200,armForwardSoftLimit,armReverseSoftLimit,0,0,},
	};
}


