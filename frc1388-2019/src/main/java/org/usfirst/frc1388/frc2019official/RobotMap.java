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

    public static final int CANID_drivetrain_LF = 3;
    public static final int CANID_drivetrain_RF = 2;

    /**
     * VictorSPX Can IDs
     */
    public static final int CANID_drivetrain_LB = 4;
    public static final int CANID_drivetrain_RB = 1;
    public static final int CANID_elevatorMotor = 5;
    public static final int CANID_climbArm = 6;
    public static final int CANID_climbWheels = 7;

    /**
     *   Pneumatics Control Modules
     */
    /* PCM CAN IDs */
    public static final int CANID_PCM_manipulator = 1;
    public static final int CANID_PCM_compressor = 1;
    public static final int CANID_PCM_base = 2;

    /* PCM1 channels */
    public static final int PCMCH_diskEjector = 1;
    public static final int PCMCH_ballejector = 2;
    public static final int PCMCH_manipulatorPush = 3;
    public static final int PCMCH_manipulatorPull = 4;
    public static final int PCMCH_pancakeArmPush = 5;
    public static final int PCMCH_pancakeArmPull = 6;

    /* PCM2 channels */
    public static final int PCMCH_towerPush = 4;
    public static final int PCMCH_towerPull = 5;
    public static final int PCMCH_lifterPush = 6;
    public static final int PCMCH_lifterPull = 7;

    /* Digital IO */
    public static final int DIO_elevatorEncoderB = 4;
    public static final int DIO_elevatorEncoderA = 5;
    public static final int DIO_bottomLimit1 = 8;
    public static final int DIO_bottomLimit2 = 9;

    /* Analog Inputs */
    // public static final int AI_channel0 = 0;
    public static final int AI_airPressure = 1;

}