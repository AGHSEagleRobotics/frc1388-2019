// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1388.frc2019official;

import org.usfirst.frc1388.frc2019official.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Thread;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public XboxController driveController = new XboxController(1);
    public XboxController opController = new XboxController(2);

    /*
     * time of single rumble in milliseconds
     */
    public long rumble_pulse_time = 100;
    public double rumble_strength = 1.0;
    private long rumble_spin_down_time = 200;

    /**
     * Driver stick positions
     */
    public double driveLeftStickY = 0.0;
    public double driveRightStickX = 0.0;

    

    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    public OI() {

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Drive", new Drive());
        SmartDashboard.putData("ElevatorMove", new ElevatorMove());
        SmartDashboard.putData("Manipulate", new Manipulate());
        SmartDashboard.putData("Climb", new Climb());
    }

    /**
     * Set rumble for XBoxController.
     * NOTE: Internal implementation to wrap WPILib API
     *
     * @param controller Reference to controller
     * @param sides Array of sides to rumble
     * @param strength Intensity of rumble [0,1]
     */
    private void rumble( XboxController controller, RumbleType[] sides, double strength ) {
        for ( RumbleType s : sides )
            controller.setRumble( s, strength );
    }

    /**
     * Pulses the rumble on controller side, num times.
     * Must be called from a context that does not loop continuously.
     * Use a flag to determine if a button has been released since the last check
     * NOTE: Internal implementation
     *
     * @param controller Reference to controller to pulse
     * @param sides Array of sides to rumble
     * @param num The number of equally spaced pulses
     */
    private void rumblePulse( XboxController controller, RumbleType[] sides, int num ) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask()
        {
            private void task_wait( long wait_time_ms )
            {
                try {
                    Thread.sleep( wait_time_ms );
                }
                catch ( Exception e )
                {
                    // Active wait
                    // Start the clock
                    long rumble_start_time = System.nanoTime();

                    // Wait for wait_time_ms in nanoseconds
                    while ( System.nanoTime() - rumble_start_time < wait_time_ms * 1000000 )
                    { /* wait */ }
                }
            }

            /*
             * This method is called automatically by Timer.schedule()
             */
            @Override
            public void run()
            {
                // Grab num from outer scope
                int n = num;

                while ( n > 0 )
                {
                    // Turn on the rumble
                    rumble( controller, sides, 1 );

                    // Wait for rumble_pulse_time
                    task_wait( rumble_pulse_time );

                    // Turn off the rumble
                    rumble( controller, sides, 0 );

                    // If this is the last pulse, do not pause for next pulse
                    if ( n <= 1 )
                        break;

                    // Allow motor to spin down
                    task_wait( rumble_spin_down_time );

                    n--;
                }

                // Destroy the thread
                timer.cancel();
                timer.purge();
            }
        };

        // Start the pulses immediately
        timer.schedule( task, 0 );
    }

    /**
     * Pulses the rumble on both sides of controller, num times.
     * Must be called from a context that does not loop continuously.
     * Use a flag to determine if a button has been released since the last check
     *
     * @param controller Reference to controller to pulse
     * @param num The number of equally spaced pulses
     */
    public void rumblePulse( XboxController controller, int num ) {
        rumblePulse( controller, RumbleType.values(), num );
    }

    /**
     * Pulses the rumble on one side of controller, num times.
     * Must be called from a context that does not loop continuously.
     * Use a flag to determine if a button has been released since the last check
     *
     * @param controller Reference to controller to pulse
     * @param side The side to rumble
     * @param num The number of equally spaced pulses
     */
    public void rumblePulse( XboxController controller, RumbleType side, int num ) {
        rumblePulse( controller, new RumbleType[] { side }, num );
    }

    /**
     * Rumble on for both sides of controller.
     * Uses member rumble_strength for intensity
     *
     * @param controller Reference to controller
     */
    public void rumbleOn( XboxController controller ) {
        rumble( controller, RumbleType.values(), rumble_strength );
    }

    /**
     * Rumble on for one side of controller.
     * Uses member rumble_strength for intensity
     *
     * @param controller Reference to controller
     * @param side The side to rumble
     */
    public void rumbleOn( XboxController controller, RumbleType side ) {
        rumble( controller, new RumbleType[] { side }, rumble_strength );
    }

    /**
     * Rumble off for both sides of controller.
     *
     * @param controller Reference to controller
     */
    public void rumbleOff( XboxController controller ) {
        rumble( controller, RumbleType.values(), 0 );
    }

    /**
     * Rumble off for one side of controller.
     *
     * @param controller Reference to controller
     * @param side The side to rumble
     */
    public void rumbleOff( XboxController controller, RumbleType side ) {
        rumble( controller, new RumbleType[] { side }, 0 );
    }

    public XboxController getDriveController() {
        return driveController;
    }

    public XboxController getOpController(){
        return opController;
    }

    /*******************************************************************************************************
     *                                            D-pad
     *******************************************************************************************************/

    /*
     * Does not check whether a direction has been pressed since last call.
     * This means you will need flags to determine the status of the mechanism being controlled.
     * i.e. Don't use these to control a mechanism. Use them to control the flag for the mechanism
     */

    /**
     * D-Pad Up is pressed.
     * @return true if driver or operator has D-Pad Up currently pressed
     */
    public boolean povUpPressed() {
        return driveController.getPOV() == 0 || opController.getPOV() == 0;
    }

    /**
     * D-Pad Up-Right is pressed.
     * @return true if driver or operator has D-Pad Up-Right currently pressed
     */
    public boolean povUpRightPressed() {
        return driveController.getPOV() == 45 || opController.getPOV() == 45;
    }

    /**
     * D-Pad Right is pressed.
     * @return true if driver or operator has D-Pad Right currently pressed
     */
    public boolean povRightPressed() {
        return driveController.getPOV() == 90 || opController.getPOV() == 90;
    }

    /**
     * D-Pad Down-Right is pressed.
     * @return true if driver or operator has D-Pad Down-Right currently pressed
     */
    public boolean povDownRightPressed() {
        return driveController.getPOV() == 135 || opController.getPOV() == 135;
    }

    /**
     * D-Pad Down is pressed.
     * @return true if driver or operator has D-Pad Down currently pressed
     */
    public boolean povDownPressed() {
        return driveController.getPOV() == 180 || opController.getPOV() == 180;
    }

    /**
     * D-Pad Down-Left is pressed.
     * @return true if driver or operator has D-Pad Down-Left currently pressed
     */
    public boolean povDownLeftPressed() {
        return driveController.getPOV() == 225 || opController.getPOV() == 225;
    }

    /**
     * D-Pad Left is pressed.
     * @return true if driver or operator has D-Pad Left currently pressed
     */
    public boolean povLeftPressed() {
        return driveController.getPOV() == 270 || opController.getPOV() == 270;
    }

    /**
     * D-Pad Up-Left is pressed.
     * @return true if driver or operator has D-Pad Up-Left currently pressed
     */
    public boolean povUpLeftPressed() {
        return driveController.getPOV() == 315 || opController.getPOV() == 315;
    }

    /**
     * D-Pad Up and D-Pad Down are pressed, one on each controller.
     * @return
     */
    public boolean povVerticalOppositePressed() {
        return ( driveController.getPOV() == 0 && opController.getPOV() == 180 ) ||
               ( driveController.getPOV() == 180 && opController.getPOV() == 0 );
    }

    /*******************************************************************************************************
     *                                            Buttons
     *******************************************************************************************************/

    /**
     * Button A has been pressed since last check
     * @return true if driver or operator has pressed Button A
     */
    public boolean buttonAPressed() {
        return driveController.getAButtonPressed() || opController.getAButtonPressed();
    }

     /**
     * Button B has been pressed since last check
     * @return true if driver or operator has pressed Button B
     */
    public boolean buttonBPressed() {
        return driveController.getBButtonPressed() || opController.getBButtonPressed();
    }

     /**
     * Button X has been pressed since last check
     * @return true if driver or operator has pressed Button X
     */
    public boolean buttonXPressed() {
        return driveController.getXButtonPressed() || opController.getXButtonPressed();
    }

     /**
     * Button Y has been pressed since last check
     * @return true if driver or operator has pressed Button Y
     */
    public boolean buttonYPressed() {
        return driveController.getYButtonPressed() || opController.getYButtonPressed();
    }

    /*******************************************************************************************************
     *                                            Bumpers
     *******************************************************************************************************/

    /**
     * Left Bumper has been pressed since last check
     * @return true if driver or operator has pressed left bumper
     */
    public boolean leftBumperPressed() {
        return driveController.getBumperPressed( Hand.kLeft ) || opController.getBumperPressed( Hand.kLeft );
    }

    /**
     * Right Bumper has been pressed since last check
     * @return true if driver or operator has pressed right bumper
     */
    public boolean rightBumperPressed() {
        return driveController.getBumperPressed( Hand.kRight ) || opController.getBumperPressed( Hand.kRight );
    }

    /*******************************************************************************************************
     *                                            Triggers
     *******************************************************************************************************/

    /*
     * Does not check whether a trigger has been pressed since last call.
     * This means you will need flags to determine the status of the mechanism being controlled.
     * i.e. Don't use these to control a mechanism. Use them to control the flag for the mechanism
     */

    /**
     * Left Trigger is pressed.
     * @return true if driver or operator is pressing left trigger
     */
    public boolean leftTriggerPressed() {
        return driveController.getTriggerAxis( Hand.kLeft ) > 0 || opController.getTriggerAxis( Hand.kLeft ) > 0;
    }

    /**
     * Right Trigger is pressed.
     * @return true if driver or operator is pressing right trigger
     */
    public boolean rightTriggerPressed() {
        return driveController.getTriggerAxis( Hand.kRight ) > 0 || opController.getTriggerAxis( Hand.kRight ) > 0;
    }

    public boolean oppositeTriggersPressed() {
        return ( driveController.getTriggerAxis( Hand.kLeft ) > 0 && opController.getTriggerAxis( Hand.kRight ) > 0 ) ||
               ( driveController.getTriggerAxis( Hand.kRight ) > 0 && opController.getTriggerAxis( Hand.kLeft ) > 0 );
    }
}

