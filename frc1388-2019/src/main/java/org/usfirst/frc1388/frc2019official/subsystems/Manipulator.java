/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc1388.frc2019official.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import org.usfirst.frc1388.frc2019official.RobotMap;

/**
 * Add your docs here.
 */
public class Manipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  /*
  * TODO: double check the pneumatic controller module IDs and channel IDs
  *
  */
  // ball grabber
  DoubleSolenoid manipulator = new DoubleSolenoid(RobotMap.CANID_PCM_manipulator, RobotMap.PCMCH_manipulatorPush, RobotMap.PCMCH_manipulatorPull); // ball grabber
  public Solenoid ballEjector = new Solenoid(RobotMap.CANID_PCM_manipulator, RobotMap.PCMCH_ballejector); // ball ballEjector

  DoubleSolenoid pancakeMaker = new DoubleSolenoid( RobotMap.CANID_PCM_manipulator, RobotMap.PCMCH_pancakeArmlift, RobotMap.PCMCH_pancakeArmlower );
  Solenoid pancakeEjector = new Solenoid( RobotMap.CANID_PCM_manipulator, RobotMap.PCMCH_diskEjector); // pancake eject

  double ballEjectorPulseDuration = 2.0; // seconds

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    setDefaultCommand(new Manipulate());

    manipulator.set(DoubleSolenoid.Value.kOff);
    ballEjector.set(false);

    pancakeMaker.set(DoubleSolenoid.Value.kForward);
    pancakeEjector.set(false);
  }

  public void initialize() {
    manipulator.set(DoubleSolenoid.Value.kOff);
    ballEjectorRetract();
    pancakeUp();
    pancakeRetract();
  }

  @Override
  public void periodic() {
    // Put code here to be run every loop

  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void ballGrab() {

    // Claw closes when actuator is retracted
    manipulator.set(DoubleSolenoid.Value.kReverse);

  }

  public void ballRelease() {

    // Claw opens when actuator is extended
    manipulator.set(DoubleSolenoid.Value.kForward);

  }

  public void ballEjectorExtend() {
    // Ball is ejected when actuator is extended
    ballEjector.setPulseDuration( ballEjectorPulseDuration );
    ballEjector.startPulse();
  }

  public boolean ballEjectorIsActive() {
    return ballEjector.get();
  }

  public void ballEjectorRetract() {

    // retract ball ballEjector
    ballEjector.set(false);

  }

  public void pancakeUp() {

    pancakeMaker.set(DoubleSolenoid.Value.kReverse);

  }

  public void pancakeDown() {

    pancakeMaker.set(DoubleSolenoid.Value.kForward);

  }

  public void pancakeEject() {

    pancakeEjector.set(true);

  }

  public void pancakeRetract() {

    pancakeEjector.set(false);

  }

}
