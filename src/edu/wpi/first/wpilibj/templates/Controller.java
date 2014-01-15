package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Controller extends Joystick implements Runnable {

    Joystick controller;

    public boolean button[] = new boolean[13];

    public Controller(int port) {
        super(port);
    }

    public void run() {

        while (true) {
            for (int i = 1; i <= 12; i++) {
                button[i] = controller.getRawButton(i);
            }   
            Timer.delay(.005);
        }
    }
}
