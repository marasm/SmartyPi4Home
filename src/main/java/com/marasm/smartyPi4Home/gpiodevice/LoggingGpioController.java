/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.Collection;

import com.marasm.logger.AppLogger;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinAnalog;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.gpio.GpioPinAnalogOutput;
import com.pi4j.io.gpio.GpioPinDigital;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinInput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.GpioPinShutdown;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.trigger.GpioTrigger;

/**
 * @author mkorotkovas
 *
 */
public class LoggingGpioController implements GpioController
{
  
  @Override
  public void export(PinMode inMode, PinState inDefaultState, GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void export(PinMode inMode, GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public boolean isExported(GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void unexport(Pin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void unexport(GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void unexportAll()
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setMode(PinMode inMode, GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public PinMode getMode(GpioPin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public boolean isMode(PinMode inMode, GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void setPullResistance(PinPullResistance inResistance, GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public PinPullResistance getPullResistance(GpioPin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public boolean isPullResistance(PinPullResistance inResistance, GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void high(GpioPinDigitalOutput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public boolean isHigh(GpioPinDigital... inPin)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void low(GpioPinDigitalOutput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public boolean isLow(GpioPinDigital... inPin)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void setState(PinState inState, GpioPinDigitalOutput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setState(boolean inState, GpioPinDigitalOutput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public boolean isState(PinState inState, GpioPinDigital... inPin)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public PinState getState(GpioPinDigital inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void toggle(GpioPinDigitalOutput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void pulse(long inMilliseconds, GpioPinDigitalOutput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setValue(double inValue, GpioPinAnalogOutput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public double getValue(GpioPinAnalog inPin)
  {
    // TODO Auto-generated method stub
    return 0;
  }
  
  @Override
  public void addListener(GpioPinListener inListener, GpioPinInput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void addListener(GpioPinListener[] inListeners, GpioPinInput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void removeListener(GpioPinListener inListener, GpioPinInput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void removeListener(GpioPinListener[] inListeners, GpioPinInput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void removeAllListeners()
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void addTrigger(GpioTrigger inTrigger, GpioPinInput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void addTrigger(GpioTrigger[] inTriggers, GpioPinInput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void removeTrigger(GpioTrigger inTrigger, GpioPinInput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void removeTrigger(GpioTrigger[] inTriggers, GpioPinInput... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void removeAllTriggers()
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider inProvider, Pin inPin, String inName,
    PinMode inMode, PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider inProvider, Pin inPin, PinMode inMode,
    PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider inProvider, Pin inPin, String inName,
    PinMode inMode)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider inProvider, Pin inPin, PinMode inMode)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin inPin, String inName, PinMode inMode,
    PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin inPin, PinMode inMode,
    PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin inPin, String inName, PinMode inMode)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin inPin, PinMode inMode)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider inProvider, Pin inPin, String inName,
    PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider inProvider, Pin inPin,
    PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider inProvider, Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider inProvider, Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalInput provisionDigitalInputPin(Pin inPin, String inName, PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalInput provisionDigitalInputPin(Pin inPin, PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalInput provisionDigitalInputPin(Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalInput provisionDigitalInputPin(Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider inProvider, Pin inPin, String inName,
    PinState inDefaultState)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider inProvider, Pin inPin, PinState inDefaultState)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider inProvider, Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider inProvider, Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalOutput provisionDigitalOutputPin(Pin inPin, String inName, PinState inDefaultState)
  {
    AppLogger.debug("Pin {} provisioned for digital output", inPin);
    return new LoggingGpioPinDigitalOutput(inPin);
  }
  
  @Override
  public GpioPinDigitalOutput provisionDigitalOutputPin(Pin inPin, PinState inDefaultState)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalOutput provisionDigitalOutputPin(Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinDigitalOutput provisionDigitalOutputPin(Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogInput provisionAnalogInputPin(GpioProvider inProvider, Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogInput provisionAnalogInputPin(GpioProvider inProvider, Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogInput provisionAnalogInputPin(Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogInput provisionAnalogInputPin(Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider inProvider, Pin inPin, String inName,
    double inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider inProvider, Pin inPin, double inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider inProvider, Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider inProvider, Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogOutput provisionAnalogOutputPin(Pin inPin, String inName, double inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogOutput provisionAnalogOutputPin(Pin inPin, double inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogOutput provisionAnalogOutputPin(Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinAnalogOutput provisionAnalogOutputPin(Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider inProvider, Pin inPin, String inName, int inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider inProvider, Pin inPin, int inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider inProvider, Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider inProvider, Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionPwmOutputPin(Pin inPin, String inName, int inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionPwmOutputPin(Pin inPin, int inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionPwmOutputPin(Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionPwmOutputPin(Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider inProvider, Pin inPin, String inName,
    int inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider inProvider, Pin inPin, int inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider inProvider, Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider inProvider, Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin inPin, String inName, int inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin inPin, int inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin inPin, String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPin provisionPin(GpioProvider inProvider, Pin inPin, String inName, PinMode inMode,
    PinState inDefaultState)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPin provisionPin(GpioProvider inProvider, Pin inPin, String inName, PinMode inMode)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPin provisionPin(GpioProvider inProvider, Pin inPin, PinMode inMode)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPin provisionPin(Pin inPin, String inName, PinMode inMode)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPin provisionPin(Pin inPin, PinMode inMode)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void setShutdownOptions(GpioPinShutdown inOptions, GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setShutdownOptions(Boolean inUnexport, GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setShutdownOptions(Boolean inUnexport, PinState inState, GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setShutdownOptions(Boolean inUnexport, PinState inState, PinPullResistance inResistance, GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setShutdownOptions(Boolean inUnexport, PinState inState, PinPullResistance inResistance, PinMode inMode,
    GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public Collection<GpioPin> getProvisionedPins()
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPin getProvisionedPin(Pin inPin)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public GpioPin getProvisionedPin(String inName)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void unprovisionPin(GpioPin... inPin)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public boolean isShutdown()
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void shutdown()
  {
    // TODO Auto-generated method stub
    
  }
  
}
