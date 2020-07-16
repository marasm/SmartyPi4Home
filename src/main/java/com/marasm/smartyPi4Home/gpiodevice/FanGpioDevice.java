/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.Arrays;
import java.util.List;

import com.marasm.smartyPi4Home.aws.AwsDevice;
import com.marasm.smartyPi4Home.aws.AwsDeviceStatus;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * @author mkorotkovas
 *
 */
public class FanGpioDevice extends BaseGpioDevice
{
  private final List<Pin> modePins = Arrays.asList(RaspiPin.GPIO_00, 
                                                   RaspiPin.GPIO_02, 
                                                   RaspiPin.GPIO_03, 
                                                   RaspiPin.GPIO_12, 
                                                   RaspiPin.GPIO_13, 
                                                   RaspiPin.GPIO_14);
  
  
  public FanGpioDevice(String inId, List<Pin> inDevicePickerPins)
  {
    super(inId, inDevicePickerPins);
  }

  public List<Pin> getModePins()
  {
    return modePins;
  }

  @Override
  public void updateDeviceStateWithAwsData(AwsDevice inAwsUpdates)
  {
    if (inAwsUpdates.getStatus() == AwsDeviceStatus.ON && inAwsUpdates.getMode() > 0)
    {
      deviceState = "ON";
      if (inAwsUpdates.getMode() == 0)
      {
        stateActivationPin = modePins.get(2);
        deviceState += "\nLevel 3";
      }
      else
      {
        int requestedLevel = inAwsUpdates.getMode();
        if (requestedLevel > 6) requestedLevel = 6;
        stateActivationPin = modePins.get(requestedLevel - 1);
        deviceState += "\nLevel " + requestedLevel + "%";
      }
    }
    else
    {
      stateActivationPin = getOffPin();
      deviceState = "OFF";
    }
  }

  @Override
  protected Pin getOffPin()
  {
    return RaspiPin.GPIO_07;
  }


  
}
