package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU.CalibrationMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotMap;
import frc.robot.commands.JoystickArcadeDrive;
import frc.robot.commands.XboxTankDrive;
import frc.robot.hardware.E4TEncoder;
import frc.robot.hardware.MB1013Ultrasonic;

public class DriveBase extends Subsystem {

  private WPI_TalonSRX
    motorFrontLeft = new WPI_TalonSRX(RobotMap.DRIVE_TALON_FL),
    motorFrontRight = new WPI_TalonSRX(RobotMap.DRIVE_TALON_FR),
    motorBackLeft = new WPI_TalonSRX(RobotMap.DRIVE_TALON_BL),
    motorBackRight = new WPI_TalonSRX(RobotMap.DRIVE_TALON_BR);
  
  private E4TEncoder
    encoderLeft = new E4TEncoder(RobotMap.DRIVE_WHEEL_DIA*Math.PI, RobotMap.DRIVE_ENCODER_L, true),
    encoderRight = new E4TEncoder(RobotMap.DRIVE_WHEEL_DIA*Math.PI, RobotMap.DRIVE_ENCODER_R, false);
  
  private MB1013Ultrasonic
    rangeFinder = new MB1013Ultrasonic(RobotMap.DRIVE_ULTRASONIC);
  
  private PigeonIMU
    pidgeon = new PigeonIMU(RobotMap.DRIVE_PIDGEON);

  private DifferentialDrive drive;

  public DriveBase() {
    super("drive_base");

    pidgeon.configFactoryDefault();

    motorFrontLeft.configFactoryDefault();
    motorFrontRight.configFactoryDefault();

    motorFrontLeft.setStatusFramePeriod(StatusFrame.Status_1_General, 10);
    motorFrontRight.setStatusFramePeriod(StatusFrame.Status_1_General, 10);

    motorFrontLeft.configAllSettings(RobotMap.DRIVE_TALON_CONFIG);
    motorFrontRight.configAllSettings(RobotMap.DRIVE_TALON_CONFIG);
    motorFrontLeft.enableVoltageCompensation(true);
    motorFrontRight.enableVoltageCompensation(true);
    motorFrontLeft.setNeutralMode(NeutralMode.Brake);
    motorFrontRight.setNeutralMode(NeutralMode.Brake);

    motorBackLeft.follow(motorFrontLeft);
    motorBackRight.follow(motorFrontRight);

    drive = new DifferentialDrive(motorFrontLeft, motorFrontRight);

    reset();
  }

  @Override
  public void periodic() {}

  public void reset() {
    encoderLeft.reset();
    encoderRight.reset();
    pidgeon.setFusedHeading(0.0);
  }

  public void arcadeDrive(double speed, double rotation, boolean squareInputs) {
    drive.arcadeDrive(speed, rotation, squareInputs);
  }

  public void tankDrive(double speedLeft, double speedRight) {
    drive.tankDrive(speedLeft, speedRight);
  }

  public void stop() {
    drive.tankDrive(0, 0);
  }

  public double getEncoderLeft() {
    return encoderLeft.getDistance();
  }

  public double getEncoderRight() {
    return encoderRight.getDistance();
  }

  public double getEncoderAngle() {
    return 180*(getEncoderLeft() - getEncoderRight()) / (Math.PI*RobotMap.DRIVE_TRACKWIDTH);
  }

  public double getEncoderDistance() {
    return (getEncoderLeft() + getEncoderRight()) / 2; 
  }

  public double getDistanceToSurface() {
    return rangeFinder.getDistance();
  }

  public void gyroCalibrate() {
    pidgeon.enterCalibrationMode(CalibrationMode.BootTareGyroAccel, 2500);
  }

  public double getAngle() {
    return pidgeon.getFusedHeading();
  }

  @Override
  public void initDefaultCommand() {
    if(DriverStation.getInstance().getJoystickIsXbox(RobotMap.CONTROL_JOYSTICK)) setDefaultCommand(new XboxTankDrive());
    else setDefaultCommand(new JoystickArcadeDrive());
  }
}
