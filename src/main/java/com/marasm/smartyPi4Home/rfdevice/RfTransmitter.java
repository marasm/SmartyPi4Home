/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

import com.marasm.logger.AppLogger;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

/**
 * @author mkorotkovas
 *
 */
public class RfTransmitter
{
  private static final int OUTPUT_PIN = 0;
  
  private final Protocol protocol;
  
  
  
  public RfTransmitter(Protocol inProtocol)
  {
    super();
    protocol = inProtocol;
    
    if (Gpio.wiringPiSetup() == -1) 
    {
      AppLogger.error(" ==>> GPIO setup failed for transmitter");
      return;
    }
    GpioUtil.export(OUTPUT_PIN, GpioUtil.DIRECTION_OUT);
    Gpio.pinMode(OUTPUT_PIN, Gpio.OUTPUT);
  }

  public void sendCode(int inCode)
  {
    String binStr = leftPadBinaryStringWithZeroes(Integer.toBinaryString(inCode), 24); 
    
    for (char bit : binStr.toCharArray())
    {
      sendBit(protocol, bit == '1');
    }
    
    sendSync(protocol);
  }
  
  private String leftPadBinaryStringWithZeroes(String inBinString, int inRequiredMinLength)
  {
    if (inBinString.length() >= inRequiredMinLength)
      return inBinString;
    else
    {
      String paddedStr = "";
      for (int i = inBinString.length(); i < inRequiredMinLength; i++)
      {
        paddedStr += "0";
      }
      return paddedStr += inBinString;
    }
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
    Gpio.digitalWrite(OUTPUT_PIN, 1);
    Gpio.delayMicroseconds(inPulseLength * nHighPulses);
    Gpio.digitalWrite(OUTPUT_PIN, 0);
    Gpio.delayMicroseconds(inPulseLength * nLowPulses);
  }
}
