/**
 * Simple class containing constants used throughout project
 */
package org.usfirst.frc1388.frc2019official.subsystems.talon;

public class Constants {
	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
    public static final int kTimeoutMs = 30;

	/**
	 * PID Gains may have to be adjusted based on the responsiveness of control loop.
     * kF: 1023 represents output value to Talon at 100%, 7200 represents Velocity units at 100% output
     *
	 *
	 *  	                                    			  kP   kI   kD   kF          Iz    PeakOut */

	// Default
	// static double p = 0.25;
	// static double i = 0.001;
	// static double d = 20;
	// static double f = 1023.0 / 7200.0;
	// static int Iz = 300;
	// static double max_power = 1.0;

	static double p = 0.25;
	static double i = 0.0; /* CAUTON: When non-zero, `i` causes robot to continue at high speeds when sticks are at zero */
	static double d = 20.0;
	static double f = 0.1;
	static int Iz = 300;
	static double max_power = 0.5;


    public final static Gains kGains_Velocit = new Gains( p, i, d, f, Iz, max_power);
}
