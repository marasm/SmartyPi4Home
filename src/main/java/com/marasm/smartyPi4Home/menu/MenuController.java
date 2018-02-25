/**
 * 
 */
package com.marasm.smartyPi4Home.menu;

import java.util.HashMap;
import java.util.Map;

import com.marasm.lcd4pi.Button;
import com.marasm.lcd4pi.LCD;
import com.marasm.logger.AppLogger;

/**
 * @author mkorotkovas
 *
 */
public class MenuController
{
  private final LCD lcd;
  private Map<Integer, MenuItem> mainMenu = new HashMap<>();
  private int curMenuItemId;
  
  public MenuController(LCD inLcd) throws InterruptedException
  {
    lcd = inLcd;
    curMenuItemId = 0;
    
    mainMenu.put(0, lcd -> lcd.setText("SmartyPi4Home\nv0.1"));
    
    selectMenuItem(curMenuItemId);
  }
  
  public void handleButtonEvents(Button inPressedButton)
  {
    AppLogger.debug(inPressedButton + " button pressed");
    switch (inPressedButton)
    {
      case SELECT:
        selectMenuItem(curMenuItemId);
        break;
      case LEFT:
        
        break;
      case RIGHT:
        
        break;
      case UP:
        
        break;
      case DOWN:
        
        break;
      
      default:
        break;
    }
  }
  
  private void selectMenuItem(Integer inItemId)
  {
    lcd.clear();
    mainMenu.get(inItemId).handleItemSelection(lcd);
  }
}


