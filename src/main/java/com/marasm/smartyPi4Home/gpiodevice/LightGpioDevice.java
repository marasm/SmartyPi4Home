/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.List;
import java.util.Map;

import com.marasm.logger.AppLogger;
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


  public LightGpioDevice(String id, String description, String type, List<Pin> devicePickerPins)
  {
    super(id, description, type, devicePickerPins);
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
  
  @Override
  public void updateDeviceStateWithMqttData(String inCommandMap)
  {
    if(inCommandMap != null ) 
    {
      deviceState = inCommandMap;
      AppLogger.warn("got state update from MQTT: " + deviceState);
      if("ON".equalsIgnoreCase(deviceState))
      {
        stateActivationPin = getOnPin();
      }
      else
      {
        stateActivationPin = getOffPin();
      }
    }
    else
    {
      AppLogger.warn("got a an empty command object from MQTT - ignoring");
    }

  }

  @Override
  public Map<String, String> getDeviceMqttConfigData()
  {
    Map<String, String> config = super.getDeviceMqttConfigData();
    //TODO add light specific config here
    return config;
  }

  
}
