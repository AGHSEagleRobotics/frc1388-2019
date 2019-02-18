// TODO: REMOVE FILE REDUNDANT WITH ELEVATOR MOVE
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.commands;

import org.usfirst.frc1388.frc2019official.UsbLogging;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorLean extends Command {
  public ElevatorLean() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    UsbLogging.info(">>> " + this.getClass().getSimpleName() + " started");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    UsbLogging.info("<<< " + this.getClass().getSimpleName() + " ended");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    UsbLogging.info("<<< " + this.getClass().getSimpleName() + " interrupted");
    end();
  }
}
