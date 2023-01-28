/**
 * 
 */
package com.marasm.smartyPi4Home.main;

import java.io.IOException;

import com.marasm.lcd4pi.ButtonPressedObserver;
import com.marasm.lcd4pi.LCD;
import com.marasm.logger.AppLogger;
import com.marasm.smartyPi4Home.gpiodevice.GpioDeviceController;
import com.marasm.smartyPi4Home.gpiodevice.LoggingGpioController;
import com.marasm.smartyPi4Home.menu.MenuController;
import com.marasm.smartyPi4Home.mqtt.MqttDeviceController;
import com.marasm.smartyPi4Home.aws.AwsDeviceController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;

/**
 * @author mkorotkovas
 *
 */
public class SmartyPi4Home
{
  private static LCD lcd;
  private static AwsDeviceController awsDeviceController;
  private static MqttDeviceController mqttDeviceController;
  

  public static void main(String[] args) throws IOException, InterruptedException 
  {
    AppLogger.initLogger("smartyPiLogger");
    AppLogger.debug("Starting SmartyPi4Home");
    
    try
    {
      lcd = LCD.getInstance();
      
      GpioDeviceController deviceController;
      if(LCD.isRunningOnPi())
      {
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.DEFAULT_PIN_NUMBERING));
        deviceController = new GpioDeviceController(GpioFactory.getInstance());
      }
      else
      {
        deviceController = new GpioDeviceController(new LoggingGpioController());
      }
      
      
      lcd.clear();
      lcd.setText("Connecting to\nAWS...");
      
      awsDeviceController = new AwsDeviceController();
      awsDeviceController.connectPhysicalDevices(deviceController);

      mqttDeviceController = new MqttDeviceController();
      mqttDeviceController.connectPhysicalDevices(deviceController);
      
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
          mqttDeviceController.diconnect();
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
        mqttDeviceController.diconnect();
        lcd.stop(); 
      }
  }
  
}
