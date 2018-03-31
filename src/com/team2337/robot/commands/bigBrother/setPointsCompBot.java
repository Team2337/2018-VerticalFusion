package com.team2337.robot.commands.bigBrother;

import com.team2337.robot.Robot;

public class setPointsCompBot {
	static int armForwardSoftLimit = Robot.arm.forwardSoftLimit;
	static int armReverseSoftLimit = Robot.arm.reverseSoftLimit;
	static int armForwardCarry = Robot.arm.forwardCarry;
	static int armClimbHook = Robot.arm.armClimbHook;
	static int armClimbMode = Robot.arm.centerPosition;
	static int armHookAdjLimit = Robot.arm.armHookAdjLimit;
	static int armCenterAdjLimit = Robot.arm.armCenterAdjLimit;
	
	static int liftLowPoint = Robot.lift.compLiftRestPoint;
	static int liftYellowNearBottom = Robot.lift.liftYellowNearBottom;	//the value between the top and bottom points of yellow
	static int liftYellowNearTop = Robot.lift.liftYellowNearTop;		//the value between the top and bottom points of yellow
	static int liftYellowHigh = Robot.lift.liftYellowHigh;
	static int liftRedNearBottom = Robot.lift.liftRedNearBottom;		//the value between the top and bottom points of red
	static int liftRedNearTop = Robot.lift.liftRedNearTop;				//the value between the top and bottom points of red
	static int liftRedHigh = Robot.lift.liftRedHigh;
	
	static int trolleyStart = Robot.trolley.trolleyStart;
	static int trolleyLowHold = Robot.trolley.trolleyLowHold;
	static int trolleyPlus215 = Robot.trolley.trolleyPlus215;
	static int trolleyPlus315 = Robot.trolley.trolleyPlus315;
	static int trolleyHookPos = Robot.trolley.trolleyHookPos;
	static int trolleyTop	  = Robot.trolley.trolleyTop;
	
	static int arm95Carry = (armForwardCarry-718);
	static int arm95Minus100 = arm95Carry - 100;
	static int arm95Minus200 = arm95Carry - 200;	//1882
	static int armAdjFlat = 600; //3320
	
	public static double points[][] = new double[][] {
		
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
		{trolleyStart,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,armAdjFlat,0,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyStart},
		{trolleyLowHold,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,armAdjFlat,0,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyLowHold},
		{trolleyLowHold,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,armAdjFlat,0,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyLowHold},
		{trolleyLowHold,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,armAdjFlat,0,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyLowHold},
		{trolleyLowHold,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,armAdjFlat,0,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyLowHold},
		{trolleyPlus215,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,armAdjFlat,0,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyPlus215},
		{trolleyPlus315,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,armAdjFlat,0,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyPlus315},
		{trolleyTop,liftLowPoint,liftLowPoint,liftLowPoint,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,armAdjFlat,0,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyTop},
		{trolleyTop,liftLowPoint,liftYellowNearBottom,liftRedNearBottom,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,armAdjFlat,0,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyTop},               
		{trolleyTop,liftLowPoint,liftYellowNearTop,liftRedNearTop,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,armAdjFlat,0,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyTop},               
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,armForwardCarry,armForwardSoftLimit,armReverseSoftLimit,50,50,liftLowPoint,0,armForwardCarry,armForwardCarry,trolleyTop}, 
		
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Carry,armForwardSoftLimit,armReverseSoftLimit,0,0,150,0,arm95Carry,armClimbMode,trolleyTop},
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Carry,armForwardSoftLimit,armReverseSoftLimit,0,0,200,0,arm95Carry,armClimbMode,trolleyTop},
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Carry,armForwardSoftLimit,armReverseSoftLimit,0,0,250,0,arm95Carry,armClimbMode,trolleyTop},
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,armCenterAdjLimit,armCenterAdjLimit,300,0,arm95Minus100,armClimbMode,trolleyTop},
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,armCenterAdjLimit,armCenterAdjLimit,350,0,arm95Minus100,armClimbMode,trolleyPlus315},
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,armCenterAdjLimit,armCenterAdjLimit,400,armHookAdjLimit,arm95Minus200,armClimbMode,trolleyPlus315},
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus100,armForwardSoftLimit,armReverseSoftLimit,armCenterAdjLimit,armCenterAdjLimit,450,armHookAdjLimit,arm95Minus200,armClimbMode,trolleyPlus315},
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus200,armForwardSoftLimit,armReverseSoftLimit,armCenterAdjLimit,armCenterAdjLimit,500,armHookAdjLimit,armClimbHook,armClimbMode,trolleyHookPos},
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus200,armForwardSoftLimit,armReverseSoftLimit,armCenterAdjLimit,armCenterAdjLimit,550,armHookAdjLimit,armClimbHook,armClimbMode,trolleyHookPos},
		{trolleyTop,liftLowPoint,liftYellowHigh,liftRedHigh,arm95Minus200,armForwardSoftLimit,armReverseSoftLimit,armCenterAdjLimit,armCenterAdjLimit,600,armHookAdjLimit,armClimbHook,armClimbMode,trolleyHookPos},
	};
		
}