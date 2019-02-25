/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.commands;

import org.usfirst.frc1388.frc2019official.Robot;
import org.usfirst.frc1388.frc2019official.UsbLogging;

import edu.wpi.first.wpilibj.command.Command;


public class AutonFollow extends Command {

  double k_r = 0.12;
  double k_a = 0.09;// area constant
  double k_max = .6;//max motor power

  /**
   * Divide target area into this to get a speed
   *   that decreases as the target area increases
   * 
   * i.e. dist_mult / Robot.area
   */
  double dist_mult = 7;

  /**
   * When the target is larger than this, stop the robot
   */
  double target_max_area = 15.0;

  public AutonFollow() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    UsbLogging.info(">>> " + this.getClass().getSimpleName() + " started");
    //   calculateTime();
  //  setTimeout(turnTime);

    // Turn on the camera's LED
    Robot.vision.ledOn();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

      double rotation = k_r * Robot.vision.targetAngleTx;
      double speed = dist_mult / Robot.vision.targetArea;

      rotation = Math.min(rotation, k_max);
      rotation = Math.max(rotation, -k_max);

      speed = Math.min(speed, k_max);
      speed = Math.max(speed, -k_max);

      if( Robot.vision.targetAngleTx >= 0)
        Robot.driveTrain.arcadeDrive(-speed , -rotation);
      else
        Robot.driveTrain.arcadeDrive(-speed , -rotation);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    /**
     * If the driver sticks are pressed more than half way
     *   the AutonFollow is finished
     */
    if ( Math.abs(Robot.oi.driveLeftStickY) > 0.5 || Math.abs(Robot.oi.driveRightStickX) > 0.5 )
      return true;

    /**
     * If area is greater than a size, we are close enough
     */
    if(Robot.vision.targetArea > target_max_area){
        return true;
    }
    else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Turn off the camera's LED
    Robot.vision.ledOff();

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
