/**
 * 
 */
package com.marasm.smartyPi4Home.menu;

import com.marasm.lcd4pi.LCD;

/**
 * @author mkorotkovas
 *
 */
public interface MenuItem
{
  public void handleItemSelection(LCD inLCD);
}
