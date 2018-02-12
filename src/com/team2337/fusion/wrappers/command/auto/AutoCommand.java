package com.team2337.fusion.wrappers.command.auto;

import com.team2337.robot.Robot;
import com.team2337.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public abstract class AutoCommand extends Command {
    public AutoCommand() {}
    void initilize() {
    	System.out.println("ijhfuidslhvfdsyuijgdkh7ifydsvkfdsvhydfsyfdsghfds");
    	AutoCommandManager.getInstance().toggle();
    	this.initialize();
    }
}
