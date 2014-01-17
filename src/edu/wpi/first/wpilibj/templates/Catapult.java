package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

public class Catapult implements Runnable {
    
    Main robot;
    boolean firing;

    private static final Compressor compressor = new Compressor(1,1);
    DoubleSolenoid solenoid = new DoubleSolenoid(1,2);
    
    Controller control;
    
    public Catapult(Main robot) {
        this.robot = robot;        
        control = new Controller(2);      
        compressor.start();
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void run() {
        
        new Thread(control).start();
        
        while (true) {

            if (control.button[4]) {
                firing = true;
                solenoid.set(DoubleSolenoid.Value.kForward);
                do {
                    Timer.delay(.5);
                } while (control.button[4]);
            }
            
            if (control.button[2]) {
                firing = false;
                solenoid.set(DoubleSolenoid.Value.kReverse);
                do {
                    Timer.delay(.5);
                } while (control.button[2]);
            }
            
            Timer.delay(.1);
        } 
    }
}
