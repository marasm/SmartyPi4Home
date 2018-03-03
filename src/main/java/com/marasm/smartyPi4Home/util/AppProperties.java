/**
 * 
 */
package com.marasm.smartyPi4Home.util;

import java.io.IOException;
import java.util.Properties;

import com.marasm.smartyPi4Home.main.SmartyPi4Home;


/**
 * @author mkorotkovas
 *
 */
public class AppProperties
{
  public static final String APP_VERSION_PROP = "app.version";
  
  private static Properties appProperties = new Properties();
  
  public static String getProperty(String inPropName) throws IOException
  {
    checkPopulateProps();
    return appProperties.getProperty(inPropName);
  }
  
  private static void checkPopulateProps() throws IOException
  {
    if (appProperties.isEmpty())
    {
      appProperties.load(SmartyPi4Home.class.getResourceAsStream("/app.properties"));
    }
  }
  
  
}
