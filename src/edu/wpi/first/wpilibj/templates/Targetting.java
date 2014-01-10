package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Targetting implements Runnable {
    
    Main robot = Main.getInstance();
    NetworkTable server;
    
    public Targetting(Main robot) {
        server = NetworkTable.getTable("SmartDashboard");
    }
    
    public void run() {
        
        double range;
        while (true) {
            range = server.getNumber("range");
        }
        
    }
}
