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


import org.usfirst.frc1388.frc2019official.Robot;
import org.usfirst.frc1388.frc2019official.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.NeutralMode;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX leftFront;
    private WPI_TalonSRX rightFront;
    //private DifferentialDrive diffDrive;
    private WPI_TalonSRX  leftBack;
    private WPI_TalonSRX rightBack;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DifferentialDrive diffDrive;

    public DriveTrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        leftFront = new WPI_TalonSRX(3);
        rightFront = new WPI_TalonSRX(2);
        leftBack = new WPI_TalonSRX(4);                    
        rightBack = new WPI_TalonSRX(1);

        setNeutralBrake();

        // uncomment which drive mode we want to use at the moment,
        //useFrontWheelsOnly(); //2 motor controllers
        //useSpeedControllerGroups(); //2 speed controller groups
        useFollowMode(); //Follow mode, links sameside motors
        
        // Why put these 3 lines here? We can just use the `useFollowMode()` method.
        // diffDrive = new DifferentialDrive(leftFront, rightFront); // arcade drive
        // leftBack.follow( leftFront );
        // rightBack.follow( rightFront );

        addChild("DifferentialDrive",diffDrive);
        diffDrive.setSafetyEnabled(true);
        diffDrive.setExpiration(0.1);
        diffDrive.setMaxOutput(1.0);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    
    }

    /*
     * The motors will apply the brake when
     *   joysticks are released
     */
    private void setNeutralBrake()
    {
        leftFront.setNeutralMode(NeutralMode.Brake);
		leftBack.setNeutralMode(NeutralMode.Brake);
		rightFront.setNeutralMode(NeutralMode.Brake);
		rightBack.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Drive());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

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

