package com.marasm.smartyPi4Home.mqtt;

import java.io.FileInputStream;
import java.util.MissingResourceException;
import java.util.Properties;

import com.marasm.logger.AppLogger;
import com.marasm.util.StringUtil;

public class MqttConfigUtil
{
  private static final String MQTT_PROPERTY_FILE_PATH = 
    System.getProperty("user.home") + "/.smartyPi4Home/mqtt.properties";
  
  private static Properties mqttProps;
  
  public static String getBrokerUri()
  {
    return getMqttProperty("broker.uri");
  }
  public static String getClientId()
  {
    return getMqttProperty("client.id");
  }
  
  private static String getMqttProperty(String name)
  {
    loadProps();
    
    String value = mqttProps.getProperty(name);
    if (StringUtil.isEmpty(value))
    {
      throw new MissingResourceException("Unable to find property in " + MQTT_PROPERTY_FILE_PATH, 
        MqttConfigUtil.class.getSimpleName(), name);
    }
    else
    {
      return value;
    }
  }
  
  private static void loadProps()
  {
    if (mqttProps == null)
    {
      mqttProps = new Properties();
      try
      {
        AppLogger.debug("Loading MQTT pros from: " + MQTT_PROPERTY_FILE_PATH);
        mqttProps.load(new FileInputStream(MQTT_PROPERTY_FILE_PATH));
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
    }
  }
  
}