package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeUp extends Command {
  public IntakeUp() {
    super("intake_up");
    requires(Robot.lift);
  }

  @Override
  protected void execute() {
    Robot.lift.intakeUp();
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
