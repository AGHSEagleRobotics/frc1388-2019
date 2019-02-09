/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.commands;

import org.usfirst.frc1388.frc2019official.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutonTurn extends Command {

  private double angle = 9999;
  private double time = 5;

  public AutonTurn( double angle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
          requires(Robot.driveTrain);

          this.angle = angle;
  }
  public AutonTurn(double timeOrAngle, boolean isTime) {
		if(isTime = true) {
			this.time = timeOrAngle;
		} else {
			this.angle = timeOrAngle;
		}
		requires(Robot.driveTrain);


	}

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(time);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 
    
    Robot.driveTrain.arcadeDrive(0, 1); } // percentage of stick, not angle  



  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(isTimedOut())
      return true;
    else
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
