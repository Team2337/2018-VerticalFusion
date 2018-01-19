package arm;

import com.team2337.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Does nothing. Placeholder/filler command for buttons or other triggers.
 */
public class extender_doNothing extends Command {
	
    public extender_doNothing() {
    	requires(Robot.arm);
    }

    protected void initialize() {}
    protected void execute() {}
    protected boolean isFinished() { return true;}
    protected void end() {}
    protected void interrupted() {this.end();}
}
