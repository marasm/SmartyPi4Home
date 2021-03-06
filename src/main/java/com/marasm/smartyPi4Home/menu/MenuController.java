/**
 * 
 */
package com.marasm.smartyPi4Home.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.marasm.lcd4pi.Button;
import com.marasm.lcd4pi.LCD;
import com.marasm.logger.AppLogger;
import com.marasm.smartyPi4Home.rfdevice.DeviceController;
import com.marasm.smartyPi4Home.types.DeviceStatus;
import com.marasm.smartyPi4Home.util.AppProperties;
import com.pi4j.system.NetworkInfo;

/**
 * @author mkorotkovas
 *
 */
public class MenuController
{
  private final LCD lcd;
  private final DeviceController deviceController;
  
  private MenuItem curMenuItem;
  private List<MenuItem> curSiblingItems;
  private List<MenuItem> parentMenuItems;
  
  public MenuController(LCD inLcd, DeviceController inDeviceController) 
    throws InterruptedException, IOException
  {
    String ipAddress = NetworkInfo.getIPAddresses()[0];

    lcd = inLcd;
    deviceController = inDeviceController;
    curSiblingItems = new ArrayList<>();
    parentMenuItems = new ArrayList<>();
    String appVersion = AppProperties.getProperty(AppProperties.APP_VERSION_PROP);
    
    curSiblingItems.add(new MenuItem("SmartyPi4Home\nv" + appVersion,
      () -> 
        {
          lcd.setText("IP Address:\n" + ipAddress);
          sleep(2000);
          showCurMenuItem();
        }, 
      null));
    
    curSiblingItems.add(new MenuItem("Device Control",
      () -> showChildMenu4CurItem(),
      createMenuItemsFromAvailableDevices( deviceController)));
    
    curMenuItem = curSiblingItems.get(0);
    
    showCurMenuItem();
  }
  
  public void handleButtonEvents(Button inPressedButton)
  {
    AppLogger.debug(inPressedButton + " button pressed");
    switch (inPressedButton)
    {
      case SELECT:
        selectCurMenuItem();
        break;
      case LEFT:
        showParentMenu();
        break;
      case RIGHT:
        selectCurMenuItem();
        break;
      case UP:
        showPreviousSiblingItem();
        break;
      case DOWN:
        showNextSiblingItem();
        break;
      
      default:
        break;
    }
  }
  
  private void showParentMenu()
  {
    if (parentMenuItems != null && !parentMenuItems.isEmpty())
    {
      curSiblingItems = parentMenuItems;
      curMenuItem = curSiblingItems.get(0);
      showCurMenuItem();
    }
    
  }

  private void showPreviousSiblingItem()
  {
    int indexOfCurItem = curSiblingItems.indexOf(curMenuItem);
    if (indexOfCurItem >= 0)
    {
      if (indexOfCurItem == 0)
      {
        curMenuItem = curSiblingItems.get(curSiblingItems.size() - 1);
      }
      else
      {
        curMenuItem = curSiblingItems.get(indexOfCurItem - 1);
      }
      
      showCurMenuItem();
    }
  }

  private void showNextSiblingItem()
  {
    int indexOfCurItem = curSiblingItems.indexOf(curMenuItem);
    if (indexOfCurItem >= 0)
    {
      if (indexOfCurItem == curSiblingItems.size() - 1)
      {
        curMenuItem = curSiblingItems.get(0);
      }
      else
      {
        curMenuItem = curSiblingItems.get(indexOfCurItem + 1);
      }
      
      showCurMenuItem();
    }
  }

  private void showCurMenuItem()
  {
    lcd.clear();
    lcd.setText(curMenuItem.getText());
  }
  
  private void selectCurMenuItem()
  {
    lcd.clear();
    curMenuItem.handleItemSelection();
  }
  
  private void showChildMenu4CurItem()
  {
    if (curMenuItem.getChildItems() != null && !curMenuItem.getChildItems().isEmpty())
    {
      parentMenuItems = curSiblingItems;
      curSiblingItems = curMenuItem.getChildItems();
      curMenuItem = curMenuItem.getChildItems().get(0);
      showCurMenuItem();
    }
  }

  private List<MenuItem> createMenuItemsFromAvailableDevices(
    DeviceController inDevController)
  {
    List<MenuItem> deviceMenuItems = new ArrayList<>();
    
    inDevController.getAllAvailableDevices().forEach( (device) -> 
    {
      deviceMenuItems.add(new MenuItem( device.getName(), 
        () -> 
          {
            lcd.clear();
            if (device.getStatus() == DeviceStatus.ON)
            { 
              device.setStatus(DeviceStatus.OFF);
              inDevController.updatedPhysicalDeviceState(device);
              lcd.setText(device.getName() + "\nOff");
            }
            else
            {
              device.setStatus(DeviceStatus.ON);
              inDevController.updatedPhysicalDeviceState(device);
              lcd.setText(device.getName() + "\n On");
            }
          }, 
        null));
    });
    return deviceMenuItems;
  }

  private void sleep(long inDelay)
  {
    try
    {
      Thread.sleep(inDelay);
    }
    catch (InterruptedException e)
    {
      AppLogger.warn("Exception while sleeping thread");
    }
  }
}


