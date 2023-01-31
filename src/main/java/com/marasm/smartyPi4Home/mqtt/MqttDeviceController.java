/**
 * 
 */
package com.marasm.smartyPi4Home.mqtt;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.marasm.logger.AppLogger;
import com.marasm.smartyPi4Home.gpiodevice.BaseGpioDevice;
import com.marasm.smartyPi4Home.gpiodevice.FanGpioDevice;
import com.marasm.smartyPi4Home.gpiodevice.GpioDeviceController;

/**
 * @author mkorotkovas
 *
 */
public class MqttDeviceController implements MqttCallback
{
  private MqttClient client;
  private GpioDeviceController physicalDeviceController;

  public MqttDeviceController() throws MqttException
  {
    String brokerUri = MqttConfigUtil.getBrokerUri(); 
    String clientId = MqttConfigUtil.getClientId();

    client = new MqttClient(brokerUri, clientId);
    
    client.setCallback(this);
    client.setTimeToWait(1000);
    
    MqttConnectOptions options = new MqttConnectOptions();
    options.setConnectionTimeout(20);
    options.setKeepAliveInterval(15);
    options.setAutomaticReconnect(true);
    options.setCleanSession(true);
    client.connect(options);
    
  }
  
  public void connectPhysicalDevices(GpioDeviceController inDevController) throws MqttException 
  {
    physicalDeviceController = inDevController;

    //publish device config for HA to find the devices
    physicalDeviceController.getAllAvailableDevices()
      .forEach(d -> 
        {
          publishDeviceConfig(d);
          updateMqttStateTopics(d);
        });
    
    //subscribe to all the command topics for all the devices
    client.subscribe(new String[]{"homeassistant/+/+/set", "homeassistant/+/+/percentage_set"}, 
      new int[]{0, 0});
  }

  private void publishDeviceConfig(BaseGpioDevice inDevice)
  {
    try
    {
      client.publish(inDevice.getBaseTopicForDevice() + "/config", createConfigPayloadForDevice(inDevice), 
        0, true);
    }
    catch (MqttException | IOException e)
    {
      AppLogger.error("Failed to publish device config: ", e);
    }
  }


  private byte[] createConfigPayloadForDevice(BaseGpioDevice inDevice) throws IOException
  {
    ObjectNode payload = JsonNodeFactory.instance.objectNode();
    inDevice.getDeviceMqttConfigData().entrySet().forEach(e -> payload.put(e.getKey(), e.getValue()));

    AppLogger.debug("Created config message payload: " + payload.toString());

    return payload.toString().getBytes();
  }

  
  @Override
  public void connectionLost(Throwable cause) 
  {
    AppLogger.error("Lost connection to MQTT", cause);
  }

  @Override
  public void messageArrived(String inTopic, MqttMessage inMessage) throws Exception 
  {
    AppLogger.debug(
      "MQTT device update received: " + inMessage.getId() + ", Topic=" + inTopic + 
        ", Payload=" + new String(inMessage.getPayload()));
    
    String uniqueId = getUniqueIdFromTopic(inTopic);
    
    if (physicalDeviceController == null)
    {
      throw new RuntimeException("MqttDeviceContoller was not properly initialized. "
        + "Make sure to add physical devices first");
    }
    try
    {
      BaseGpioDevice device = physicalDeviceController.getDeviceById(uniqueId);
      //update the device with the MQTT data
      device.updateDeviceStateWithMqttData(new String(inMessage.getPayload()));
      physicalDeviceController.updatePhysicalDeviceState(device);
      //update the MQTT device status topic
      updateMqttStateTopics(device);
    }
    catch(Exception e)
    {
      throw new RuntimeException("Error handling MQTT Update", e);
    }
    
  }

  
  @Override
  public void deliveryComplete(IMqttDeliveryToken token) 
  {
    AppLogger.debug("Delivery Complete: " + token);
  }

  private void updateMqttStateTopics(BaseGpioDevice inDevice) 
  {
    try
    {
      client.publish(inDevice.getStateTopicForDevice(), inDevice.getDeviceState().getBytes(), 1, false);
      // for fan devices also update the percentage state topic
      if (inDevice instanceof FanGpioDevice)
      {
        client.publish(inDevice.getBaseTopicForDevice() + "/percentage_state", 
          ((FanGpioDevice)inDevice).getMode().getBytes(), 1, false);
      }

    }
    catch (MqttException e)
    {
      AppLogger.error("Failed to update device state in MQTT", e);
    }
  }

  private String getUniqueIdFromTopic(String inTopic) 
  {
    //assume topic is this format: homeassistant/{component}/{objectId/uniqueId}/{topicType}
    return inTopic.split("/")[2];
  }


  public void diconnect() 
  {
    try
    {
      if (client != null)
      {
        client.disconnect();
        client.close();
      } 
    }
    catch (Exception e)
    {
      throw new RuntimeException("Error while diconnecting from MQTT", e);
    }
  }

}
