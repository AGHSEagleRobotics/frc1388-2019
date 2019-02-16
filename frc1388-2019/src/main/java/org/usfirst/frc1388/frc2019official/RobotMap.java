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
    public static final int CANID_ClimbArm = 6;
    public static final int CANID_ClimbWheels = 7;

    /**
     *   Pneumatics Control Modules
     */
    /* PCM CAN IDs */
    public static final int CANID_PCM1 = 1;
    public static final int CANID_PCM2 = 2;

    /* PCM1 channels */
    // public static final int PCMCH_PCM1channel0 = 0;
    // public static final int PCMCH_PCM1channel1 = 1;
    public static final int PCMCH_manipulatorPush = 2;
    public static final int PCMCH_manipulatorPull = 3;
    public static final int PCMCH_ejector = 4;
    public static final int PCMCH_pancakeEjector = 5;
    public static final int PCMCH_pancakeArm = 6;

    /* PCM2 channels */
    public static final int PCMCH_towerPush = 0;       // TODO: determine the correct channel
    public static final int PCMCH_towerPull = 1;       // TODO: determine the correct channel
    public static final int PCMCH_Lifter_L = 2;
    public static final int PCMCH_Lifter_R = 3;
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

     /**
     * Solenoid IDs
     */
    // 12V PCM IDs ( PCM 1 )
    public static final int CANID_Climber_L = 0;
    public static final int CANID_Climber_R = 1;
}