/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.List;

import com.marasm.smartyPi4Home.aws.AwsDevice;
import com.pi4j.io.gpio.Pin;

/**
 * @author mkorotkovas
 *
 */
public abstract class BaseGpioDevice
{
  protected final String id;
  protected final List<Pin> devicePickerPins;
  
  
  protected String deviceState = "OFF";
  protected Pin stateActivationPin = getOffPin();
  
  public BaseGpioDevice(String inId, List<Pin> inDevicePickerPins)
  {
    super();
    id = inId;
    devicePickerPins = inDevicePickerPins;
  }

  protected abstract Pin getOffPin();
  
  public List<Pin> getDevicePickerPins()
  {
    return devicePickerPins;
  }

  public String getId()
  {
    return id;
  }

  public abstract void updateDeviceStateWithAwsData(AwsDevice inAwsUpdates);
  

  public String getDeviceState()
  {
    return deviceState;
  }

  public Pin getStateActivationPin()
  {
    return stateActivationPin;
  }
}
