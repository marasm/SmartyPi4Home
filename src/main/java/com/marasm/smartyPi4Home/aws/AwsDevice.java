/**
 * 
 */
package com.marasm.smartyPi4Home.aws;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.iot.client.AWSIotDevice;
import com.amazonaws.services.iot.client.AWSIotDeviceProperty;
import com.amazonaws.services.iot.client.AWSIotMessage;
import com.marasm.logger.AppLogger;
import com.marasm.smartyPi4Home.types.DeviceStatus;

/**
 * @author mkorotkovas
 *
 */
public class AwsDevice extends AWSIotDevice
{
  @AWSIotDeviceProperty(enableReport=false, allowUpdate=true)
  private DeviceStatus status = DeviceStatus.OFF;
  
  private List<AwsDeviceUpdateListener> awsDeviceUpdateListenerList;

  
  public AwsDevice(String inThingName, DeviceStatus inInitDeviceStatus)
  {
    super(inThingName);
    awsDeviceUpdateListenerList = new ArrayList<>();
    status = inInitDeviceStatus;
  }
  
  public void addAwsDeviceUpdateListener(AwsDeviceUpdateListener inAwsDeviceUpdateListener)
  {
    awsDeviceUpdateListenerList.add(inAwsDeviceUpdateListener);
  }
  
  public void updateReportedShadow(DeviceStatus inDeviceStatus, boolean inOverrideDesired)
  {
    String payload = "{\"state\": {\"reported\": {\"status\": \"" + DeviceStatus.UNKNOWN + "\"}}}";
    if (inOverrideDesired)
    {
      payload = 
        "{\"state\": {"
         + "\"reported\": {\"status\": \"" + inDeviceStatus + "\"},"
         + "\"desired\": null"
        + "}}";
    }
    
    try
    {
      update(new AWSIotMessage("$aws/things/" + getThingName() + "/shadow/update", 
        getDeviceReportQos(), 
        payload), 3000);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Error updating reported shadow", e);
    }
  }
  
  public void updateReportedShadow(boolean inOverrideDesired)
  {
    updateReportedShadow(getStatus(), inOverrideDesired);
  }

  public DeviceStatus getStatus()
  {
    return status;
  }

  public void setStatus(DeviceStatus inStatus)
  {
    if (inStatus != status)
    {
      status = inStatus;
      notifyUpdateListeners();
    }
  }

  public void setStatusNoNotification(DeviceStatus inStatus)
  {
    AppLogger.debug("SetStatus NoN called. instatus=" + inStatus + ", Status=" + status);
    status = inStatus;
  }
  
  private  void notifyUpdateListeners()
  {
    awsDeviceUpdateListenerList.forEach(l -> l.onAwsDeviceShadowUpdate(this));
  }

}
