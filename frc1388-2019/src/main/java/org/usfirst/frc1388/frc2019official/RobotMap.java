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
    public static final int CANID_PCM_base = 2;

    /* PCM1 channels */
    // public static final int PCMCH_PCM1channel0 = 0;
    // public static final int PCMCH_PCM1channel1 = 1;
    public static final int PCMCH_manipulatorPush = 2;
    public static final int PCMCH_manipulatorPull = 3;
    public static final int PCMCH_ballejector = 4;
    public static final int PCMCH_diskEjector = 5;
    public static final int PCMCH_pancakeArmlift = 6;
    public static final int PCMCH_pancakeArmlower = 7;

    /* PCM2 channels */
    public static final int PCMCH_towerPush = 0;       // TODO: determine the correct channel
    public static final int PCMCH_towerPull = 1;       // TODO: determine the correct channel
    public static final int PCMCH_lifter_L = 2;
    public static final int PCMCH_lifter_R = 3;
    // public static final int PCMCH_PCM2channel1 = 5;
    // public static final int PCMCH_PCM2channel1 = 6;
    // public static final int PCMCH_PCM2channel1 = 7;

    /* Digital IO */
    public static final int DIO_elevatorEncoderB = 4;
    public static final int DIO_elevatorEncoderA = 5;
    public static final int DIO_bottomLimit1 = 8;
    public static final int DIO_bottomLimit2 = 9;

    /* Analog Inputs */
    // public static final int AI_channel0 = 0;
    public static final int AI_airPressure = 1;

}