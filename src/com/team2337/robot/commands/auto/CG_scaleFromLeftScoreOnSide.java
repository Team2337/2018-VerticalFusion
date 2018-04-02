package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.claw.claw_CGOpen;
import com.team2337.robot.commands.claw.claw_open;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_scaleFromLeftScoreOnSide extends CommandGroup {

	String ourSwitch, scale;
	public CG_scaleFromLeftScoreOnSide(String ourSwitch, String scale) {
		this.ourSwitch = ourSwitch;
		this.scale = scale;		
		
		// Score Scale, get cube, Score Scale, get cube,  Score Scale	//LLL AND RLR
				if  (scale.equals("L")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					addSequential (new auto_driveToAngleWithEncoder(.7,10,0,80000,80000,.13));
					addParallel (new auto_moveUpperPosition(2127, 500));
					addSequential (new auto_driveToAngleWithEncoder(.6,10,0,140000,140000,.13));
					addParallel (new auto_moveUpperPosition(2300, 500));
					addSequential (new auto_driveToAngleWithEncoderandLine(.4,0.5,0,160000,160000,.13)); 
					addSequential (new auto_brakeModeOn());
					addSequential (new auto_moveUpperPosition(2300, 500));
					addSequential (new auto_wait(1));
					addSequential (new auto_LiftUp(580)); //600 practice
					
					addSequential(new shifter_low());
					addSequential (new auto_gyroMMTurn(-90, 1));
					addParallel (new auto_driveForwardWithTime(0.5,0.3));
					addSequential (new auto_moveUpperPosition(1850, 500));
					addSequential (new auto_wait(0.5));
					addSequential(new auto_intakeOut(0.8,2));
					addSequential (new auto_clawOpen());
					addSequential (new auto_moveUpperPosition(2800, 500));
					addSequential (new auto_wait(1));
					addSequential (new auto_LiftDown(60, 80)); //100,120 practice
					addSequential (new auto_moveUpperPosition(2800, 80));
					
					addSequential(new auto_resetEncoder());
					addSequential(new auto_clawOpen());
					addSequential (new auto_driveToAngleWithEncoder(.5,10,15,18000,18000,.2));
					addSequential(new auto_resetEncoder());
					addSequential(new shifter_high());
					addParallel (new auto_moveUpperPosition(3300, 80));
					addSequential (new auto_driveToAngleWithEncoder(-.6,10,35,18000,18000,.08));

					addSequential(new auto_wait(0.8));
					addParallel(new auto_intake_in(1, 3));
					addSequential(new auto_wait(1));
					addSequential(new auto_clawClose());
					addSequential(new auto_claw60());
					addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 9000, 9000, 0.04, true));
					addParallel (new auto_moveUpperPosition(2800, 80));
					
					
				//Switch is on our side scale is not LRL
					//Score in switch from side and try to grab cube
				} else if (ourSwitch.equals("L")  && scale.equals("R")) {
					addParallel(new auto_bigBrother_DoNothing());
					addSequential(new auto_holdUpperPosition(0.1));
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					addSequential (new auto_driveToAngleWithEncoder(.5, 4, 0, 93000, 93000, 0.04));// speed was .9 
					addSequential (new auto_brakeModeOn());
					addSequential (new auto_gyroMMTurn(-80, 1));
					addSequential (new auto_driveForwardWithTime(.5,0.5)); //.5,2
					addSequential (new auto_moveUpperPosition(1800,60));  //-250,60  ///Score into Switch position
					addSequential (new auto_wait(0.3));
					addSequential (new auto_resetEncoder());
					addSequential (new auto_intakeOut(.8,1.5));
					addSequential (new commonCG_armToPickupFromFront());
					addSequential (new auto_driveToAngleWithEncoder(-.5, 4, -90, 12000, 12000, 0.04));
					
				//Switch is NOT on our side scale is NOT RLR
					//Cross the line
				} else if (ourSwitch.equals("R")  && scale.equals("R")) {
					addSequential(new CG_crossTheLine());
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
					
				} else {
					addSequential(new CG_crossTheLine());
					//STOP  DO NOT CHANGE THE ABOVE OR PUT ANY CODE BEFORE THESE LINES YOU WILL SEND THE TROLLEY FLYING!!!!!
				}
		//Switch and scale are NOT on our side  LLL
		
		
	
	}
}
