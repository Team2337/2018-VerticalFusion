package com.team2337.robot.commands.bigBrother;

public class setPoints {
	
	public static double points[][] = new double[][] {
		
	//trolley set points
		/* 
		 * Columns
		 * 0: trolley set points
		 * 1: lift set points A
		 * 2: lift set points B
		 * 3: lift set points C
		 * 4: arm set points
		 * 5: trolley forward soft limits                           //not needed?   set in subsystem???
		 * 6: trolley reverse soft limits                          //not needed?   set in subsystem???
		 * 7: arm forward soft limits
		 * 8: arm reverse soft limits
		 * 9: trolley positive adjustment                          //not needed ??same positive and negative??  just set above as fixed?? in command??
		 *10: trolley negative adjustment                         //not needed ??same positive and negative??  just set above as fixed?? in command??
		 *11: arm positive adjustment 
		 *12: arm negative adjustment
		 */
		
		{-60 ,0,0,0,0   ,530 ,0  ,968,0   ,5   ,0 ,0  ,0},
		{-98 ,0,0,0,0   ,530 ,0  ,968,0   ,5   ,-5,0  ,0},
		{-147,0,0,0,968 ,530,0  ,968 ,0   ,10,-10,0,0},
		{-196,0,0,0,968 ,530,0  ,968 ,0   ,10,-10,0,0},
		{-245,0,0,0,968 ,530,0  ,968 ,0   ,10,-10,0,0},
		{-294,0,0,0,968 ,530,0  ,968 ,0   ,10,-10,0,0},
		{-343,0,0,0,968 ,530,0  ,968 ,0   ,10,-10,0,0},
		{-392,0,0,0,968 ,530,0  ,968 ,0   ,10,-10,0,0},
		{-441,0,0,0,968 ,530,0  ,968 ,0   ,10,-10,0,0},
		{-490,0,0,0,968 ,530,0  ,968 ,0   ,10,-10,0,0},
		{-530,0,0,0,1080,530,0  ,2503,1080,10,-10,5,-5},
		{-530,0,0,0,1195,530,0  ,2503,1080,10,-10,5,-5},
		{-530,0,0,0,1320,530,0  ,2503,1080,10,-10,5,-5},
		{-530,0,0,0,0   ,530,0  ,2503,1080,10,-10,5,-5},
		{-530,0,0,0,0   ,530,0  ,2503,1080,10,-10,5,-5},
		{-530,0,0,0,0   ,530,0  ,2503,1080,10,-10,5,-5},
		{-530,0,0,0,0   ,530,0  ,2503,1080,10,-10,5,-5},
		{-530,0,0,0,0   ,530,0  ,2503,1080,10,-10,5,-5},
		{-530,0,0,0,0   ,530,0  ,2503,1080,5 ,-5 ,5,-5},
		{-530,0,0,0,0   ,530,0  ,2503,1080,5 ,-5 ,5,-5},
		{-530,0,0,0,1365,530,0  ,2503,1080,5 ,-5 ,5,-5},


	};
}