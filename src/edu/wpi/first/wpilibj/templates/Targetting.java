package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Targetting implements Runnable {

    Main robot;
    NetworkTable table;

    public Targetting(Main robot) {
        this.robot = robot;
        table = NetworkTable.getTable("SmartDashboard");
        table.putNumber("Range", 0);
        table.putNumber("Angle", 0);
    }

    public void run() {

        while (true) {
            Main.station.toLCDLine(1, "Range " + getRange());
            Main.station.toLCDLine(2, "Angle " + getAngle());
            Timer.delay(.01);
        }
        

    }

    public double getRange() {
        return table.getNumber("Range");
    }
    
    public double getAngle() {
        return table.getNumber("Angle");
    }

}
