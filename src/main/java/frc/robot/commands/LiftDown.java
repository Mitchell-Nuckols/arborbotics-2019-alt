package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftDown extends Command {
  public LiftDown() {
    super("lift_down");
    requires(Robot.lift);
  }

  @Override
  protected void execute() {
    Robot.lift.down();
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
