/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team5507.robot.commands.ClimberStop;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static CANSparkMax arm1 = new CANSparkMax(1, MotorType.kBrushless);
  private static CANEncoder NEncoder = new CANEncoder(arm1);
  private static CANPIDController NPidController = new CANPIDController(arm1);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ClimberStop());
  }
  

  /*public void stop() { //when pressed
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
  } */
   
  public void armOneMove(double speed) {//up or down
    arm1.set(speed);
  }
  // kP = 0.2; 
    // kI = 1e-4;
    // kD = 1; 
    // kIz = 0; 
    // kFF = 0; 
    // kMaxOutput = 1; 
    // kMinOutput = -1;

  public void armOneZero()
  {
    
  }

  public void armOneFortyFive()
  {

  }
}

