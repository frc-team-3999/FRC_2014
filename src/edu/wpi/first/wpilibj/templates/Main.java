package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;

public class Main extends SimpleRobot {

    public static DStation station;
    public static DriveSystem drive;
    public static Targetting targetting;
    public static Catapult catapult;
    
    public void robotInit() {

        // start communications
        station = new DStation();
        new Thread(station).start();
        
        // initialize targetting
        targetting = new Targetting(this);
        new Thread(targetting).start();
        
        // activate the drive system
        drive = new DriveSystem(this);
        new Thread(drive).start();
        
        // charge up the catapult
        catapult = new Catapult(this);
        new Thread(catapult).start();
        
    }
   
}
