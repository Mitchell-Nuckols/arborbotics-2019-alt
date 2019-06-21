package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class SleepDriveBase extends TimedCommand {

  public SleepDriveBase(double timeout) {
    super(timeout);
    requires(Robot.driveBase);
  }

  public void execute() {
    Robot.driveBase.stop();
  }
}
