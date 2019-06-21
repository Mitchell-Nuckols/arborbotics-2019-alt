package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveUntilDistance extends Command {

  private double distance, speed;

  public DriveUntilDistance(double distance) {
    this(distance, 0.5);
  }

  public DriveUntilDistance(double distance, double speed) {
    super("drive_until_distance");
    
    requires(Robot.driveBase);

    this.distance = distance;
    this.speed = speed;
  }

  @Override
  protected void initialize() {
    Robot.driveBase.reset();
  }

  @Override
  protected void execute() {
    Robot.driveBase.tankDrive(speed, speed);
  }

  @Override
  protected boolean isFinished() {
    return Robot.driveBase.getDistanceToSurface() <= distance;
  }

  @Override
  protected void end() {
    Robot.driveBase.stop();
  }
}
