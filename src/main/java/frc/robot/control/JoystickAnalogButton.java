package frc.robot.control;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickAnalogButton extends Button {

    public double deadband = 0.1;

    public GenericHID hid;
    public int analogAxis;

    public JoystickAnalogButton(GenericHID hid, int analogAxis) {
        this.hid = hid;
        this.analogAxis = analogAxis;
    }

    public JoystickAnalogButton(GenericHID hid, int analogAxis, double deadband) {
        this(hid, analogAxis);
        this.deadband = deadband;
    }

    @Override
    public boolean get() {
        return Math.abs(hid.getRawAxis(analogAxis)) > deadband;
    }
}
