/**
 * 
 */
package com.marasm.smartyPi4Home.menu;

import java.util.List;

/**
 * @author mkorotkovas
 *
 */
public class MenuItem
{
  private final String id;
  private final MenuItemSelectionHandler selectionHandler;
  private String text;
  private final List<MenuItem> childItems;
  
  
  
  public MenuItem(String inId, String inText,  MenuItemSelectionHandler inSelectionHandler,
    List<MenuItem> inChildItems)
  {
    super();
    id = inId;
    selectionHandler = inSelectionHandler;
    text = inText;
    childItems = inChildItems;
  }

  public void handleItemSelection() 
  {
    if (selectionHandler != null) selectionHandler.handleSelectAction();
  }

  public String getText()
  {
    return text;
  }

  public void setText(String inText)
  {
    text = inText;
  }

  public List<MenuItem> getChildItems()
  {
    return childItems;
  }

  public String getId()
  {
    return id;
  }
}
