package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;

public class Catapult implements Runnable {
    
    //private final Main robot;
    public boolean firing;

    // compressor on digital 1 and relay 1
    private static final Compressor compressor = new Compressor(1,1);
    private static final DoubleSolenoid solenoid = new DoubleSolenoid(1,2);
    
    private static final Talon cockingMotor = new Talon(5);
    private static final Encoder strap = new Encoder(2,3);
    
    private final Controller control;
    
    public Catapult() {
        //this.robot = robot;        
        control = new Controller(2);      
        compressor.start();
        solenoid.set(DoubleSolenoid.Value.kForward);
        strap.setReverseDirection(true);
        //strap.setSamplesToAverage(5);
    }
    
    public void run() {
        
        new Thread(control).start();

        while (true) {

            if (control.button[4]) {
                firing = true;
                solenoid.set(DoubleSolenoid.Value.kReverse);
                do {
                    Timer.delay(.5);
                } while (control.button[4]);
            }
            
            if (control.button[2]) {
                firing = false;
                cock();
                do {
                    Timer.delay(.5);
                } while (control.button[2]);
            }
            
            Timer.delay(.1);
        } 
    }
    
    public void cock() {
        strap.start();
        strap.reset();
        while (strap.get() < 100) {
            Main.station.toLCDLine(4, "Catapult Encoder " + strap.get());
            cockingMotor.set(.1);
        }
        cockingMotor.stopMotor();
        solenoid.set(DoubleSolenoid.Value.kForward);
        Timer.delay(1);
        while (strap.get() > 0) {
            Main.station.toLCDLine(4, "Catapult Encoder " + strap.get());
            cockingMotor.set(-.1);
        }
        cockingMotor.stopMotor();
        strap.stop();
    }
}
