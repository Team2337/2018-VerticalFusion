package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.Robot;

public class setPointsCompBot {
	static int armForwardSoftLimit = Robot.arm.forwardSoftLimit;
	static int armReverseSoftLimit = Robot.arm.reverseSoftLimit;
	static int armForwardCarry = Robot.arm.forwardCarry;
	static int armClimbHook = Robot.arm.armClimbHook;
	static int armClimbMode = Robot.arm.centerPosition;
	
	static int liftLowPoint = Robot.lift.compLiftRestPoint;
	static int liftYellowHigh = Robot.lift.liftYellowHigh;
	static int liftRedHigh = Robot.lift.liftRedHigh;
	
	static int arm95Carry = (armForwardCarry-718);
	static int arm95Minus100 = arm95Carry - 100;
	static int arm95Minus200 = arm95Carry - 300;	//1882
	
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
		 * 9: lift set points climb
		 *10: arm climb adjustment
		 *11: arm Climb Hook - Used when grabbing hook 
		 *12: arm Climb Mode - Used when releasing hook
		 *13: trolley climb mode - 0-10 same, 11-20 inverted values of 0-10
		 */
		{60,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,450,0,liftLowPoint,0,armForwardCarry,armForwardCarry,60},
		{150,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,450,0,liftLowPoint,0,armForwardCarry,armForwardCarry,150},
		{150,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,450,0,liftLowPoint,0,armForwardCarry,armForwardCarry,150},
		{150,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,450,0,liftLowPoint,0,armForwardCarry,armForwardCarry,150},
		{150,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,450,0,liftLowPoint,0,armForwardCarry,armForwardCarry,150},
		{275,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,450,0,liftLowPoint,0,armForwardCarry,armForwardCarry,275},
		{375,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,450,0,liftLowPoint,0,armForwardCarry,armForwardCarry,375},
		{545,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,450,0,liftLowPoint,0,armForwardCarry,armForwardCarry,545},
		{545,liftLowPoint,126,225,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,450,0,liftLowPoint,0,armForwardCarry,armForwardCarry,545},               
		{545,liftLowPoint,207,405,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,450,0,liftLowPoint,0,armForwardCarry,armForwardCarry,545},               
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,50,50,liftLowPoint,0,armForwardCarry,armForwardCarry,545}, 
		
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Carry,armForwardSoftLimit,armReverseSoftLimit,0,0,150,0,arm95Carry,armClimbMode,545},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Carry,armForwardSoftLimit,armReverseSoftLimit,0,0,200,0,arm95Carry,armClimbMode,545},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Carry,armForwardSoftLimit,armReverseSoftLimit,0,0,250,0,arm95Carry,armClimbMode,545},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,200,200,300,0,arm95Minus100,armClimbMode,545},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,200,200,350,0,arm95Minus100,armClimbMode,375},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,200,200,400,300,arm95Minus200,armClimbMode,275},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,200,200,450,300,arm95Minus200,armClimbMode,150},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus200,armForwardSoftLimit,armReverseSoftLimit,200,200,500,300,armClimbHook,armClimbMode,150},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus200,armForwardSoftLimit,armReverseSoftLimit,200,200,550,300,armClimbHook,armClimbMode,150},
		{545,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus200,armForwardSoftLimit,armReverseSoftLimit,200,200,600,300,armClimbHook,armClimbMode,60},
	};
		
}