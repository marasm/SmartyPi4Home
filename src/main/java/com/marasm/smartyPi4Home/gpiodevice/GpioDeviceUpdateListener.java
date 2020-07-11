/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

/**
 * @author mkorotkovas
 *
 */
@FunctionalInterface
public interface GpioDeviceUpdateListener
{
  public void onPhysicalDeviceUpdate(BaseGpioDevice inDevice);
}
