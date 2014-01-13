package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Timer;

public class DriveSystem implements Runnable {

    Main robot = Main.getInstance();

    private static Gyro gyro;
    
    private static Victor leftFront = new Victor(1);
    private static Victor leftRear = new Victor(2);
    private static Victor rightFront = new Victor(3);
    private static Victor rightRear = new Victor(4);

    private RobotDrive drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);

    static Joystick control = new Joystick(1);

    // constructor
    public DriveSystem() {
        gyro.reset();
    }

    public void run() {

        while (true) {  // start infinite loop to keep thread running

            if (robot.isEnabled()) {

                if (robot.isAutonomous()) {
                    
                    // do some autonomous stuff

                } else if (robot.isOperatorControl()) {

                    if (!robot.catapult.firing) {
                        manualDrive();
                    } else {
                        autoDrive();
                    }
                }

                Timer.delay(.005);
            }
        }
    }

    private void manualDrive() {

        // pythagorean theorem from X and Y joystick values
        double magnitude = Math.sqrt(
                MathUtils.pow(control.getRawAxis(1), 2)
                + MathUtils.pow(control.getRawAxis(2), 2)
        );

        // convert to polar coordinate theta
        double direction = MathUtils.atan2(
                control.getRawAxis(2),
                control.getRawAxis(1)
        );

        // bot rotation from right stick x axis
        double rotation = control.getRawAxis(3);

        drive.mecanumDrive_Polar(magnitude, direction, rotation);

        return;
    }

    private void autoDrive() {

        return;
    }

}
