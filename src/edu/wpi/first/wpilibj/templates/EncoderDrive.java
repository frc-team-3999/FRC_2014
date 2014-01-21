
package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author joseph
 */
public class EncoderDrive implements Runnable {
    
    Main robot; 
    boolean cocking;
    
   private final SpeedController Single = new Victor(5);   
   
    private final Encoder SingleEncoder = new Encoder(1,2);
    
    
        //Starts the encoders
              
        Controller control;
       
         public EncoderDrive(Main robot) {
        this.robot = robot;        
        control = new Controller(3);      
        SingleEncoder.start();
        int count = SingleEncoder.get ();
        System.out.println(SingleEncoder.get());

}
                             
          public void run() {
        
        new Thread(control).start();
         while (true) {

            while (200 >= SingleEncoder.get()){
             if (control.button[1]) {
                cocking = true;
                Single.set (1);           
            }
         }
            
         while (0 < SingleEncoder.get()){   
            if (control.button[3]) {
                cocking = false;
                Single.set (-1);

            } 
         }   
             Timer.delay(.1);
        }
    }
}
