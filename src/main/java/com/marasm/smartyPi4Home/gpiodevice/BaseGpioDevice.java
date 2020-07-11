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
public abstract class BaseGpioDevice
{
  protected final String id;
  protected final List<Pin> devicePickerPins;
  protected final Pin offPÍin  = RaspiPin.GPIO_00;
  
  public BaseGpioDevice(String inId, List<Pin> inDevicePickerPins)
  {
    super();
    id = inId;
    devicePickerPins = inDevicePickerPins;
  }

  public Pin getOffPÍin()
  {
    return offPÍin;
  }
  public List<Pin> getDevicePickerPins()
  {
    return devicePickerPins;
  }

  public String getId()
  {
    return id;
  }
}
