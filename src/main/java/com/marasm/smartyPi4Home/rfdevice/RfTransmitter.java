/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

import com.pi4j.wiringpi.Gpio;

/**
 * @author mkorotkovas
 *
 */
public class RfTransmitter
{
  private final Protocol protocol;
  
  public RfTransmitter(Protocol inProtocol)
  {
    super();
    protocol = inProtocol;
  }

  public void sendCode(int inCode)
  {
    String binStr = Integer.toBinaryString(inCode);
    
    for (int i = 0; i < binStr.length(); i++)
    {
      sendBit(protocol, binStr.charAt(i) == '1');
    }
    
    sendSync(protocol);
  }
   
  private void sendSync(Protocol inProtocol)
  {
    transmit(inProtocol.getPulseLength(),
      inProtocol.getPulseMultiplierSyncHi(), 
      inProtocol.getPulseMultiplierSyncLow());
  }
  
  private void sendBit(Protocol inProtocol, boolean inIsBinOne)
  {
    if(inIsBinOne)
    {
      transmit(inProtocol.getPulseLength(),
        inProtocol.getPulseMultiplierOneHi(), 
        inProtocol.getPulseMultiplierOneLow());
    }
    else
    {
      transmit(inProtocol.getPulseLength(),
        inProtocol.getPulseMultiplierZeroHi(), 
        inProtocol.getPulseMultiplierZeroLow());
    }
  }


  private void transmit(int inPulseLength, int nHighPulses, int nLowPulses)
  {
    Gpio.digitalWrite(0, 1);
    Gpio.delayMicroseconds(inPulseLength * nHighPulses);
    Gpio.digitalWrite(0, 0);
    Gpio.delayMicroseconds(inPulseLength * nLowPulses);
  }
}
