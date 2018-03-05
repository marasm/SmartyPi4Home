/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.marasm.smartyPi4Home.types.DeviceStatus;

/**
 * @author mkorotkovas
 *
 */
public class DeviceController
{
  private final RfTransmitter transmitter;
  
  private final List<RfOutlet> allAvailableDevices = new ArrayList<>();
  
  public DeviceController(RfTransmitter inTransmitter)
  {
    super();
    transmitter = inTransmitter;

    allAvailableDevices.add(new RfOutlet("office-outlet-1", "Office #1", Protocol.ONE, 4527411, 4527420));
    allAvailableDevices.add(new RfOutlet("office-outlet-2", "Office #2", Protocol.ONE, 4527555, 4527564));
    allAvailableDevices.add(new RfOutlet("office-outlet-3", "Office #3", Protocol.ONE, 4527875, 4527884));
                        
    allAvailableDevices.add(new RfOutlet("b1", "Bedroom #1", Protocol.ONE, 5526835, 5526844));
    allAvailableDevices.add(new RfOutlet("b2", "Bedroom #2", Protocol.ONE, 5526979, 5526988));
    allAvailableDevices.add(new RfOutlet("b3", "Bedroom #3", Protocol.ONE, 5527299, 5527308));
  }

  public List<RfOutlet> getAllAvailableDevices()
  {
    Collections.sort(allAvailableDevices);
    
    return allAvailableDevices;
  }
  
  public RfOutlet getDeviceById(String inId)
  {
    return allAvailableDevices.stream()
      .filter( d -> d.getId().equals(inId))
      .findFirst()
      .get();
  }
  
  public void updatedPhysicalDeviceState(RfOutlet inDevice)  
  {
    if (inDevice.getStatus() == DeviceStatus.ON)
    {
      transmitter.sendCode(inDevice.getProtocol(), inDevice.getOnCode());
    }
    else
    {
      transmitter.sendCode(inDevice.getProtocol(), inDevice.getOffCode());
    }
  }
  
  
  
}
