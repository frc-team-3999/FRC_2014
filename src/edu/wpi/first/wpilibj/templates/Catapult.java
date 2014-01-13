package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Catapult implements Runnable {
    
    Main robot = Main.getInstance();
    boolean firing;

    static Compressor compressor = new Compressor(1,1);
    
    static Joystick control = new Joystick(2);
    
    public Catapult() {
        compressor.start();
    }
    
    public void run() {
        
        while (true) {

            Timer.delay(.005);
        }
        
    }
    
}
