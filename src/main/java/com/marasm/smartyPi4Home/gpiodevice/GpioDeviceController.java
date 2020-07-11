/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marasm.smartyPi4Home.rfdevice.GenericRfDevice;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * @author mkorotkovas
 *
 */
public class GpioDeviceController
{
  private final List<Pin> ALL_UTILIZED_OUTPUT_PINS = List.of();
  
  private List<BaseGpioDevice> allAvailableDevices = new ArrayList<>();
  private final GpioController gpioController;
  private Map<Pin, GpioPinDigitalOutput> availableOutputPins = new HashMap<>();
  
  public GpioDeviceController()
  {
    super();
    allAvailableDevices.add(new LightGpioDevice("office-light", List.of(RaspiPin.GPIO_00)));
    allAvailableDevices.add(  new FanGpioDevice("office-fan",   List.of(RaspiPin.GPIO_00)));
    allAvailableDevices.add(  new FanGpioDevice("bedroom-fan",  List.of(RaspiPin.GPIO_00)));
    allAvailableDevices.add(  new FanGpioDevice("living-fan",   List.of(RaspiPin.GPIO_00)));
    
    gpioController = GpioFactory.getInstance();
    
    //provision and init all pins defined for devices
    ALL_UTILIZED_OUTPUT_PINS.stream()
      .forEach(p -> availableOutputPins.put(p, 
        gpioController.provisionDigitalOutputPin(p, p.toString(), PinState.LOW)));
  }

  public List<BaseGpioDevice> getAllAvailableDevices()
  {
    return allAvailableDevices;
  }
  
  public BaseGpioDevice getDeviceById(String inId)
  {
    return allAvailableDevices.stream()
      .filter(d -> d.getId().equals(inId))
      .findFirst().orElse(null);
  }

  public void setAllAvailableDevices(List<BaseGpioDevice> inAllAvailableDevices)
  {
    allAvailableDevices = inAllAvailableDevices;
  }
  
  public synchronized void updatedPhysicalDeviceState(BaseGpioDevice inDevice)  
  {
    //TODO
    if (inDevice instanceof LightGpioDevice)
    {
      
    }
    else if(inDevice instanceof FanGpioDevice)
    {
      
    }
  }

  
}
