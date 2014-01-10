package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Timer;

public class DriveSystem implements Runnable {

    Joystick control = new Joystick(1);
    Main robot = Main.getInstance();

    Victor leftFront = new Victor(1);
    Victor leftRear = new Victor(2);
    Victor rightFront = new Victor(3);
    Victor rightRear = new Victor(4);
    
    RobotDrive drive = new RobotDrive(leftFront,leftRear,rightFront,rightRear);
    
    // constructor
    public DriveSystem() {
    }
    
    public void run() {
       
        while (true) {  // start infinite loop to keep thread running
            
            // pythagorean theorem from X and Y joystick values
            double magnitude = Math.sqrt(
                    MathUtils.pow(control.getRawAxis(1),2)
                    +
                    MathUtils.pow(control.getRawAxis(2),2)
                );

            // convert to polar coordinate theta
            double direction = MathUtils.atan2(
                    control.getRawAxis(2), 
                    control.getRawAxis(1)
                );
            
            // bot rotation from right stick x axis
            double rotation = control.getRawAxis(3);
            
            if ( robot.isEnabled() && robot.isOperatorControl() ) {
                drive.mecanumDrive_Polar(magnitude, direction, rotation);
            }
            
            Timer.delay(.005);
        }
    }
    
}
