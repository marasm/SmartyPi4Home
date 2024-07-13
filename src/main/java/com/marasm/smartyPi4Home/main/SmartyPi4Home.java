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
      AppLogger.debug("LCD Initialized");
      
      GpioDeviceController deviceController;
      if(LCD.isRunningOnPi())
      {
        AppLogger.debug("Detected that we are running on PI. About to init GPIO controller.");
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.DEFAULT_PIN_NUMBERING));
        deviceController = new GpioDeviceController(GpioFactory.getInstance());
      }
      else
      {
        deviceController = new GpioDeviceController(new LoggingGpioController());
      }
      AppLogger.debug("GPIO controller initialized.");
      
      
      AppLogger.debug("About to connect to AWS");
      lcd.clear();
      lcd.setText("Connecting to\nAWS...");
      
      awsDeviceController = new AwsDeviceController();
      awsDeviceController.connectPhysicalDevices(deviceController);
      AppLogger.debug("AWS Connection success.");

      AppLogger.debug("Connection to MQTT");
      mqttDeviceController = new MqttDeviceController();
      mqttDeviceController.connectPhysicalDevices(deviceController);
      AppLogger.debug("MQTT Connection success.");
      
      AppLogger.debug("Initializing the menu");
      MenuController menuCtrl = new MenuController(lcd, deviceController);
      ButtonPressedObserver buttonHandler = new ButtonPressedObserver(lcd);
      Thread buttonCheckerThread = buttonHandler.addButtonListener(
        button -> menuCtrl.handleButtonEvents(button));
      AppLogger.debug("Menu init complete.");
      
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
        AppLogger.debug("Joining button checker thread.");
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
