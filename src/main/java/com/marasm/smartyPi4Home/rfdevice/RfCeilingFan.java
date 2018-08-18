/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

/**
 * @author mkorotkovas
 *
 */
public class RfCeilingFan extends GenericRfDevice
{

  private final int onMode1Code;
  private final int onMode2Code;
  private final int onMode3Code;
  private final int onMode4Code;
  private final int onMode5Code;
  private final int onMode6Code;
  private final int offCode;
  
  
 

  public RfCeilingFan(String inId, String inName, Protocol inProtocol, int inOnMode1Code, int inOnMode2Code,
    int inOnMode3Code, int inOnMode4Code, int inOnMode5Code, int inOnMode6Code, int inOffCode)
  {
    super(inId, inName, inProtocol);
    onMode1Code = inOnMode1Code;
    onMode2Code = inOnMode2Code;
    onMode3Code = inOnMode3Code;
    onMode4Code = inOnMode4Code;
    onMode5Code = inOnMode5Code;
    onMode6Code = inOnMode6Code;
    offCode = inOffCode;
  }


 

  public int getOffCode()
  {
    return offCode;
  }




  public int getOnMode1Code()
  {
    return onMode1Code;
  }




  public int getOnMode2Code()
  {
    return onMode2Code;
  }




  public int getOnMode3Code()
  {
    return onMode3Code;
  }




  public int getOnMode4Code()
  {
    return onMode4Code;
  }




  public int getOnMode5Code()
  {
    return onMode5Code;
  }




  public int getOnMode6Code()
  {
    return onMode6Code;
  }
  
  
}
