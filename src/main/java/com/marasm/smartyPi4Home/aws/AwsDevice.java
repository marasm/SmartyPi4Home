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
  private int mode = 0;
  
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
  
  public void updateReportedShadow()
  {
    String payload = 
        "{\"state\": {"
         + "\"reported\": {\"status\": \"" + getStatus() + "\","
         + "               \"mode\": " + getMode() + "}"
        + "}}";
    
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
    //only notify if updates are valid
    if ((status == AwsDeviceStatus.ON && mode > 0) ||
        (status == AwsDeviceStatus.OFF && mode == 0))
    {
      awsDeviceUpdateListenerList.forEach(l -> l.onAwsDeviceShadowUpdate(this));
    }
    
  }

  public int getMode()
  {
    return mode;
  }

  public void setMode(int inMode)
  {
    mode = inMode;
    notifyUpdateListeners();
  }

  public void initializeValues()
  {
    status = AwsDeviceStatus.OFF;
    mode = 0;
  }

}
