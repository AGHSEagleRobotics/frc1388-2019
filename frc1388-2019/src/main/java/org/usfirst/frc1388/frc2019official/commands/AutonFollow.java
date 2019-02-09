/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.commands;

import org.usfirst.frc1388.frc2019official.NetworkTables;
import org.usfirst.frc1388.frc2019official.Robot;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class AutonFollow extends Command {
 // NetworkTableEntry tx = .getEntry("tx");
        //  NetworkTableEntry tv = table.getEntry("tv");


  double k_r = 0.12;
  double k_a = 0.09;// area constant
  double k_max = .8;//max motor power

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

        double rotation = k_r * NetworkTables.angleTx;
        double speed = NetworkTables.area / k_a;

        rotation = Math.min(rotation, k_max);
        rotation = Math.max(rotation, -k_max);

        speed = Math.min(speed, k_max);
        speed = Math.max(speed, -k_max);
        // double angleTx = tx.getDouble(0.0);
       // double getTv = tv.getDouble(1.0);
       // System.out.println("getTv" + getTv);

    
     //if(NetworkTables.getTv == 1.0 ){
      //if(NetworkTables.angleTx >= 0 ){
      if( NetworkTables.angleTx >= 0)
        Robot.driveTrain.arcadeDrive(-speed , -rotation);
      else
      Robot.driveTrain.arcadeDrive(-speed , -rotation);

      //}
     /* else
      {
        Robot.driveTrain.arcadeDrive(0 , power);
      } */
    }
    //}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(NetworkTables.area == 0){
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
  }
}
