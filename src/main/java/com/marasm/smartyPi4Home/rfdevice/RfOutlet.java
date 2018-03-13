/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

import java.util.ArrayList;
import java.util.List;

import com.marasm.smartyPi4Home.types.DeviceStatus;

/**
 * @author mkorotkovas
 *
 */
public class RfOutlet implements Comparable<RfOutlet> 
{
  private final String id;
  private final String name;
  private final Protocol protocol;
  private final int onCode;
  private final int offCode;
  
  private DeviceStatus status = DeviceStatus.OFF;
  private List<DeviceUpdateListener> deviceUpdateListeners; 
  
  public RfOutlet(String inId, String inName, Protocol inProtocol, int inOnCode, int inOffCode)
  {
    super();
    id = inId;
    name = inName;
    protocol = inProtocol;
    onCode = inOnCode;
    offCode = inOffCode;
    deviceUpdateListeners = new ArrayList<>();
  }

  public void addDeviceUpdateListener(DeviceUpdateListener inListener)
  {
    deviceUpdateListeners.add(inListener);
  }
  
  public String getId()
  {
    return id;
  }


  public String getName()
  {
    return name;
  }


  public int getOnCode()
  {
    return onCode;
  }


  public int getOffCode()
  {
    return offCode;
  }


  public Protocol getProtocol()
  {
    return protocol;
  }


  @Override
  public int compareTo(RfOutlet inOutlet)
  {
    return id.compareTo(inOutlet.getId());
  }


  public DeviceStatus getStatus()
  {
    return status;
  }

  
  public void setStatus(DeviceStatus inStatus)
  {
    status = inStatus;
    notifyUpdateListeners();
  }

  public void setStatusNoNotification(DeviceStatus inStatus)
  {
    status = inStatus;
  }


  private void notifyUpdateListeners()
  {
    deviceUpdateListeners.forEach(l -> l.onPhysicalDeviceUpdate(this));
  }
  
  
  
  
}
