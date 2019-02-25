/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {

    public NetworkTable limelight;

    public double targetAngleTx, targetArea, targetSkew, targetAngleTy, streamMode ;

    // streaming modes - Limelight "stream" NetworkTableEntry
    public static final int StreamMode_Standard = 0;
    public static final int StreamMode_PiPMain = 1;
    public static final int StreamMode_PiPSecondary = 2;
    
    public Vision() {
        limelight = NetworkTableInstance.getDefault().getTable("limelight-eagle");

        limelight.getEntry("stream").setNumber( StreamMode_PiPSecondary );
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void periodic() {
        targetArea = limelight.getEntry("ta").getDouble(0.0);
        targetAngleTx = limelight.getEntry("tx").getDouble(0.0);
        targetAngleTy = limelight.getEntry("ty").getDouble(0.0);
        targetSkew = limelight.getEntry("ts").getDouble(0.0);
        streamMode = limelight.getEntry("stream").getDouble(0.0);

        SmartDashboard.putNumber("LimelightArea", targetArea);
        SmartDashboard.putNumber("LimelightAngleTx", targetAngleTx);
        SmartDashboard.putNumber("LimelightAngleTy", targetAngleTy);
        SmartDashboard.putNumber("LimelightAngleTs", targetSkew);
        SmartDashboard.putNumber("LimelightStreamMode", streamMode);
    }
}
