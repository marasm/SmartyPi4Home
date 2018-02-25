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
  ONE(175);     
  
  private final int pulseLength;

  
  private Protocol(int inPulseLength)
  {
    pulseLength = inPulseLength;
  }


  public int getPulseLength()
  {
    return pulseLength;
  }

}
