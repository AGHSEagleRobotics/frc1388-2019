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
    public static final int CANID_elevatorMotor = 55;   // TODO: assign the correct ID

    /**
     *   Pneumatics Control Modules
     */
    /* PCM CAN IDs */
    public static final int CANID_PCM1 = 1;
    public static final int CANID_PCM2 = 2;

    /* PCM12V channels */
    // public static final int PCMCH_12Vchannel0 = 0;
    // public static final int PCMCH_12Vchannel1 = 1;
    public static final int PCMCH_manipulatorPush = 2;
    public static final int PCMCH_manipulatorPull = 3;
    public static final int PCMCH_ejector = 4;
    public static final int PCMCH_pancakeEjector = 5;
    public static final int PCMCH_pancakeArm = 6;

    /* PCM24V channels */
    public static final int PCMCH_towerPush = 99;       // TODO: determine the correct channel
    public static final int PCMCH_towerPull = 99;       // TODO: determine the correct channel
    // public static final int PCMCH_24Vchannel1 = 2;
    // public static final int PCMCH_24Vchannel1 = 3;
    // public static final int PCMCH_24Vchannel1 = 4;
    // public static final int PCMCH_24Vchannel1 = 5;
    // public static final int PCMCH_24Vchannel1 = 6;
    // public static final int PCMCH_24Vchannel1 = 7;

    /* Digital IO */
    public static final int DIO_elevatorEncoderA = 1;   // TODO: verify this is correct
    public static final int DIO_elevatorEncoderB = 2;   // TODO: verify this is correct
    public static final int DIO_bottomLimit1 = 8;       // TODO: verify this is correct
    public static final int DIO_bottomLimit2 = 9;       // TODO: verify this is correct
}