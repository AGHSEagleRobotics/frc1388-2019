/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1388.frc2019official.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc1388.frc2019official.commands.*;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import org.usfirst.frc1388.frc2019official.RobotMap;

/**
 * Add your docs here.
 */
public class Manipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  /*
  * MAKE TRIPPLE SURE TO IMPLEMENT MANIPULATOR SAFETY INTERLOCKS WHEN ADDING NEW FUNCTIONALITY
  */
  // ball grabber
  private DoubleSolenoid manipulator = new DoubleSolenoid(RobotMap.CANID_PCM_manipulator, RobotMap.PCMCH_manipulatorPush, RobotMap.PCMCH_manipulatorPull);
  private Solenoid ballEjector = new Solenoid(RobotMap.CANID_PCM_manipulator, RobotMap.PCMCH_ballejector);

  private DoubleSolenoid pancakeMaker = new DoubleSolenoid( RobotMap.CANID_PCM_manipulator, RobotMap.PCMCH_pancakeArmPush, RobotMap.PCMCH_pancakeArmPull );
  private Solenoid pancakeEjector = new Solenoid( RobotMap.CANID_PCM_manipulator, RobotMap.PCMCH_diskEjector);

  public AnalogInput ballSensor = new AnalogInput(RobotMap.AI_ballSensor);

  double ballEjectorPulseDuration = 1.0; // seconds
  boolean armIsDown;
  boolean clawIsClosed;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    setDefaultCommand(new Manipulate());
  }

  /**
   * Set the initial state of the subsystem
   */
  public void initialize() {
    // TODO: Come up with a smarter way of initializing this state
    armIsDown = false;
    clawIsClosed = true;
  }

  /**
   * Disable all actuators.
   * May be called at the end() of a command or when the robot is disabled.
   */
  public void deactivate() {
    manipulator.set(DoubleSolenoid.Value.kOff);
    pancakeMaker.set(DoubleSolenoid.Value.kOff);
    ballEjector.set(false);
    pancakeEjector.set(false);
  }

  @Override
  public void periodic() {
    // Put code here to be run every loop

  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void ballGrab() {
    if( !armIsDown )
    {
      // Claw closes when actuator is retracted
      manipulator.set(DoubleSolenoid.Value.kReverse);
      clawIsClosed = true;
    }

  }

  public void ballRelease() {
    // Claw opens when actuator is extended
    manipulator.set(DoubleSolenoid.Value.kForward);
    clawIsClosed = false;
  }

  public void ballEjectorExtend() {
    if( ! armIsDown )
    {
      // Ball is ejected when actuator is extended
      ballEjector.setPulseDuration( ballEjectorPulseDuration );
      ballEjector.startPulse();
    }
  }

  public boolean ballEjectorIsActive() {
    return ballEjector.get();
  }

  public void ballEjectorRetract() {

    // retract ball ballEjector
    ballEjector.set(false);

  }

  public void pancakeUp() {
    if( !clawIsClosed )
    {
      pancakeMaker.set(DoubleSolenoid.Value.kReverse);
      armIsDown = false;
    }
  }

  public void pancakeDown() {
    if( !clawIsClosed )
    {
      pancakeMaker.set(DoubleSolenoid.Value.kForward);
      armIsDown = true;
    }
  }

  public void pancakeEject() {
    if( !armIsDown )
    {
      pancakeEjector.setPulseDuration( ballEjectorPulseDuration );
      pancakeEjector.startPulse();

      ballEjectorExtend();
    }
  }

  public void pancakeRetract() {

    pancakeEjector.set(false);

  }

}
