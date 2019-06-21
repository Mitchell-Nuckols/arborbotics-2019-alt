package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.DriveStraightPID;
import frc.robot.commands.auto.DriveTimed;
import frc.robot.commands.auto.DriveTurnEncoderPID;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;

public class Robot extends TimedRobot {
  public static DriveBase driveBase = new DriveBase();
  public static Lift lift = new Lift();
  public static Intake intake = new Intake();
  
  public static OI oi;

  Command autonomousCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    oi = new OI();


    chooser.addOption("Level 1", new DriveTimed(2, 0.5));
    chooser.addOption("Level 2", new DriveTimed(4, -0.5));
    chooser.setDefaultOption("None", null);
    SmartDashboard.putData("Auto Mode", chooser);

    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setVideoMode(PixelFormat.kMJPEG, 400, 300, 10);
    Shuffleboard.getTab("SmartDashboard").add("Video", camera).withWidget(BuiltInWidgets.kCameraStream).withSize(10, 4);

    driveBase.reset();
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Left", driveBase.getEncoderLeft());
    SmartDashboard.putNumber("Right", driveBase.getEncoderRight());
    SmartDashboard.putNumber("Avg", driveBase.getEncoderDistance());
    SmartDashboard.putNumber("Distance to Surface:", driveBase.getDistanceToSurface());
    SmartDashboard.putNumber("Gyro Angle", driveBase.getAngle());
    SmartDashboard.putNumber("Base Angle", driveBase.getEncoderAngle());

    angle.setDouble(driveBase.getEncoderAngle());
    encoderDist.setDouble(driveBase.getEncoderDistance());
  }

  @Override
  public void disabledInit() {
    driveBase.reset();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    autonomousCommand = chooser.getSelected();

    if(autonomousCommand != null) {
      autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    driveBase.reset();

    if(autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  ShuffleboardTab pidTab = Shuffleboard.getTab("PID Tuning");
  NetworkTableEntry
    angle = pidTab.add("Turn Angle", 0.0).withWidget(BuiltInWidgets.kGraph).withSize(4, 3).withPosition(0, 0).getEntry(),
    encoderDist = pidTab.add("Encoder Distance", 0.0).withWidget(BuiltInWidgets.kGraph).withSize(4, 3).withPosition(5, 0).getEntry();
  String
    turnPID = pidTab.add("Turn PID", new DriveTurnEncoderPID(0.0)).withWidget(BuiltInWidgets.kPIDCommand).withSize(1, 3).withPosition(4, 0).getTitle(),
    straightPID = pidTab.add("Straight PID", new DriveStraightPID(100)).withWidget(BuiltInWidgets.kPIDCommand).withSize(1, 3).withPosition(9, 0).getTitle();

  @Override
  public void testInit() {
    Robot.driveBase.reset();
    Robot.lift.stop();
    Robot.intake.stop();
  }

  @Override
  public void testPeriodic() {}
}
