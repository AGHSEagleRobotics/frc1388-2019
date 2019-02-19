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

public class AutonDrive extends Command {

  private double distance = 99999;
  private double time = 99999;
  double k_p = 0.1;
  double k_max = .5;//max motor power


  public AutonDrive(  ) { // TODO readd distance

      requires(Robot.driveTrain);

    //  this.distance = distance;
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
    double power = k_p * Robot.angleTx;


        power = Math.min(power, k_max);
        power = Math.max(power, -k_max);

        // double angleTx = tx.getDouble(0.0);
       // double getTv = tv.getDouble(1.0);
       // System.out.println("getTv" + getTv);

    
     //if(NetworkTables.getTv == 1.0 ){
      //if(NetworkTables.angleTx >= 0 ){
      


    Robot.driveTrain.arcadeDrive( power , 0); //.2 is speed set to wheels when driving straigh

  
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
