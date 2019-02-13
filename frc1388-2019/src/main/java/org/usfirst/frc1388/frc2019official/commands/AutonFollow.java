/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.commands;

import org.usfirst.frc1388.frc2019official.Robot;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;


public class AutonFollow extends Command {

  double k_r = 0.12;
  double k_a = 0.09;// area constant
  double k_max = .6;//max motor power

  /**
   * divide target area into this to get a speed
   *   that decreases as the target area increases
   * 
   * i.e. dist_mult / Robot.area
   */
  double dist_mult = 7;

  public AutonFollow() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  //   calculateTime();
  //  setTimeout(turnTime);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

      double rotation = k_r * Robot.angleTx;
      double speed = dist_mult / Robot.area;

      rotation = Math.min(rotation, k_max);
      rotation = Math.max(rotation, -k_max);

      speed = Math.min(speed, k_max);
      speed = Math.max(speed, -k_max);

      if( Robot.angleTx >= 0)
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
    System.out.println( "Robot.area: " + Robot.area );
    if(Robot.area > 15){
        return true;
    }
    else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
