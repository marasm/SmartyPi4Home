/**
 * 
 */
package com.marasm.smartyPi4Home.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.marasm.lcd4pi.ButtonPressedObserver;
import com.marasm.lcd4pi.LCD;
import com.marasm.logger.AppLogger;
import com.marasm.smartyPi4Home.menu.MenuController;
import com.marasm.smartyPi4Home.rfdevice.Protocol;
import com.marasm.smartyPi4Home.rfdevice.RfTransmitter;
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
  private static final List<Integer> deviceCodes = Arrays.asList(
    4527411,
    4527420,
    4527555,
    4527564,
    4527875,
    4527884);
  
  
  private static LCD lcd;
  
  private static RfTransmitter transmitter;

  public static void main(String[] args) throws IOException, InterruptedException 
  {
    AppLogger.initLogger("smartyPiLogger");
    AppLogger.debug("Starting SmartyPi4Home");
    
    try
    {
      lcd = LCD.getInstance();
      MenuController menuCtrl = new MenuController(lcd);
      ButtonPressedObserver buttonHandler = new ButtonPressedObserver(lcd);
      Thread buttonCheckerThread = buttonHandler.addButtonListener(
        button -> menuCtrl.handleButtonEvents(button));
      
//      GpioController gpio = GpioFactory.getInstance(); 
//      
//      GpioPinDigitalInput rxDataPin = 
//        gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
//      GpioPinListenerDigital rxStateChangeListener = 
//        e -> 
//      System.out.println("Pin 27 State changed: " + e.getState().getValue());
//      rxDataPin.addListener( rxStateChangeListener );
      
      //TODO tx GPIO == 00
      transmitter = new RfTransmitter(Protocol.ONE);
      for (Integer code: deviceCodes)
      {
        transmitter.sendCode(code.intValue());
        Thread.sleep(2000);
      }
      
      
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
