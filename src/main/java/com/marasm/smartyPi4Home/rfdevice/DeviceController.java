/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mkorotkovas
 *
 */
public class DeviceController
{
  private final RfTransmitter transmitter;
  
  private final Map<String, RfOutlet> allAvailableDeviceMap = new HashMap<>();
  
  public DeviceController(RfTransmitter inTransmitter)
  {
    super();
    transmitter = inTransmitter;

    allAvailableDeviceMap.put("o1", new RfOutlet("o1", "Office #1", Protocol.ONE, 4527411, 4527420));
    allAvailableDeviceMap.put("o2", new RfOutlet("o2", "Office #2", Protocol.ONE, 4527555, 4527564));
    allAvailableDeviceMap.put("o3", new RfOutlet("o3", "Office #3", Protocol.ONE, 4527875, 4527884));
    
    allAvailableDeviceMap.put("b1", new RfOutlet("b1", "Bedroom #1", Protocol.ONE, 5526835, 5526844));
    allAvailableDeviceMap.put("b2", new RfOutlet("b2", "Bedroom #2", Protocol.ONE, 5526979, 5526988));
    allAvailableDeviceMap.put("b3", new RfOutlet("b3", "Bedroom #3", Protocol.ONE, 5527299, 5527308));
  }

  public List<RfOutlet> getAllAvailableDevices()
  {
    List<RfOutlet> allDeviceList = new ArrayList<RfOutlet>(allAvailableDeviceMap.values());
    Collections.sort(allDeviceList);
    
    return allDeviceList;
  }
  
  public RfOutlet getDeviceById(String inId)
  {
    return allAvailableDeviceMap.get(inId);
  }
  
  public void turnDeviceOn(RfOutlet inDevice) 
  {
    transmitter.sendCode(inDevice.getProtocol(), inDevice.getOnCode());
    inDevice.setIsOn(true);
  }
  
  public void turnDeviceOff(RfOutlet inDevice) 
  {
    transmitter.sendCode(inDevice.getProtocol(), inDevice.getOffCode());
    inDevice.setIsOn(false);
  }
  
}
