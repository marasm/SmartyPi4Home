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
public class FanGpioDevice extends BaseGpioDevice
{
  private final Pin mode1Pin = RaspiPin.GPIO_00;
  private final Pin mode2Pin = RaspiPin.GPIO_00;
  private final Pin mode3Pin = RaspiPin.GPIO_00;
  private final Pin mode4Pin = RaspiPin.GPIO_00;
  private final Pin mode5Pin = RaspiPin.GPIO_00;
  private final Pin mode6Pin = RaspiPin.GPIO_00;
  
  public FanGpioDevice(String inId, List<Pin> inDevicePickerPins)
  {
    super(inId, inDevicePickerPins);
  }

  public Pin getMode1Pin()
  {
    return mode1Pin;
  }

  public Pin getMode2Pin()
  {
    return mode2Pin;
  }

  public Pin getMode3Pin()
  {
    return mode3Pin;
  }

  public Pin getMode4Pin()
  {
    return mode4Pin;
  }

  public Pin getMode5Pin()
  {
    return mode5Pin;
  }

  public Pin getMode6Pin()
  {
    return mode6Pin;
  }
  
}
