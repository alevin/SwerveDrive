/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team5507.robot.commands.ClimberStop;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static TalonSRX arm1 = new TalonSRX(25); //change later
  private static TalonSRX arm2 = new TalonSRX(26);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ClimberStop());
  }

  public void stop() { //when pressed
    arm1.set(ControlMode.PercentOutput, 0);
    arm2.set(ControlMode.PercentOutput, 0);
  }

  public void latch() { //when held
    arm1.set(ControlMode.PercentOutput, .5);
  }

  public void rollUp() { //when held
    arm1.set(ControlMode.PercentOutput, .3);
    arm2.set(ControlMode.PercentOutput, .3);
  }

  public void pullUpArm2() { //when held
    arm2.set(ControlMode.PercentOutput, -0.3);
  }

  public void pullUpArm1() { //when held
    arm1.set(ControlMode.PercentOutput, -0.3);
  }
}
