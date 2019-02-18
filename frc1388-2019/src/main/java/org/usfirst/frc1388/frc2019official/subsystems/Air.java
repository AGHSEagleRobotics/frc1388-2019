/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.subsystems;

import org.usfirst.frc1388.frc2019official.RobotMap;
import org.usfirst.frc1388.frc2019official.UsbLogging;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Air extends Subsystem {

    private Compressor compressor;

    private static AnalogPotentiometer airPressure;

    private final double k_transducerMaxPSI = 157.0;       // determined empirically; spec = 150 psi
    private final double k_fullRange = (k_transducerMaxPSI / 4.0 * 5.0);  // (psi / Vrange * Vfullscale) - see datasheet
    private final double k_offset = -21;      // PSI - empirical

    private boolean lastCompressorOn;           // record compressor status

    private Timer timer;
    private double loggingPeriod = 5;           // log info this often

    // Constructor
    public Air() {
        // create objects
        compressor = new Compressor(RobotMap.CANID_PCM_compressor);
        airPressure = new AnalogPotentiometer(RobotMap.AI_airPressure, k_fullRange, k_offset);
        timer = new Timer();

        lastCompressorOn = false;
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    @Override
    public void periodic() {
        int pressure = (int) getPressure();
        boolean compressorOn = compressor.enabled();

        if (compressorOn != lastCompressorOn) {
            // The compressor has just turned on or off
            logCompressorInfo(pressure, compressorOn);

            if (compressorOn) {
                timer.reset();
                timer.start();
            }
            else {
                timer.stop();
            }
        }
        lastCompressorOn = compressorOn;

        if (timer.hasPeriodPassed(loggingPeriod)) {
            logCompressorInfo(pressure, compressorOn);
        }

        // Send info to dashboard
        SmartDashboard.putBoolean("Compressor Status", compressorOn);
        SmartDashboard.putNumber("Air Pressure", pressure);
    }

	private void logCompressorInfo(int pressure, boolean compressorOn) {
		String statusStr;
		statusStr = "Compressor is " + (compressorOn ? "ON " : "OFF");
		statusStr += "  Pressure= " + pressure + "psi";
		if (compressorOn) {
            statusStr += String.format("  Current= %.2f A", compressor.getCompressorCurrent());
        }
        UsbLogging.info(statusStr);
    }

    public double getPressure() {
        return airPressure.get();
    }
}
