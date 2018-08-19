/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

/**
 * @author mkorotkovas
 *
 */
public class RfOutlet extends GenericRfDevice 
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



}
