package frc.robot.commands.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.SleepDriveBase;
import frc.robot.commands.auto.DriveTurnEncoderPID;

public class TurnRoutine extends CommandGroup {

  public TurnRoutine() {
    addSequential(new DriveTurnEncoderPID(90));
    addSequential(new SleepDriveBase(2));
    addSequential(new DriveTurnEncoderPID(-90));
  }
}
