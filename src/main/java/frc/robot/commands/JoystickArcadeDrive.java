package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class JoystickArcadeDrive extends Command {
  public JoystickArcadeDrive() {
    super("joystick_arcade_drive");

    requires(Robot.driveBase);
  }

  @Override
  protected void initialize() {
    Robot.driveBase.stop();
    Robot.driveBase.reset();
  }

  @Override
  protected void execute() {
    Robot.driveBase.arcadeDrive(Robot.oi.getSpeed(), Robot.oi.getRotation(), false);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.driveBase.stop();
  }
}
