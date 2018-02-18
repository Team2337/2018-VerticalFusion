package com.team2337.fusion.wrappers.command.action;

import com.team2337.fusion.led.BlinkIn;
import com.team2337.fusion.led.Color;
import com.team2337.fusion.wrappers.command.auto.Wait;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionColor extends CommandGroup {

	public BlinkIn blinkin;
	public boolean color;

	public void setBlinkIn(BlinkIn blinink) {
		this.blinkin = blinink;
	}
	public void newParallel(Command command, double timeout) {
		this.strobeColor();
		System.out.print("PARALLEL");
		super.addParallel(command, timeout);
	}
	
	public void newSequential(Command command, double timeout) {
		this.toggleColor();
		System.out.print("SEQUENTIAL");
		super.addSequential(command, timeout);
	}

	public void addWait(double time, Object subsystem) {
		super.addSequential(new Wait(time, subsystem), (time + 1));
	}
	
	
	public void toggleColor() {
		if (color == true) {
			SmartDashboard.putString("COLOR_CMD", "Green");
			blinkin.setColor(Color.GREEN);
			color = false;
		} else {
			SmartDashboard.putString("COLOR_CMD", "YELLOW");
			blinkin.setColor(Color.YELLOW);
			color = true;
		}
	}
	public void strobeColor() {
		if (blinkin.getColor() == Color.GREEN) {
			blinkin.setColor(Color.STROBE_GREEN);
		}
		if (blinkin.getColor() == Color.YELLOW) {
			blinkin.setColor(Color.STROBE_YELLOW);
		}
	}

}
