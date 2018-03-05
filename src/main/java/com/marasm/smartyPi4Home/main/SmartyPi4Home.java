/**
 * 
 */
package com.marasm.smartyPi4Home.main;

import java.io.IOException;

import com.marasm.lcd4pi.ButtonPressedObserver;
import com.marasm.lcd4pi.LCD;
import com.marasm.logger.AppLogger;
import com.marasm.smartyPi4Home.aws.AwsDeviceController;
import com.marasm.smartyPi4Home.menu.MenuController;
import com.marasm.smartyPi4Home.rfdevice.DeviceController;
import com.marasm.smartyPi4Home.rfdevice.RfTransmitter;

/**
 * @author mkorotkovas
 *
 */
public class SmartyPi4Home
{
  private static LCD lcd;
  private static AwsDeviceController awsDeviceController;
  

  public static void main(String[] args) throws IOException, InterruptedException 
  {
    AppLogger.initLogger("smartyPiLogger");
    AppLogger.debug("Starting SmartyPi4Home");
    
    try
    {
      lcd = LCD.getInstance();
      
      RfTransmitter transmitter = new RfTransmitter();
      DeviceController deviceController = new DeviceController(transmitter);
      
      lcd.clear();
      lcd.setText("Connecting to\nAWS...");
      
      awsDeviceController = new AwsDeviceController();
      awsDeviceController.connectPhysicalDevices(deviceController);
      
      MenuController menuCtrl = new MenuController(lcd, deviceController);
      ButtonPressedObserver buttonHandler = new ButtonPressedObserver(lcd);
      Thread buttonCheckerThread = buttonHandler.addButtonListener(
        button -> menuCtrl.handleButtonEvents(button));
      
      Runtime.getRuntime().addShutdownHook(
        new Thread(() -> 
        {
          System.out.println("Shutting down...");
          lcd.clear();
          lcd.stop();
          awsDeviceController.diconnect();
        }
        ));
      
      //wait 
      buttonCheckerThread.join();
      

      
    }
    catch (Exception e)
    {
      AppLogger.error("System Error: " + e.getMessage() + "\nStopping app...", e);
      lcd.clear();
      lcd.setText("System Error :(\nShutting down");
      Thread.sleep(5000);
      System.exit(1);
    }
    finally
    {
      awsDeviceController.diconnect();
      lcd.stop();
    }
  }
  
}
