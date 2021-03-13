/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.List;

import com.marasm.smartyPi4Home.aws.AwsDevice;
import com.marasm.smartyPi4Home.aws.AwsDeviceStatus;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * @author mkorotkovas
 *
 */
public class LightGpioDevice extends BaseGpioDevice
{

  public LightGpioDevice(String inId, List<Pin> inDevicePickerPins)
  {
    super(inId, inDevicePickerPins);
  }

  public Pin getOnPin()
  {
    return RaspiPin.GPIO_11;
  }

  @Override
  protected Pin getOffPin()
  {
    return RaspiPin.GPIO_11;
  }

  @Override
  public void updateDeviceStateWithAwsData(AwsDevice inAwsUpdates)
  {
    deviceState = inAwsUpdates.getStatus().toString();
    if(inAwsUpdates.getStatus() == AwsDeviceStatus.ON)
    {
      stateActivationPin = getOnPin();
    }
    else
    {
      stateActivationPin = getOffPin();
    }
  }
  
}
