// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1388.frc2019official.subsystems;


import org.usfirst.frc1388.frc2019official.ADIS16448_IMU;
import org.usfirst.frc1388.frc2019official.Robot;
import org.usfirst.frc1388.frc2019official.RobotMap;
import org.usfirst.frc1388.frc2019official.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDSourceType;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 *
 */
public class DriveTrain extends Subsystem {

    private WPI_TalonSRX leftFront;
    private WPI_TalonSRX rightFront;
    private WPI_VictorSPX leftBack;
    private WPI_VictorSPX rightBack;

    private DifferentialDrive diffDrive;

    public DriveTrain() {

        ADIS16448_IMU gyro = new ADIS16448_IMU();

        leftFront = new WPI_TalonSRX(RobotMap.CANID_drivetrain_LF);
        rightFront = new WPI_TalonSRX(RobotMap.CANID_drivetrain_RF);
        leftBack = new WPI_VictorSPX(RobotMap.CANID_drivetrain_LB);
        rightBack = new WPI_VictorSPX(RobotMap.CANID_drivetrain_RB);

        setNeutralBrake();

        // uncomment which drive mode we want to use at the moment,
        //useFrontWheelsOnly(); //2 motor controllers
        //useSpeedControllerGroups(); //2 speed controller groups
        useFollowMode(); //Follow mode, links sameside motors

        addChild("DifferentialDrive",diffDrive);
        diffDrive.setSafetyEnabled(true);
        diffDrive.setExpiration(0.1);
        diffDrive.setMaxOutput(1.0);
        diffDrive.setDeadband( 0.2 );
    }

    /*
     * The motors will apply the brake when
     *   joysticks are released
     */
    public void setNeutralBrake()
    {
        leftFront.setNeutralMode(NeutralMode.Brake);
		leftBack.setNeutralMode(NeutralMode.Brake);
		rightFront.setNeutralMode(NeutralMode.Brake);
		rightBack.setNeutralMode(NeutralMode.Brake);
    }

    public void setNeutralCoast()
    {
        leftFront.setNeutralMode(NeutralMode.Coast);
        leftBack.setNeutralMode(NeutralMode.Coast);
        rightFront.setNeutralMode(NeutralMode.Coast);
        rightBack.setNeutralMode(NeutralMode.Coast);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new Drive());

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void arcadeDrive(double speed, double rotation) {
        diffDrive.arcadeDrive(speed, rotation);
    }

    public void cheesyDrive( double speed, double rotation, boolean isQuickTurn ){
        diffDrive.curvatureDrive( speed, rotation, isQuickTurn);
    }

    public void tankDrive( double leftSpeed, double rightSpeed ){
        diffDrive.tankDrive( leftSpeed, rightSpeed );
    }
    
    private void useSpeedControllerGroups() {
        SpeedControllerGroup leftGroup = new SpeedControllerGroup( leftFront, leftBack );
        SpeedControllerGroup rightGroup = new SpeedControllerGroup( rightFront, rightBack );

        diffDrive = new DifferentialDrive( leftGroup, rightGroup );
    }

    private void useFollowMode() {
        leftBack.follow( leftFront );
        rightBack.follow( rightFront );

        diffDrive = new DifferentialDrive( leftFront, rightFront );
    }

    private void useFrontWheelsOnly() {
        diffDrive = new DifferentialDrive( leftFront, rightFront );
    }
}

