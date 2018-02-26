/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

/**
 * @author mkorotkovas
 *
 */
public class RfOutlet implements Comparable<RfOutlet> 
{
  private final String id;
  private final String name;
  private final Protocol protocol;
  private final int onCode;
  private final int offCode;
  
  private boolean isOn;
  
  
  public RfOutlet(String inId, String inName, Protocol inProtocol, int inOnCode, int inOffCode)
  {
    super();
    id = inId;
    name = inName;
    protocol = inProtocol;
    onCode = inOnCode;
    offCode = inOffCode;
  }


  public String getId()
  {
    return id;
  }


  public String getName()
  {
    return name;
  }


  public int getOnCode()
  {
    return onCode;
  }


  public int getOffCode()
  {
    return offCode;
  }


  public Protocol getProtocol()
  {
    return protocol;
  }


  public boolean getIsOn()
  {
    return isOn;
  }


  public void setIsOn(boolean inIsOn)
  {
    isOn = inIsOn;
  }


  @Override
  public int compareTo(RfOutlet inOutlet)
  {
    return id.compareTo(inOutlet.getId());
  }
  
  
}
