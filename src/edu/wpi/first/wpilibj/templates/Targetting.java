package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Targetting implements Runnable {

    Main robot = Main.getInstance();
    NetworkTable table;

    public Targetting() {
        table = NetworkTable.getTable("SmartDashboard");
        table.putNumber("Range", 0);
        table.putNumber("Angle", 0);
        table.putNumber("Elevation", 0);
    }

    public void run() {

        while (true) {
            Timer.delay(.01);
        }
        

    }

    public double getRange() {
        return table.getNumber("Range");
    }
    
    public double getAngle() {
        return table.getNumber("Angle");
    }
    
    public double getElevation() {
        return table.getNumber("Elevation");
    }
    
    
}
