/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.List;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * @author mkorotkovas
 *
 */
public class LightGpioDevice extends BaseGpioDevice
{
  
  private final Pin onPin = RaspiPin.GPIO_00;


  public LightGpioDevice(String inId, List<Pin> inDevicePickerPins)
  {
    super(inId, inDevicePickerPins);
  }


  public Pin getOnPin()
  {
    return onPin;
  }

  
}
