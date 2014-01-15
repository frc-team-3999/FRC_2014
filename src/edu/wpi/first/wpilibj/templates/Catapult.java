package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

public class Catapult implements Runnable {
    
    Main robot;
    boolean firing;

    private static final Compressor compressor = new Compressor(1,1);
    private static final DoubleSolenoid solenoid = new DoubleSolenoid(1,2);
    
    static Joystick control = new Joystick(2);
    
    public Catapult(Main robot) {
        this.robot = robot;
        compressor.start();
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void run() {
        
        while (true) {

            if (control.getRawButton(8)) {
                solenoid.set(DoubleSolenoid.Value.kForward);
            }
            Timer.delay(.005);
        }
        
    }
    
}
