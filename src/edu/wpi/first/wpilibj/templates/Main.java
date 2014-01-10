package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;

public class Main extends SimpleRobot {

    DStation station;
    DriveSystem drive;
    
    public void robotInit() {

        // start the driver station communications
        station = new DStation();
        new Thread(station).start();
        
        // activate the drive system
        drive = new DriveSystem(this);
        new Thread(drive).start();
        
    }
   
}
