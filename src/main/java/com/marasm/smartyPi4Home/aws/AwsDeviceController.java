/**
 * 
 */
package com.marasm.smartyPi4Home.aws;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.marasm.logger.AppLogger;
import com.marasm.smartyPi4Home.aws.AwsConfigUtil.KeyStorePasswordPair;
import com.marasm.smartyPi4Home.gpiodevice.BaseGpioDevice;
import com.marasm.smartyPi4Home.gpiodevice.GpioDeviceController;

/**
 * @author mkorotkovas
 *
 */
public class AwsDeviceController implements AwsDeviceUpdateListener
{
  private AWSIotMqttClient client;
  private List<AwsDevice> awsDeviceList;
  private GpioDeviceController physicalDeviceController;

  public AwsDeviceController()
  {
    String clientEndpoint = AwsConfigUtil.getClientEndpoint(); 
    String clientId = AwsConfigUtil.getClientId();
    String certificateFile = AwsConfigUtil.getCertificateFilePath();
    String privateKeyFile = AwsConfigUtil.getPrivateKeyFilePath(); 

    KeyStorePasswordPair pair = AwsConfigUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile);
    client = new AWSIotMqttClient(clientEndpoint, clientId, pair.keyStore, pair.keyPassword);
    
    
    awsDeviceList = new ArrayList<>();
  }
  
  public void connectPhysicalDevices(GpioDeviceController inDevController) throws AWSIotException
  {
    physicalDeviceController = inDevController;
    physicalDeviceController.getAllAvailableDevices().forEach(d -> {
      try
      {
        AwsDevice awsDevice = new AwsDevice(d.getId());
        client.attach(awsDevice);
        awsDeviceList.add(awsDevice);
        awsDevice.addAwsDeviceUpdateListener(this);
      }
      catch (AWSIotException e)
      {
        throw new RuntimeException(e);
      }
    });
    
    client.connect();
    
    //force all aws shadows to OFF
    awsDeviceList.forEach(d -> 
    {
      try
      {
        d.initializeValues();
        d.updateReportedShadow();
      }
      catch (Exception e)
      {
        throw new RuntimeException("Error setting all shadows to OFF", e);
      }
    });
  }

  

  @Override
  public synchronized void onAwsDeviceShadowUpdate(AwsDevice inAwsDevice)
  {
    AppLogger.debug(
      "AWS device update received: " + inAwsDevice.getThingName() + ", Status=" + inAwsDevice.getStatus() 
      + " Level=" + inAwsDevice.getLevel());
    
    if (physicalDeviceController == null)
    {
      throw new RuntimeException("AwsDeviceContoller was not properly initialized. "
        + "Make sure to add physical devices first");
    }
    try
    {
      BaseGpioDevice device = physicalDeviceController.getDeviceById(inAwsDevice.getThingName());
      device.updateDeviceStateWithAwsData(inAwsDevice);
      physicalDeviceController.updatePhysicalDeviceState(device);
      inAwsDevice.updateReportedShadow();
    }
    catch(Exception e)
    {
      throw new RuntimeException("Error handling AWS Update", e);
    }
  }
  

  public void diconnect() 
  {
    try
    {
      if (client != null) client.disconnect();
    }
    catch (Exception e)
    {
      throw new RuntimeException("Error while diconnecting from AWS", e);
    }
  }
}
