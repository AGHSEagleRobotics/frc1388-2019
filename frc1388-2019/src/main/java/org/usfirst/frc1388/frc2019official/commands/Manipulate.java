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

import javax.lang.model.util.ElementScanner6;

import org.usfirst.frc1388.frc2019official.Robot;
import org.usfirst.frc1388.frc2019official.UsbLogging;

import edu.wpi.first.wpilibj.Timer;

public class Manipulate extends Command {

  // ballgrab variables
  private boolean ejectorEnabled = false;
  private Timer ejectorTimer;
  private boolean ejectorEnabledAndTimerExpired;

  // pancake variables
  private boolean pancakeIsDown = false;


  public Manipulate() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    requires(Robot.manipulator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    ballGrabExecute();
    pancakeExecute();
  }
  
  /**
   *  ball grab execute method
   */
  private void ballGrabExecute() {
    boolean leftBumper = Robot.oi.driveController.getBumperPressed( Hand.kLeft );
    boolean rightBumper = Robot.oi.driveController.getBumperPressed( Hand.kRight);
    boolean getYButton = Robot.oi.driveController.getYButton();

    boolean bothBumpersPressed = (Robot.oi.driveController.getBumper( Hand.kLeft ) && Robot.oi.driveController.getBumper( Hand.kRight ));
    ejectorEnabledAndTimerExpired = (ejectorEnabled && ejectorTimer.hasPeriodPassed(2));

    // Eject Ball (open grabber and extend ejector) when both triggers pressed
    if ( bothBumpersPressed ) {
      UsbLogging.info( "[Manipulate] Ball is ejecting" );
      Robot.manipulator.ballRelease();

      /**
       * Use pulse duration to eject ball.
       * Automatically retracts after ballEjectorPulseDuration
       */
      Robot.manipulator.ballEjectorExtend();
    }

    // Grab (close grabber) when Right trigger pressed
    else if (rightBumper && ! Robot.manipulator.ballEjectorIsActive()) {
      UsbLogging.info( "[Manipulate] Ball grabber closing" );

      // Grab (close Grabber)
      Robot.manipulator.ballGrab();
    }

    // Release (open grabber) when Left trigger pressed
    else if (leftBumper) {
      UsbLogging.info( "[Manipulate] Ball grabber opening" );

      Robot.manipulator.ballRelease();
    }

    // open grabber and close when the ball is detected
    if( getYButton && Robot.exampleAnalog.getVoltage() > 1.2 && Robot.exampleAnalog.getVoltage() < 2.25)
    {
      UsbLogging.info( "[Manipulate] Ball grabber closing using proximity sensor" );

      Robot.manipulator.ballGrab();
    }

  }

  /**
   * pancake arm execute method
   */
  private void pancakeExecute() {
    // pancake execute
    boolean upPressed = Robot.oi.povUpPressed() || Robot.oi.povUpLeftPressed() || Robot.oi.povUpRightPressed();
    boolean downPressed = Robot.oi.povDownPressed() || Robot.oi.povDownLeftPressed() || Robot.oi.povDownRightPressed();
    
    /**
     * at the start, the pancake arm is default to an up position
     */

    if (upPressed && !downPressed ) { // move pancake arm up
      pancakeIsDown = false;
      
    }

    else if (downPressed && !upPressed ) { // move pancake arm down
      pancakeIsDown = true;
      
    }

    if( pancakeIsDown ) {
      Robot.manipulator.pancakeDown();
    }

    else {
      Robot.manipulator.pancakeUp();
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
