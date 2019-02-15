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
    public static final int CANID_PCM12v = 0;
    public static final int CANID_PCM24v = 1;

    /* PCM12V channels */
    // public static final int PCMCH_12Vchannel0 = 0;
    // public static final int PCMCH_12Vchannel1 = 1;
    public static final int PCMCH_manipulatorPush = 2;
    public static final int PCMCH_manipulatorPull = 3;
    public static final int PCMCH_ejector = 4;
    public static final int PCMCH_pancakeEjector = 5;
    public static final int PCMCH_pancakeArm = 6;

    /* PCM24V channels */
    // public static final int PCMCH_24Vchannel0 = 0;
    // public static final int PCMCH_24Vchannel1 = 1;
    // public static final int PCMCH_24Vchannel1 = 2;
    // public static final int PCMCH_24Vchannel1 = 3;
    // public static final int PCMCH_24Vchannel1 = 4;
    // public static final int PCMCH_24Vchannel1 = 5;
    // public static final int PCMCH_24Vchannel1 = 6;
    // public static final int PCMCH_24Vchannel1 = 7;

    public static final int PCM_Module2_12V = 1;


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