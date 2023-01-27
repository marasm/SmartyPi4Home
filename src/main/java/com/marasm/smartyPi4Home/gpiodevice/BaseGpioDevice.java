/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.marasm.smartyPi4Home.aws.AwsDevice;
import com.pi4j.io.gpio.Pin;

/**
 * @author mkorotkovas
 *
 */
public abstract class BaseGpioDevice
{
  protected final String id;
  protected final String description;
  

  protected final List<Pin> devicePickerPins;
  
  protected String deviceState = "OFF";

  protected Pin stateActivationPin = getOffPin();
  

  public BaseGpioDevice(String id, String description, String type, List<Pin> devicePickerPins)
  {
    this.id = id;
    this.description = description;
    this.devicePickerPins = devicePickerPins;
    this.type = type;
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
  
  public abstract void updateDeviceStateWithMqttData(String inCommand);

  public Map<String, String> getDeviceMqttConfigData()
  {
    Map<String, String> config = new HashMap<>();
    config.put("name", getDescription());
    config.put("unique_id", getId());
    config.put("object_id", getId());
    config.put("stat_t", getBaseTopicForDevice() + "/state");
    config.put("cmd_t", getBaseTopicForDevice() + "/set");

    return config;
  }
  

  public String getDeviceState()
  {
    return deviceState;
  }

  public Pin getStateActivationPin()
  {
    return stateActivationPin;
  }

  public String getDescription()
  {
    return description;
  }

  protected final String type;

  public String getType()
  {
    return type;
  }

  public String getBaseTopicForDevice()
  {
    return "homeassistant/" + getType() + "/" + getId();
  }

  public String getStateTopicForDevice()
  {
    return getBaseTopicForDevice() + "/state";
  }

}
