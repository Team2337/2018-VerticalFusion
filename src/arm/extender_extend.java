package arm;

import edu.wpi.first.wpilibj.command.Command;

import com.team2337.robot.Robot;

/**
 * Extender: EXTEND - Moves the extender out
 * 
 * @category EXTENDER
 * @author Bryce
 */
public class extender_extend extends Command {
	public extender_extend() {
		requires(Robot.arm);
	}

	protected void initialize() {
		Robot.arm.extend();
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
