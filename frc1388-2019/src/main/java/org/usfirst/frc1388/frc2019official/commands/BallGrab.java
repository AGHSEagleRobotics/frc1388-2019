// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1388.frc2019official.commands;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1388.frc2019official.Robot;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class BallGrab extends Command {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR

    private boolean ejectorEnabled = false;
    private Timer ejectorTimer;
    private boolean drTriggersPressed;
    private boolean opTriggersPressed;

    public BallGrab() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.ballGrabber); 
        ejectorEnabled = false;     
        ejectorTimer = new Timer();
        drTriggersPressed = false;
        opTriggersPressed = false;
    }

    public void ballGrab( DoubleSolenoid controlSolenoid ){        
        controlSolenoid.set(DoubleSolenoid.Value.kForward ); // pushes the actuator on the effector forward to allow for grabbing
        // get a signal and or after a delay
        controlSolenoid.set(DoubleSolenoid.Value.kReverse ); // contract the jaws to hold the ball
    }
    public void ballRelease( DoubleSolenoid controlSolenoid ){        
        controlSolenoid.set( DoubleSolenoid.Value.kForward );
    }
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        
        System.out.println("ZZZZZZZ Timer value = "+ejectorTimer.get());    
        // Read Triggers
        double drRightTrigger = Robot.oi.getDriveController().getTriggerAxis(Hand.kRight);
        double opRightTrigger = Robot.oi.getOpController().getTriggerAxis(Hand.kRight);
        double drLeftTrigger = Robot.oi.getDriveController().getTriggerAxis(Hand.kLeft);
        double opLeftTrigger = Robot.oi.getOpController().getTriggerAxis(Hand.kLeft);
        drTriggersPressed = ( drRightTrigger > 0 && drLeftTrigger > 0 );
        opTriggersPressed = ( opRightTrigger > 0 && opLeftTrigger > 0 );

        // Grab (close grabber) when Right trigger pressed (eiter controller)
        if(drRightTrigger > 0 || opRightTrigger > 0) {
            // Grab (close Grabber)
            Robot.ballGrabber.grab();
        }

        // Release (open grabber) when Left trigger pressed (either controller)		
        if( drLeftTrigger > 0 || opLeftTrigger > 0 ) {
            System.out.println("!ejectorEnabled and Left trigger pressed");
            Robot.ballGrabber.release();
        }
        
        // Eject Ball (open grabber and extend ejector) when both triggers pressed (either controller) 
        if( drTriggersPressed  || opTriggersPressed ){

        // TODO: put this in eject
            Robot.ballGrabber.release();
            Robot.ballGrabber.eject();
            // set an ejector enabled flag
            ejectorEnabled = true;
            // start a timer
            ejectorTimer.start();
        }

        // Retract the ejector afater two seconds and both triggers are released (either controller)
        if(ejectorEnabled && ejectorTimer.hasPeriodPassed(2)) {
            if( !drTriggersPressed && !opTriggersPressed ){
                System.out.println("ejectorEnabled and isTimedOut");
                // Retract the ejector
                Robot.ballGrabber.retract();
                // reset the ejector enabled flag
                ejectorEnabled = false;
                // stop the timer
                ejectorTimer.stop();
            }
        }
}

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
