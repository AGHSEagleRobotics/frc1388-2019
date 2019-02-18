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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Air extends Subsystem {

    private final double k_transducerMaxPSI = 157.0;       // determined empirically; spec = 150 psi
    private final double k_fullRange = (k_transducerMaxPSI / 4.0 * 5.0);  // (psi / Vrange * Vfullscale) - see datasheet
    private final double k_offset = -21;      // PSI - empirical

    private boolean lastCompressorOn;           // record compressor status

    private Timer timer;
    private double loggingPeriod = 2;           // log info this often

    private int minClimbPressure = 80;          // Singal that it's ok to climb at this pressure

    private Compressor compressor;
    private AnalogPotentiometer airPressure;
    private DriverStation driverStation;

    // Constructor
    public Air() {
        // create objects
        compressor = new Compressor(RobotMap.CANID_PCM_compressor);
        airPressure = new AnalogPotentiometer(RobotMap.AI_airPressure, k_fullRange, k_offset);

        timer = new Timer();
        timer.start();

        driverStation = DriverStation.getInstance();

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

        if (driverStation.isEnabled()) {
            if ((compressorOn != lastCompressorOn) || (timer.hasPeriodPassed(loggingPeriod))) {
                logCompressorInfo(pressure, compressorOn);

                // Reset the timer period after logging
                timer.reset();
            }
        }
        lastCompressorOn = compressorOn;

        // Send info to dashboard
        SmartDashboard.putBoolean("Compressor Status", compressorOn);
        SmartDashboard.putNumber("Air Pressure", pressure);
        SmartDashboard.putBoolean("Ok To Climb", (pressure >= minClimbPressure));
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
