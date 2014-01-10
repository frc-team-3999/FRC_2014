package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;

public class Main extends SimpleRobot {

    DStation station;
    DriveSystem drive;

    private static Main instance = null;
    protected Main() {}
    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }
    
    public void robotInit() {

        // start the driver station communications
        station = new DStation();
        new Thread(station).start();
        
        // activate the drive system
        drive = new DriveSystem();
        new Thread(drive).start();
        
    }
   
}
