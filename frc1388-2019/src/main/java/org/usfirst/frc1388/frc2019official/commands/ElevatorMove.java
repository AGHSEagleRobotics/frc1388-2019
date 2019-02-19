package org.usfirst.frc1388.frc2019official.commands;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1388.frc2019official.Robot;
import org.usfirst.frc1388.frc2019official.UsbLogging;
import org.usfirst.frc1388.frc2019official.subsystems.Elevator;

/**
 *
 */
public class ElevatorMove extends Command {

	//private final Elevator elevator = Robot.elevator;
	private final double k_elevatorDeadband = 0.1; // deadband for the elevator, not tested
	
	private final Elevator elevator = Robot.elevator;

	public ElevatorMove() {
        requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
    	UsbLogging.info(">>> " + this.getClass().getSimpleName() + " started");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        // Tower control TODO: Double check controls & if they work with Xbox one.
        boolean drStartPressed = Robot.oi.getDriveController().getStartButtonPressed();
        boolean drBackPressed = Robot.oi.getDriveController().getBackButtonPressed();
        boolean opStartPressed = Robot.oi.getOpController().getStartButtonPressed();
        boolean opBackPressed = Robot.oi.getOpController().getBackButtonPressed();

        if( drStartPressed || opStartPressed ){
            Robot.elevator.stand();
        }
        if( drBackPressed || opBackPressed ){
            Robot.elevator.lean();
        }

		// TODO make on/off vs hold
		boolean override = Robot.oi.getOpController().getBackButton();

		double leftTrigger = Robot.oi.getOpController().getTriggerAxis(Hand.kLeft);

		double rightTrigger = Robot.oi.getOpController().getTriggerAxis(Hand.kRight);

		double elevatorSpeed = 0;

		if ( rightTrigger > 0 && leftTrigger > 0)  { // If both triggers pressed, elevatorSpeed = 0

			elevatorSpeed = 0;

		} else if (rightTrigger > 0) {
			
			elevatorSpeed = applyDeadband(rightTrigger, k_elevatorDeadband); 
			
		} else if (leftTrigger > 0) {
			
			elevatorSpeed = -applyDeadband(leftTrigger, k_elevatorDeadband); // negative because trigger joysticks both postive range
			
		}

		elevator.setMotor(elevatorSpeed, override);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		elevator.setMotor(0, false);
    	UsbLogging.info("<<< " + this.getClass().getSimpleName() + " ended");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
    	UsbLogging.info("<<< " + this.getClass().getSimpleName() + " interrupted");
		end();
	}

	private double applyDeadband(double value, double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
		} else {
			return 0.0;
		}
	}
}