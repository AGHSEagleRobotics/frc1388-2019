/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Add your docs here.
 */
public class NetworkTables extends Robot {
   // private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-eagle");


    public static NetworkTableEntry tx, ta, ty, ts, tshort, tlong, getPipe, tv;
 /*  public NetworkTableEntry tx;
   public NetworkTableEntry ta;
   public NetworkTableEntry ty;
   public NetworkTable+Entry ts;
   public NetworkTableEntry tshort;
   public NetworkTableEntry tlong;
   public NetworkTableEntry getpipe;
   public NetworkTableEntry tv; */
   public static double angleTx, area, angleTs, angleTy, getTv, lengthTShort, lengthTLong, getPipeline ;


    @Override
    public void robotInit() {
        super.robotInit();
       /* NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry ts = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts");
        NetworkTableEntry tshort = NetworkTableInstance.getDefault().getTable("table").getEntry("tshort");
        NetworkTableEntry tlong =  NetworkTableInstance.getDefault().getTable("table").getEntry("tlong");
        NetworkTableEntry getpipe = NetworkTableInstance.getDefault().getTable("table").getEntry("getpipe");
        NetworkTableEntry tv = NetworkTableInstance.getDefault().getTable("table").getEntry("getpipe"); */
    }
    @Override
    public void robotPeriodic() {
        super.robotPeriodic();
         NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-eagle");
      //  System.out.println("x");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        ts = NetworkTableInstance.getDefault().getTable("limelight-eagle").getEntry("ts");
        tshort = NetworkTableInstance.getDefault().getTable("table").getEntry("tshort");
        tlong =  NetworkTableInstance.getDefault().getTable("table").getEntry("tlong");
        getPipe = NetworkTableInstance.getDefault().getTable("table").getEntry("getpipe");
        tv = NetworkTableInstance.getDefault().getTable("table").getEntry("getpipe");

        angleTx = tx.getDouble(0.0);
        area = ta.getDouble(0.0);
        angleTs = ts.getDouble(0.0);
        angleTy = ty.getDouble(0.0);
        getTv = tv.getDouble(0.0);
        lengthTShort = tshort.getDouble(0.0);
        lengthTLong = tlong.getDouble(0.0);
        getPipeline = getPipe.getDouble(0.0);
    }
    @Override
    public void teleopPeriodic() {
        super.teleopPeriodic();
        
     /*   angleTx = tx.getDouble(0.0);
        area = ta.getDouble(0.0);
        angleTs = ts.getDouble(0.0);
        angleTy = ty.getDouble(0.0);
        getTv = tv.getDouble(0.0);
        lengthTShort = tshort.getDouble(0.0);
        lengthTLong = tlong.getDouble(0.0);
        getPipeline = getPipe.getDouble(0.0); */

        SmartDashboard.putNumber("LimelightX", angleTx);
        SmartDashboard.putNumber("LimelightY", angleTy);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("LimelightArea", getTv);
        SmartDashboard.putNumber("LimelightArea", angleTs);
        SmartDashboard.putNumber("LimelightArea", lengthTShort);
        SmartDashboard.putNumber("LimelightArea", lengthTLong);
        SmartDashboard.putNumber("LimelightArea", getPipeline) ;
        
    }
}
