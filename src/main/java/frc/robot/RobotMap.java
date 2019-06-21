package frc.robot;

import frc.robot.hardware.TalonConfig;

public class RobotMap {
  public static final int
    DRIVE_TALON_FL = 1,
    DRIVE_TALON_FR = 3,
    DRIVE_TALON_BL = 2,
    DRIVE_TALON_BR = 4;
  public static final int[]
    DRIVE_ENCODER_L = {0, 1},
    DRIVE_ENCODER_R = {2, 3};
  public static final int DRIVE_ULTRASONIC = 0;
  public static final int DRIVE_PIDGEON = 5;
  public static final double DRIVE_WHEEL_DIA = 15.2;
  public static final double DRIVE_TRACKWIDTH = 62.5;
  public static final TalonConfig DRIVE_TALON_CONFIG = new TalonConfig(){
    @Override
    public void buildConfig() {
      openloopRamp = 0.3;
      voltageCompSaturation = 10.0;
      neutralDeadband = 0.10;
    }
  };

  public static final int INTAKE_TALON = 6;
  public static final int
    INTAKE_LEFT = 0,
    INTAKE_RIGHT = 1;
  public static final double INTAKE_WHEEL_SPEED_IN = 0.35;
  public static final double INTAKE_WHEEL_SPEED_OUT = 0.5;
  public static final double INTAKE_JOINT_SPEED = 0.15;

  public static final int LIFT_VICTOR = 7;
  public static final double LIFT_SPEED = 0.75;

  public static final int CONTROL_JOYSTICK = 0;
  public static final int CONTROL_ASSIST = 1;
  public static final double
    CONTROL_SPEED_DAMP = 0.4,
    CONTROL_ROTATION_DAMP = 0.4;
}
