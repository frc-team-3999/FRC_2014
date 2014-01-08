package edu.wpi.first.wpilibj.templates;

/**
 * @author William Dell
 * @author Team 3999
 * @version v7, January 7, 2014
 * @version FRC Java version 2013.589
 */
import edu.wpi.first.wpilibj.*;

/**
 * The routines for dealing with the driver station are ridiculously complicated
 * and cryptic. Hopefully this class will simplify the process by replacing them
 * with shorter, more obvious methods.
 */
public class DStation implements Runnable {

    // going to treat the LCD as a stack, in reverse here because
    // new lines appear on the bottom and scroll upwards (mostly)
    DriverStationLCD.Line[] lcdLines = {
        DriverStationLCD.Line.kUser6, // bottom line
        DriverStationLCD.Line.kUser5,
        DriverStationLCD.Line.kUser4,
        DriverStationLCD.Line.kUser3,
        DriverStationLCD.Line.kUser2,
        DriverStationLCD.Line.kUser1// top line
    };
    String[] lcdHistory = new String[6];  // array to scroll lcd display
    String clearLine = "                     "; // 21 spaces to clear a line

    // empty constructor
    public DStation() {
    } // end constructor

    /**
     * When run, the class starts a continuous loop to update the dashboard
     * constantly. This is probably a bad way to do it.
     */
    public void run() {

        // caution, infinite loop, this could be bad....
        while (true) {
            this.updateDashboard();
        } // end while
        // but it actually works!

    } // end method run()

    /**
     * Messages to the LCD must be 21 characters long or you get artifacts from
     * previous lines. This should pad the desired message out to the proper
     * length.
     *
     * @param msg the message to be padded out
     * @return line the message line padded out to 21 characters
     */
    private String padMsg(String msg) {
        String line = msg;
        if (line.length() < 21) {  // if the message is already 21 or more do nothing
            // otherwise tack on however many spaces we are short
            for (int i = msg.length() + 1; i <= 21; i++) {
                line += " ";
            } // end for
        } // end if
        line = line.substring(0, 21); // make sure we are only 21 long
        return line; // and return the new, padded out line

    } // end method padMsg()

    /**
     * Does just what it says, clears the driver station LCD.
     */
    public void clearLCD() {
        for (int i = 0; i < 6; i++) {
            lcdHistory[i] = clearLine; // clear the lcd "memory"
            // and then use the cleared memory to clear the actual display
            DriverStationLCD.getInstance().println(lcdLines[i], 1, lcdHistory[i]);
        } // end for
        DriverStationLCD.getInstance().updateLCD();  // display the screen

    } // end method clearLCD()

    /**
     * Simplified messages to the LCD, with scrolling; new messages appear at
     * the bottom of the screen and scroll up.
     *
     * @param msg the message to be sent to the screen
     */
    public void sendToLCD(String msg) {

        // scroll down the history;  iterate backwards!
        for (int i = 5; i > 0; i--) {
            lcdHistory[i] = clearLine;
            lcdHistory[i] = lcdHistory[i - 1];
        } // end for
        lcdHistory[0] = clearLine; // clear the bottom line of the screen
        lcdHistory[0] = padMsg(msg); // insert new line at the bottom, padded out to 21 chars

        // set up updated screen by rewriting all lines
        for (int i = 0; i < 6; i++) {
            DriverStationLCD.getInstance().println(lcdLines[i], 1, lcdHistory[i]);
        } // end for
        DriverStationLCD.getInstance().updateLCD(); // display the screen

    } // end method sendToLCD()

    /**
     * This makes it easier to send message to a specific line. Takes the
     * desired line number (top to bottom, 1-6) and the message to print as
     * arguments.
     *
     * @param line which line of the LCD the message should be displayed on
     * @param msg the message to be displayed
     */
    public void toLCDLine(int line, String msg) {

        int index = 6 - line; // array is set up bottom to top, this reverses it
        DriverStationLCD.getInstance().println(lcdLines[index], 1, padMsg(msg));
        DriverStationLCD.getInstance().updateLCD(); // keep forgetting this, d'oh!

    } // end method toLCDLine()

    /*
     * From here down it's stolen from DashboardExampleProject, and I'm not sure
     * how it works. Looks like this mess should be replaceable with
     * SmartDashboard, but as usual the documentation sucks. I've started
     * deciphering what all this means, but for now it just works.
     */
    void updateDashboard() {
        Dashboard lowDashData = DriverStation.getInstance().getDashboardPackerLow();
        lowDashData.addCluster();
        {
            lowDashData.addCluster();
            {     //analog modules
                lowDashData.addCluster();
                {
                    for (int i = 1; i <= 8; i++) {
                        lowDashData.addFloat((float) AnalogModule.getInstance(1).getAverageVoltage(i));
                    }
                }
                lowDashData.finalizeCluster();
                lowDashData.addCluster();
                {
                    for (int i = 1; i <= 8; i++) {
                        lowDashData.addFloat(0);
                        //lowDashData.addFloat((float) AnalogModule.getInstance(2).getAverageVoltage(i));
                    }
                }
                lowDashData.finalizeCluster();
            }
            lowDashData.finalizeCluster();

            lowDashData.addCluster();
            { //digital modules
                lowDashData.addCluster();
                {
                    lowDashData.addCluster();
                    {
                        int module = 1;
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
                        lowDashData.addCluster();
                        {
                            for (int i = 1; i <= 10; i++) {
                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                            }
                        }
                        lowDashData.finalizeCluster();
                    }
                    lowDashData.finalizeCluster();
                }
                lowDashData.finalizeCluster();

                lowDashData.addCluster();
                {
                    lowDashData.addCluster();
                    {
                        /*
                         * ok, this thing is written for two digital modules. to
                         * make it work, I've basically told it to read module 1
                         * twice. If you have more than one digital module (for
                         * a second digital sidecar), change the module number
                         * below to "2"
                         */
                        int module = 1;  // yeah, this number
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayReverse());
                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
                        lowDashData.addCluster();
                        {
                            for (int i = 1; i <= 10; i++) {
                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                            }
                        }
                        lowDashData.finalizeCluster();
                    }
                    lowDashData.finalizeCluster();
                }

                lowDashData.finalizeCluster();

            }
            lowDashData.finalizeCluster();

            // if you have a solenoid module uncomment the line below
            //lowDashData.addByte(Solenoid.getAllFromDefaultModule());
            // and comment out the next two lines
            byte thisBytes = 0;
            lowDashData.addByte(thisBytes);
        }
        lowDashData.finalizeCluster();
        lowDashData.commit();
    }
} // end class DStation
