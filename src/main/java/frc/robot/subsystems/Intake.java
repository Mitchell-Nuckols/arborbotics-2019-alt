package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Intake extends Subsystem {

  private Spark
    leftSpark = new Spark(RobotMap.INTAKE_LEFT),
    rightSpark = new Spark(RobotMap.INTAKE_RIGHT);

  public Intake() {
    super("intake");
  }

  public void stop() {
    leftSpark.stopMotor();
    rightSpark.stopMotor();
  }

  public void in() {
    leftSpark.set(RobotMap.INTAKE_WHEEL_SPEED_IN);
    rightSpark.set(RobotMap.INTAKE_WHEEL_SPEED_IN);
  }

  public void out() {
    leftSpark.set(-RobotMap.INTAKE_WHEEL_SPEED_OUT);
    rightSpark.set(-RobotMap.INTAKE_WHEEL_SPEED_OUT);
  }

  @Override
  public void initDefaultCommand() {
  }
}
