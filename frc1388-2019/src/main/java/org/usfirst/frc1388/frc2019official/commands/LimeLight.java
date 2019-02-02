/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1388.frc2019official.Robot;
import org.usfirst.frc1388.frc2019official.subsystems.DriveTrain;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLight extends Command {
  public LimeLight() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry ts = NetworkTableInstance.getDefault().getTable("table").getEntry("ts");
    NetworkTableEntry tshort = NetworkTableInstance.getDefault().getTable("table").getEntry("tshort");
    NetworkTableEntry tlong =  NetworkTableInstance.getDefault().getTable("table").getEntry("tlong");
    NetworkTableEntry getpipe = NetworkTableInstance.getDefault().getTable("table").getEntry("getpipe");



    //read values periodically
    double angleTx = tx.getDouble(0.0);
    double angleTy = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double angleTs = ts.getDouble(0.0);
    double lengthTShort = tshort.getDouble(0.0);
    double lengthTLong = tlong.getDouble(0.0);
    double getPipeline = getpipe.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("Limelight Offset from Goal Vector to Robot Vector", angleTx);
    SmartDashboard.putNumber("Limelight Vertical Offset", angleTy);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("Limelight Offset from Side Wall to Goal Vector", angleTs);
    SmartDashboard.putNumber("Limelight Shortest Side of Target", lengthTShort);
    SmartDashboard.putNumber("Limelight Longest Side of Target", lengthTLong);
    SmartDashboard.putNumber("Limelight Current Pipeline", getPipeline);






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
