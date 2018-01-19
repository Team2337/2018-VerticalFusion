package arm;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Extender: RETRACT - Moves the extender in
 * 
 * @category EXTENDER
 * @author Bruce
 */
public class extender_retract extends Command {
	public extender_retract() {
		requires(Robot.arm);
	}

	protected void initialize() {
		Robot.arm.retract();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
