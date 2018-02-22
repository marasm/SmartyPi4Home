/**
 * 
 */
package com.marasm.smartyPi4Home.main;

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
  
  public static void main(String[] args) 
  {
    AppLogger.initLogger("smartyPiLogger");
    AppLogger.debug("Starting SmartyPi4Home");
    
    LCD lcd = null;
    try
    {
      lcd = LCD.getInstance();
      lcd.clear();
      lcd.setText("SmartyPi4Home\nv0.1");
      
      ButtonPressedObserver buttonHandler = new ButtonPressedObserver(lcd);
      buttonHandler.addButtonListener(button -> 
        System.out.println("Button: " + button.getPin()));
      
      
      GpioController gpio = GpioFactory.getInstance(); 
      
      GpioPinDigitalInput rxDataPin = 
        gpio.provisionDigitalInputPin(RaspiPin.GPIO_27, PinPullResistance.PULL_DOWN);
      GpioPinListenerDigital rxStateChangeListener = e -> 
          System.out.println("Pin 27 State changed: " + e.getState().getValue());
      rxDataPin.addListener( rxStateChangeListener );
      
    }
    catch (Exception e)
    {
      AppLogger.error("System Error: " + e.getMessage() + "\nStopping app...", e);
      System.exit(1);
    }
  }
  
}
