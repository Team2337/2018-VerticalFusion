package com.team2337.robot.commands.auto;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.shifter.shifter_high;
import com.team2337.robot.commands.shifter.shifter_low;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_centerSwitch extends CommandGroup {

	public CG_centerSwitch() {
		//addSequential(new auto_driveToAngleWithTime(.5, 1.5, 28));
		
		//addSequential(new auto_driveToAngleWithTime(.5, 0.6, 0));
		
		
		//System.out.println(Robot.ourswitch);
		
		if (Robot.ourswitch.equals("L") || Robot.ourswitch.equals("l")) {
			
			/*addSequential(new auto_brakeModeOn());
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, 35, 40000, 50708, 0.04));
			addSequential(new shifter_high());
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 40000, 80000, 0.02));
			*/
		} else {
			addSequential(new shifter_low());
			addSequential(new shifter_high());
			addSequential(new shifter_low());
			addSequential(new shifter_high());
			addSequential(new shifter_low());
			addSequential(new shifter_high());
			addSequential(new shifter_low());
			addSequential(new shifter_high());
			addSequential(new shifter_low());
			addSequential(new shifter_high());
			addSequential(new shifter_low());
			addSequential(new shifter_high());
			addSequential(new shifter_low());
			addSequential(new shifter_high());
			addSequential(new shifter_low());
			addSequential(new shifter_high());
			addSequential(new shifter_low());
			addSequential(new shifter_high());
			addSequential(new shifter_low());
			/*
			 * addSequential(new auto_brakeModeOn());
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, -32, 40000, 50708, 0.04));
			addSequential(new shifter_high());
			addSequential(new auto_driveToAngleWithEncoder(.5, 3, 0, 40000, 80000, 0.02));
			 */
		}
		
	
	}
}
