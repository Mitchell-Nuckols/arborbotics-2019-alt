package frc.robot.hardware;

import edu.wpi.first.wpilibj.Encoder;

public class E4TEncoder extends Encoder {
    public static final int COUNTS_PER_REVOLUTION = 360;

    public E4TEncoder(double distancePerRevolution, int channelA, int channelB, boolean reverseDirection) {
        super(channelA, channelB, reverseDirection, EncodingType.k1X);

        setDistancePerRevolution(distancePerRevolution);
    }

    public E4TEncoder(double distancePerRevolution, int[] channels, boolean reverseDirection) {
        this(distancePerRevolution, channels[0], channels[1], reverseDirection);
    }

    public void setDistancePerRevolution(double distancePerRevolution) {
        this.setDistancePerPulse(distancePerRevolution / COUNTS_PER_REVOLUTION);
    }
}