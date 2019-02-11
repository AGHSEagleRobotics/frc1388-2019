/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1388.frc2019official.Robot;
import org.usfirst.frc1388.frc2019official.UsbLogging;

import edu.wpi.first.wpilibj.Timer;

public class Manipulate extends Command {

  // ballgrab variables
  private boolean ejectorEnabled = false;
  private Timer ejectorTimer;
  private boolean drBothTriggersPressed;
  private boolean opBothTriggersPressed;
  private boolean bothTriggersPressed;
  private boolean leftTriggerPressed;
  private boolean rightTriggerPressed;
  private boolean ejectorEnabledAndTimerExpired;

  // pancake variables
  private boolean dPadUp;
  private boolean dPadDown;

  public Manipulate() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    requires(Robot.manipulator);
    // ballgrab variables
    ejectorEnabled = false;
    ejectorTimer = new Timer();
    drBothTriggersPressed = false;
    opBothTriggersPressed = false;
    bothTriggersPressed = false;
    leftTriggerPressed = false;
    rightTriggerPressed = false;
    ejectorEnabledAndTimerExpired = false;

    // pancake variables
    dPadUp = false;
    dPadDown = false;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // ballgrab execute
    // Read Triggers
    double drRightTrigger = Robot.oi.getDriveController().getTriggerAxis(Hand.kRight);
    double opRightTrigger = Robot.oi.getOpController().getTriggerAxis(Hand.kRight);
    double drLeftTrigger = Robot.oi.getDriveController().getTriggerAxis(Hand.kLeft);
    double opLeftTrigger = Robot.oi.getOpController().getTriggerAxis(Hand.kLeft);
    boolean opRightBumper = Robot.oi.getOpController().getBumper(Hand.kRight);
    // Assign values
    drBothTriggersPressed = (drRightTrigger > 0 && drLeftTrigger > 0);
    opBothTriggersPressed = (opRightTrigger > 0 && opLeftTrigger > 0);
    bothTriggersPressed = (drBothTriggersPressed || opBothTriggersPressed);
    leftTriggerPressed = (drLeftTrigger > 0 || opLeftTrigger > 0);
    rightTriggerPressed = (drRightTrigger > 0 || opRightTrigger > 0);
    ejectorEnabledAndTimerExpired = (ejectorEnabled && ejectorTimer.hasPeriodPassed(2));

    // Grab (close grabber) when Right trigger pressed (either controller)
    if (rightTriggerPressed && !bothTriggersPressed && !ejectorEnabled) {
      // Grab (close Grabber)
      Robot.manipulator.ballGrab();
  
    }

    //Use distance sensor to auto close the grabber when ball is close enough
    System.out.println("getVoltage " + Robot.ai.getVoltage());
    if(  Robot.ai.getVoltage() > 1.2 && Robot.ai.getVoltage() < 2.25 &&  opRightBumper )
      Robot.manipulator.ballGrab();
    //else if(Robot.ai.getValue() < .7 && opRightBumper )

    

    // Release (open grabber) when Left trigger pressed (either controller)
    if (leftTriggerPressed && !bothTriggersPressed) {
      Robot.manipulator.ballRelease();
    }

    // Eject Ball (open grabber and extend ejector) when both triggers pressed
    // (either controller)
    if (bothTriggersPressed) {
      Robot.manipulator.ballRelease();
      Robot.manipulator.ballEjectorExtend();
      // set an ejector enabled flag
      ejectorEnabled = true;
      // start a timer
      ejectorTimer.start();
    }

    // Retract the ejector afater two seconds and both triggers are released (either
    // controller)
    if (ejectorEnabledAndTimerExpired && !bothTriggersPressed) {
      // Retract the ejector
      Robot.manipulator.ballEjectorRetract();
      // reset the ejector enabled flag
      ejectorEnabled = false;
      // stop the timer
      ejectorTimer.stop();
    }

    // pancake execute
    int Up = 0;
    int Down = 180;
    boolean drDPadUp = Robot.oi.getDriveController().getPOV(0) == Up;
    boolean opDPadUp = Robot.oi.getOpController().getPOV(0) == Up;
    boolean drDPadDown = Robot.oi.getDriveController().getPOV(0) == Down;
    boolean opDPadDown = Robot.oi.getOpController().getPOV(0) == Down;

    dPadUp = (drDPadUp || opDPadUp);
    dPadDown = (drDPadDown || opDPadDown);

    if (dPadUp && !dPadDown) { // move pancake arm up
      Robot.manipulator.pancakeUp();
      UsbLogging.debug("pancake arm up");
    }

    if (dPadDown && !dPadUp) { // move pancake arm down
      Robot.manipulator.pancakeDown();
      UsbLogging.debug("pancake arm down");
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
