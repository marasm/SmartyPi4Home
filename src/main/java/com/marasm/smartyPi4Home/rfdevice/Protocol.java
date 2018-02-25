/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

/**
 * @author mkorotkovas
 *
 */
public enum Protocol
{
  ONE(175,        //pulse length microseconds                                        ___
      3, 1,       //to transmit a 1 the high pulse is 3 pulse lengths and low is 1: |   |_
                  //                                                                 _
      1, 3,       //to transmit a 0 the high pulse is 1 pulse lengths and low is 3: | |___
                  //                                                            _      
      1, 31);     //sync signal is one high pulse and then low for 31 pulses : | |_______________________________
  
  private final int pulseLength;
  private final int pulseMultiplierOneHi;
  private final int pulseMultiplierOneLow;
  private final int pulseMultiplierZeroHi;
  private final int pulseMultiplierZeroLow;
  private final int pulseMultiplierSyncHi;
  private final int pulseMultiplierSyncLow;

  
  private Protocol(int inPulseLength, int inPulseMultiplierOneHi, int inPulseMultiplierOneLow,
    int inPulseMultiplierZeroHi, int inPulseMultiplierZeroLow, int inPulseMultiplierSyncHi,
    int inPulseMultiplierSyncLow)
  {
    pulseLength = inPulseLength;
    pulseMultiplierOneHi = inPulseMultiplierOneHi;
    pulseMultiplierOneLow = inPulseMultiplierOneLow;
    pulseMultiplierZeroHi = inPulseMultiplierZeroHi;
    pulseMultiplierZeroLow = inPulseMultiplierZeroLow;
    pulseMultiplierSyncHi = inPulseMultiplierSyncHi;
    pulseMultiplierSyncLow = inPulseMultiplierSyncLow;
  }


  public int getPulseLength()
  {
    return pulseLength;
  }


  public int getPulseMultiplierOneHi()
  {
    return pulseMultiplierOneHi;
  }


  public int getPulseMultiplierOneLow()
  {
    return pulseMultiplierOneLow;
  }


  public int getPulseMultiplierZeroHi()
  {
    return pulseMultiplierZeroHi;
  }


  public int getPulseMultiplierZeroLow()
  {
    return pulseMultiplierZeroLow;
  }


  public int getPulseMultiplierSyncHi()
  {
    return pulseMultiplierSyncHi;
  }


  public int getPulseMultiplierSyncLow()
  {
    return pulseMultiplierSyncLow;
  }
  
  
}
