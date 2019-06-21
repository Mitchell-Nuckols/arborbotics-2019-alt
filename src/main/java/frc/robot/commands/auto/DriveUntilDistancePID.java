package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;
import frc.robot.hardware.MB1013Ultrasonic;

public class DriveUntilDistancePID extends PIDCommand {

  private static final double P = 0.4, I = 0.0, D = 0.2;

  private double distance;

  public DriveUntilDistancePID(double distance) {
    super("drive_until_distance_pid", P, I, D);
    requires(Robot.driveBase);

    getPIDController().setOutputRange(-0.4, 0.4);
    getPIDController().setInputRange(MB1013Ultrasonic.MIN_DIST, MB1013Ultrasonic.MAX_DIST);
    getPIDController().setAbsoluteTolerance(0.05);
    getPIDController().setContinuous(true);

    this.distance = distance;
  }

  @Override
  protected void initialize() {
    Robot.driveBase.reset();
    setSetpoint(distance);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return getPIDController().onTarget();
  }

  @Override
  protected void end() {
    Robot.driveBase.stop();
  }

  @Override
  protected double returnPIDInput() {
    return Robot.driveBase.getDistanceToSurface();
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.driveBase.tankDrive(-output, -output);
  }
}
