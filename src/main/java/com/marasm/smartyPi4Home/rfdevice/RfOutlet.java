/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

/**
 * @author mkorotkovas
 *
 */
public class RfOutlet extends GenericRfDevice implements Comparable<RfOutlet> 
{
  private final int onCode;
  private final int offCode;
  
  public RfOutlet(String inId, String inName, Protocol inProtocol, int inOnCode, int inOffCode)
  {
    super(inId, inName, inProtocol);

    onCode = inOnCode;
    offCode = inOffCode;
  }

  public int getOnCode()
  {
    return onCode;
  }


  public int getOffCode()
  {
    return offCode;
  }



  @Override
  public int compareTo(RfOutlet inOutlet)
  {
    return id.compareTo(inOutlet.getId());
  }


 

 
  
  
  
}
