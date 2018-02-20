package com.team2337.fusion.wrappers.commands;

import com.team2337.fusion.wrappers.auto.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Action extends CommandGroup {
	
	public boolean color;

	public void addWait(double time) {
		super.addSequential(new Wait(time), (time + 1));
	}	
}