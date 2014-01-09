package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Targetting implements Runnable {
    
    Main robot;
    NetworkTable cameraTable;
    
    public Targetting(Main robot) {
        this.robot = robot;
        cameraTable = NetworkTable.getTable("camera");
    }
    
    public void run() {
        
        double range;
        while (true) {
            range = cameraTable.getNumber("range");
            robot.station.sendToLCD("Range = " + range);
        }
        
    }
}
