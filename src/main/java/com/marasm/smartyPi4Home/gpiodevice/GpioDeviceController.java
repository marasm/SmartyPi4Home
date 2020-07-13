/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pi4j.io.gpio.GpioController;
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
  private final List<Pin> ALL_UTILIZED_OUTPUT_PINS = List.of(
    RaspiPin.GPIO_00,
    RaspiPin.GPIO_01,
    RaspiPin.GPIO_02,
    RaspiPin.GPIO_03,
    RaspiPin.GPIO_04,
    RaspiPin.GPIO_05,
    RaspiPin.GPIO_06,
    RaspiPin.GPIO_07,
    RaspiPin.GPIO_10,
    RaspiPin.GPIO_12,
    RaspiPin.GPIO_13,
    RaspiPin.GPIO_14);
  
  private List<BaseGpioDevice> allAvailableDevices = new ArrayList<>();
  private final GpioController gpioController;
  private Map<Pin, GpioPinDigitalOutput> availableOutputPins = new HashMap<>();
  
  public GpioDeviceController(GpioController inGpioController)
  {
    super();
    allAvailableDevices.add(new LightGpioDevice("office-light", List.of(RaspiPin.GPIO_01, RaspiPin.GPIO_04)));
    allAvailableDevices.add(  new FanGpioDevice("office-fan",   List.of(RaspiPin.GPIO_01, RaspiPin.GPIO_04)));
    allAvailableDevices.add(  new FanGpioDevice("bedroom-fan",  List.of(RaspiPin.GPIO_01, RaspiPin.GPIO_04, 
                                                                        RaspiPin.GPIO_05, RaspiPin.GPIO_06)));
    allAvailableDevices.add(  new FanGpioDevice("living-fan",   List.of(RaspiPin.GPIO_04, RaspiPin.GPIO_05)));

    gpioController = inGpioController;
    
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
  
  private void resetAllOutputPinsToLow()
  {
    availableOutputPins.values().stream()
      .forEach(GpioPinDigitalOutput::low);
  }
  
  public synchronized void updatePhysicalDeviceState(BaseGpioDevice inDevice)  
  {
    resetAllOutputPinsToLow();
    
    //pick device
    inDevice.devicePickerPins
      .forEach(p -> availableOutputPins.get(p).high());
    
    availableOutputPins.get(inDevice.getStateActivationPin()).pulse(500, true);
    
    resetAllOutputPinsToLow();
  }

  
}