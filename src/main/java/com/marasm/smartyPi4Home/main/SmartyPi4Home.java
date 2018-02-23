/**
 * 
 */
package com.marasm.smartyPi4Home.main;

import java.io.IOException;

import com.marasm.lcd4pi.ButtonPressedObserver;
import com.marasm.lcd4pi.LCD;
import com.marasm.logger.AppLogger;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * @author mkorotkovas
 *
 */
public class SmartyPi4Home
{
  
  private static LCD lcd;

  public static void main(String[] args) throws IOException, InterruptedException 
  {
    AppLogger.initLogger("smartyPiLogger");
    AppLogger.debug("Starting SmartyPi4Home");
    
    try
    {
      lcd = LCD.getInstance();
      lcd.clear();
      lcd.setText("SmartyPi4Home\nv0.1");
      Thread.sleep(1000);
      
      ButtonPressedObserver buttonHandler = new ButtonPressedObserver(lcd);
      Thread buttonCheckerThread = buttonHandler.addButtonListener(button -> 
      System.out.println("Button: " + button.getPin()));
      
      GpioController gpio = GpioFactory.getInstance(); 
      
      GpioPinDigitalInput rxDataPin = 
        gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
      GpioPinListenerDigital rxStateChangeListener = 
        e -> 
      System.out.println("Pin 27 State changed: " + e.getState().getValue());
      rxDataPin.addListener( rxStateChangeListener );
      
      //TODO tx GPIO == 00
      
      Runtime.getRuntime().addShutdownHook(
        new Thread(() -> 
        {
          System.out.println("Shutting down...");
          lcd.stop();
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
      lcd.stop();
    }
  }
  
}
