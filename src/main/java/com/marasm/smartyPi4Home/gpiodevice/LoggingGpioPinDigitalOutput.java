/**
 * 
 */
package com.marasm.smartyPi4Home.gpiodevice;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.marasm.logger.AppLogger;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinShutdown;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinListener;

/**
 * @author mkorotkovas
 *
 */
public class LoggingGpioPinDigitalOutput implements GpioPinDigitalOutput
{
  private Pin pin;
  
  public LoggingGpioPinDigitalOutput(Pin inPin)
  {
    super();
    pin = inPin;
  }

  @Override
  public boolean isHigh()
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public boolean isLow()
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public PinState getState()
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public boolean isState(PinState inState)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public GpioProvider getProvider()
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Pin getPin()
  {
    return pin;
  }
  
  @Override
  public void setName(String inName)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public String getName()
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void setTag(Object inTag)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public Object getTag()
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void setProperty(String inKey, String inValue)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public boolean hasProperty(String inKey)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public String getProperty(String inKey)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public String getProperty(String inKey, String inDefaultValue)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Map<String, String> getProperties()
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void removeProperty(String inKey)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void clearProperties()
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void export(PinMode inMode)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void export(PinMode inMode, PinState inDefaultState)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void unexport()
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public boolean isExported()
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void setMode(PinMode inMode)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public PinMode getMode()
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public boolean isMode(PinMode inMode)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void setPullResistance(PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public PinPullResistance getPullResistance()
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public boolean isPullResistance(PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public Collection<GpioPinListener> getListeners()
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void addListener(GpioPinListener... inListener)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void addListener(List<? extends GpioPinListener> inListeners)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public boolean hasListener(GpioPinListener... inListener)
  {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void removeListener(GpioPinListener... inListener)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void removeListener(List<? extends GpioPinListener> inListeners)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void removeAllListeners()
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public GpioPinShutdown getShutdownOptions()
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void setShutdownOptions(GpioPinShutdown inOptions)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setShutdownOptions(Boolean inUnexport)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setShutdownOptions(Boolean inUnexport, PinState inState)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setShutdownOptions(Boolean inUnexport, PinState inState, PinPullResistance inResistance)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setShutdownOptions(Boolean inUnexport, PinState inState, PinPullResistance inResistance, PinMode inMode)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void high()
  {
    AppLogger.debug("{} Pin set to high", this.getPin());
  }
  
  @Override
  public void low()
  {
    AppLogger.debug("{} Pin set to low", this.getPin());
  }
  
  @Override
  public void toggle()
  {
    AppLogger.debug("{} Pin toggled", this.getPin());
  }
  
  @Override
  public Future<?> blink(long inDelay)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> blink(long inDelay, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> blink(long inDelay, PinState inBlinkState)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> blink(long inDelay, PinState inBlinkState, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> blink(long inDelay, long inDuration)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> blink(long inDelay, long inDuration, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> blink(long inDelay, long inDuration, PinState inBlinkState)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> blink(long inDelay, long inDuration, PinState inBlinkState, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration)
  {
    AppLogger.debug("{} Pin pulse for {}", this.getPin(), inDuration);
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, Callable<Void> inCallback)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, Callable<Void> inCallback, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, boolean inBlocking)
  {
    AppLogger.debug("{} Pin pulse for {}, blocking = {}", this.getPin(), inDuration, inBlocking);
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, boolean inBlocking, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, boolean inBlocking, Callable<Void> inCallback)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, boolean inBlocking, Callable<Void> inCallback, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, PinState inPulseState)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, PinState inPulseState, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, PinState inPulseState, Callable<Void> inCallback)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, PinState inPulseState, Callable<Void> inCallback, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, PinState inPulseState, boolean inBlocking)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, PinState inPulseState, boolean inBlocking, TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, PinState inPulseState, boolean inBlocking, Callable<Void> inCallback)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Future<?> pulse(long inDuration, PinState inPulseState, boolean inBlocking, Callable<Void> inCallback,
    TimeUnit inTimeUnit)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void setState(PinState inState)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setState(boolean inState)
  {
    // TODO Auto-generated method stub
    
  }
  
}
