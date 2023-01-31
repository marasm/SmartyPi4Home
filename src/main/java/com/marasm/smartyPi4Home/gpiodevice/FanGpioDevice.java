/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.marasm.smartyPi4Home.aws.AwsDeviceStatus;
import com.marasm.logger.AppLogger;
import com.marasm.smartyPi4Home.aws.AwsDevice;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * @author mkorotkovas
 *
 */
public class FanGpioDevice extends BaseGpioDevice
{
  private String mode = "0";


  private final List<Pin> modePins = Arrays.asList(RaspiPin.GPIO_00, 
                                                   RaspiPin.GPIO_02, 
                                                   RaspiPin.GPIO_03, 
                                                   RaspiPin.GPIO_12, 
                                                   RaspiPin.GPIO_13, 
                                                   RaspiPin.GPIO_14);
  
  public FanGpioDevice(String id, String description, String type, List<Pin> devicePickerPins)
  {
    super(id, description, type, devicePickerPins);
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
        deviceState += "\nMode 3";
      }
      else
      {
        int requestedMode = inAwsUpdates.getMode();
        if (requestedMode > 6) requestedMode = 6;
        stateActivationPin = modePins.get(requestedMode - 1);
        deviceState += "\nMode " + requestedMode;
      }
    }
    else
    {
      stateActivationPin = getOffPin();
      deviceState = "OFF";
    }
  }

  public String getMode()
  {
    return mode;
  }


  public void setMode(String mode)
  {
    this.mode = mode;
  }

  @Override
  protected Pin getOffPin()
  {
    return RaspiPin.GPIO_07;
  }

  @Override
  public void updateDeviceStateWithMqttData(String inCommand)
  {
    if (inCommand != null)
    {
      AppLogger.debug("fan got an command obj: " + inCommand);
      if ("ON".equalsIgnoreCase(inCommand))
      {
        deviceState = "ON";
        //set to medium speed (3)
        stateActivationPin = modePins.get(2);
        mode = "3";
      }
      else if ("OFF".equalsIgnoreCase(inCommand))
      {
        deviceState = "OFF";
        stateActivationPin = getOffPin();
        mode = "0";
      }
      //percentage command contains just the speed value (not JSON)
      else
      {
        int requestedSpeed = Integer.parseInt(inCommand);
        if (requestedSpeed == 0)
        {
          stateActivationPin = getOffPin();
          deviceState = "OFF";
          mode = "0";
        }
        else
        {
          if(requestedSpeed > 6) requestedSpeed = 6;
          stateActivationPin = modePins.get(requestedSpeed - 1);
          deviceState = "ON";
          mode = String.valueOf(requestedSpeed);
        }
      }
    }
    else
    {
      AppLogger.warn("fan got an empty command obj - ignoring");
    }
  }

  @Override
  public Map<String, String> getDeviceMqttConfigData()
  {
    Map<String, String> config = super.getDeviceMqttConfigData();
    config.put("percentage_state_topic", getBaseTopicForDevice() + "/percentage_state");
    config.put("percentage_command_topic", getBaseTopicForDevice() + "/percentage_set");
    config.put("speed_range_min", "1");
    config.put("speed_range_max", "6");
    return config;
  }
  
}
