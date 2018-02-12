package com.team2337.fusion.wrappers.command.auto;

import com.team2337.fusion.led.BlinkIn;
import com.team2337.fusion.led.Color;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * FusionManager - Manager for {@link FusionSubsystem}
 * @author Brendan
 *
 */
public class AutoCommandManager {
	
	private static AutoCommandManager instance;
	private double color;
	private BlinkIn led;
	/**
	 * Returns the {@link CommandManager}, creating it if one does not exist.
	 *
	 * @return the {@link CommandManager}
	 */
	public static synchronized AutoCommandManager getInstance() {
		if (instance == null) {
			instance = new AutoCommandManager();
		}
		return instance;
	}
	public void setBlinkin(BlinkIn led) {
		this.led = led;
	}
	public void toggle() {
		
		if (color == Color.BLUE) {
			color = Color.RED;
			SmartDashboard.putString("COLOR_COMMAND", "RED");
			led.setColor(color);
		} else {
			color = Color.BLUE;
			SmartDashboard.putString("COLOR_COMMAND", "BLUE");
			led.setColor(color);
		}
	}
}