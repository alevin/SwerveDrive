/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.commands.ShowLimelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static int camMode = 0;
  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public static NetworkTableEntry tx = table.getEntry("tx");
  public static NetworkTableEntry ty = table.getEntry("ty");
  public static NetworkTableEntry ta = table.getEntry("ta");

  // read values periodically
  public static double limelightx;
  public static double limelighty;
  public static double limelightarea;
  public static double angleErr;
  private double kP = .05;
  private double kI = .001;
  private double kD = 0;
  private double xErr = 0;
  private double xIErr = 0;

  public static final double WIDTH = 320;
  public static final double HEIGHT = 240;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ShowLimelight());
  }

  public static void switchModes() {
    if (camMode == 0) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
      camMode = 1;
    } 
    else {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
      camMode = 0;
    }
  }

  public void printInfo() {
    // post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", limelightx);
    SmartDashboard.putNumber("LimelightY", limelighty);
    SmartDashboard.putNumber("LimelightArea", limelightarea);
    SmartDashboard.putNumber("Gyro Angle", (Robot.swerveDriveSubsystem.getGyroAngle()));
  }

  public void align() // method to line us up in the middle of the tape
  {
    limelightx = tx.getDouble(0.3);
    limelighty = ty.getDouble(0.3);
    limelightarea = ta.getDouble(0.3);
    angleErr = 0 - (Robot.swerveDriveSubsystem.getGyroAngle() % 360);
    double rotation = 0;
    double strafe = 0;

    xErr = 0 - limelightx;
    xIErr = xIErr + (xErr)*.02;
    

    if (Math.abs(limelightx) > .5) {
      //strafe = 0.12 * limelightx;
      strafe = kP*xErr + kI*xIErr;
      // double a = limelightx;
      // System.out.println(a);
    }
    if(Math.abs(angleErr) > .5) {
      rotation = .01 * angleErr;
      System.out.println(angleErr);
    }

    Robot.swerveDriveSubsystem.holonomicDrive(.3, -strafe, rotation); //forward: .3 * (1/limelightarea)

  }
}
