package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeDown extends Command {
  public IntakeDown() {
    super("intake_down");
    requires(Robot.lift);
  }

  @Override
  protected void execute() {
    Robot.lift.intakeDown();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.lift.stop();
  }
}
