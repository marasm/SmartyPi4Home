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
public abstract class GenericRfDevice
{

  protected final String id;
  protected final String name;
  protected final Protocol protocol;
  protected DeviceStatus status = DeviceStatus.OFF;
  protected List<DeviceUpdateListener> deviceUpdateListeners;

  public GenericRfDevice(String inId, String inName, Protocol inProtocol)
  {
    super();
    id = inId;
    name = inName;
    protocol = inProtocol;
    deviceUpdateListeners = new ArrayList<>();
  }
 
  public String getId()
  {
    return id;
  }


  public String getName()
  {
    return name;
  }
  
  public Protocol getProtocol()
  {
    return protocol;
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
  
  public void addDeviceUpdateListener(DeviceUpdateListener inListener)
  {
    deviceUpdateListeners.add(inListener);
  }
  
  public void setStatusNoNotification(DeviceStatus inStatus)
  {
    status = inStatus;
  }


  private void notifyUpdateListeners()
  {
    deviceUpdateListeners.forEach(listener -> listener.onPhysicalDeviceUpdate(this));
  }
  
  
}
