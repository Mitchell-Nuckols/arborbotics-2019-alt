package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Lift extends Subsystem {

  private WPI_VictorSPX
    liftVictor = new WPI_VictorSPX(RobotMap.LIFT_VICTOR);
  private WPI_TalonSRX
    intakeTalon = new WPI_TalonSRX(RobotMap.INTAKE_TALON);

  public Lift() {
    super("lift");

    liftVictor.configFactoryDefault();
    liftVictor.setNeutralMode(NeutralMode.Brake);
    
    intakeTalon.configFactoryDefault();
    intakeTalon.setNeutralMode(NeutralMode.Brake);
  }

  public void stop() {
    liftVictor.stopMotor();
    intakeTalon.stopMotor();
  }

  public void up() {
    liftVictor.set(RobotMap.LIFT_SPEED);
  }

  public void down() {
    liftVictor.set(-RobotMap.LIFT_SPEED);
  }

  public void intakeUp() {
    intakeTalon.set(RobotMap.INTAKE_JOINT_SPEED);
  }

  public void intakeDown() {
    intakeTalon.set(-RobotMap.INTAKE_JOINT_SPEED);
  }

  @Override
  protected void initDefaultCommand() {}
}
