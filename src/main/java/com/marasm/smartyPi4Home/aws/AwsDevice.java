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

/**
 * @author mkorotkovas
 *
 */
public class AwsDevice extends AWSIotDevice
{
  @AWSIotDeviceProperty(enableReport=false, allowUpdate=true)
  private AwsDeviceStatus status = AwsDeviceStatus.OFF;

  @AWSIotDeviceProperty(enableReport=false, allowUpdate=true)
  private int level = 0;
  
  private List<AwsDeviceUpdateListener> awsDeviceUpdateListenerList;

  
  public AwsDevice(String inThingName)
  {
    super(inThingName);
    awsDeviceUpdateListenerList = new ArrayList<>();
  }
  
  public void addAwsDeviceUpdateListener(AwsDeviceUpdateListener inAwsDeviceUpdateListener)
  {
    awsDeviceUpdateListenerList.add(inAwsDeviceUpdateListener);
  }
  
  public void updateReportedShadow(AwsDeviceStatus inDeviceStatus, boolean inOverrideDesired)
  {
    String payload = "{\"state\": {\"reported\": {\"status\": \"" + AwsDeviceStatus.UNKNOWN + "\","
                   + "                            \"level\": 0}}}";
    if (inOverrideDesired)
    {
      payload = 
        "{\"state\": {"
         + "\"reported\": {\"status\": \"" + inDeviceStatus + "\","
         + "               \"level\": 0},"
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

  public AwsDeviceStatus getStatus()
  {
    return status;
  }

  public void setStatus(AwsDeviceStatus inStatus)
  {
    if (inStatus != status)
    {
      status = inStatus;
      notifyUpdateListeners();
    }
  }

  public void setStatusNoNotification(AwsDeviceStatus inStatus)
  {
    AppLogger.debug("SetStatus NoN called. instatus=" + inStatus + ", Status=" + status);
    status = inStatus;
  }
  
  private  void notifyUpdateListeners()
  {
    awsDeviceUpdateListenerList.forEach(l -> l.onAwsDeviceShadowUpdate(this));
  }

  public int getLevel()
  {
    return level;
  }

  public void setLevel(int inLevel)
  {
    level = inLevel;
    notifyUpdateListeners();
  }

}
