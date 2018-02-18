package com.team2337.fusion.wrappers.command.action;

import com.team2337.fusion.wrappers.command.auto.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Action extends CommandGroup {
	
	public boolean color;

	public void addWait(double time, Object subsystem) {
		super.addSequential(new Wait(time, subsystem), (time + 1));
	}	
}