package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Catapult implements Runnable {
    
    Main robot;
    boolean firing;

    private static final Compressor compressor = new Compressor(1,1);
    private static final DoubleSolenoid solenoid = new DoubleSolenoid(1,2);
    
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

            if (control.button[8]) {
                solenoid.set(DoubleSolenoid.Value.kForward);
            }
        } 
    }
}
