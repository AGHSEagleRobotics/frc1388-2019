package org.usfirst.frc1388.frc2019official;

/*
 * Use this class to define all hardware IDs on the robot.
 * IDs in this file must match the Robot Wiring Map.
 */
public class RobotMap
{
    /**
     * TalonSRX Can IDs
     */
    public static final int CANID_Drivetrain_LF = 3;
    public static final int CANID_Drivetrain_RF = 2;

    /**
     * VictorSPX Can IDs
     */
    public static final int CANID_Drivetrain_LB = 4;
    public static final int CANID_Drivetrain_RB = 1;

    /**
     *   Pneumatics Control Modules
     */
    /* PCM CAN IDs */
    public static final int CANID_PCM1 = 1;
    public static final int CANID_PCM2 = 2;

    /* PCM Module 1 Channels */
    public static final int PCMCH_manipulatorPush = 1;
    public static final int PCMCH_manipulatorPull = 2;
    public static final int PCMCH_ballEjectorPush = 3;
    public static final int PCMCH_ballEjectorPull = 4;
    public static final int PCMCH_pancakeArm = 5;
    public static final int PCMCH_pancakeEjector = 6;

    /* PCM Module 2 Channels */
    
    public static final int PCMCH_elevatorLeanExtend = 1;
    public static final int PCMCH_elevatorLeanRetract = 2;
    // public static final int PCMCH_lifterExtend = 2;
    // public static final int PCMCH_lifterRetract = 3;
}