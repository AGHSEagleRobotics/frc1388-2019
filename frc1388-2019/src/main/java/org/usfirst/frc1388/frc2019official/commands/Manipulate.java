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

    boolean leftTriggersPressed = Robot.oi.leftTriggerPressed();
    boolean rightTriggersPressed = Robot.oi.rightTriggerPressed();
    boolean oppositeTriggersPressed = Robot.oi.oppositeTriggersPressed();

    boolean bothTriggersPressed = (leftTriggersPressed && rightTriggersPressed) && ! oppositeTriggersPressed;
    ejectorEnabledAndTimerExpired = (ejectorEnabled && ejectorTimer.hasPeriodPassed(2));

    // Eject Ball (open grabber and extend ejector) when both triggers pressed (either controller)
    if (bothTriggersPressed) {
      Robot.manipulator.ballRelease();
      Robot.manipulator.ballEjectorExtend();

      // TODO: Explain why we keep track of ejector
      ejectorEnabled = true;

      // TODO: Explain why we start timer
      ejectorTimer.start();
    }

    // Grab (close grabber) when Right trigger pressed (either controller)
    else if (rightTriggersPressed && ! ejectorEnabled) {
      // Grab (close Grabber)
      Robot.manipulator.ballGrab();
    }

    // Release (open grabber) when Left trigger pressed (either controller)
    else if (leftTriggersPressed) {
      Robot.manipulator.ballRelease();
    }

    // Retract the ejector after two seconds and both triggers are released (either controller)
    if (ejectorEnabledAndTimerExpired && ! bothTriggersPressed) {
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
