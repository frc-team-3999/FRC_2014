package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Main extends SimpleRobot {

    Joystick driverPad = new Joystick(1);
    DStation station;
    DriveSystem drive;
    
    public void robotInit() {

        // start the driver station communications
        station = new DStation();
        new Thread(station).start();
        
        // activate the drive system
        drive = new DriveSystem(this,driverPad);
        new Thread(drive).start();
        
    }
   
}
