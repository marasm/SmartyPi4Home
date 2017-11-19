/**
 * 
 */
package com.marasm.smartyPi4Home.main;

import com.marasm.lcd4pi.LCD;
import com.marasm.logger.AppLogger;

/**
 * @author mkorotkovas
 *
 */
public class SmartyPi4Home
{
  
  public static void main(String[] args) 
  {
    AppLogger.initLogger("smartyPiLogger");
    AppLogger.debug("Starting SmartyPi4Home");
    
    try
    {
      LCD lcd = LCD.getInstance();
      lcd.clear();
      lcd.setText("SmartyPi4Home\nv0.1");
    }
    catch (Exception e)
    {
      AppLogger.error(e.getMessage(), e);
      System.exit(1);
    }
  }
  
}
