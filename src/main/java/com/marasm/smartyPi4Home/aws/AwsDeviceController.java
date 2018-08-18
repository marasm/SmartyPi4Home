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
import com.marasm.smartyPi4Home.rfdevice.GenericRfDevice;
import com.marasm.smartyPi4Home.rfdevice.DeviceController;
import com.marasm.smartyPi4Home.rfdevice.DeviceUpdateListener;
import com.marasm.smartyPi4Home.rfdevice.RfOutlet;
import com.marasm.smartyPi4Home.types.DeviceStatus;

/**
 * @author mkorotkovas
 *
 */
public class AwsDeviceController implements DeviceUpdateListener, AwsDeviceUpdateListener
{
  private AWSIotMqttClient client;
  private List<AwsDevice> awsDeviceList;
  private DeviceController physicalDeviceController;

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
  
  public void connectPhysicalDevices(DeviceController inDevController) throws AWSIotException
  {
    physicalDeviceController = inDevController;
    physicalDeviceController.getAllAvailableDevices().forEach(d -> {
      try
      {
        AwsDevice awsDevice = new AwsDevice(d.getId(), d.getStatus());
        client.attach(awsDevice);
        awsDeviceList.add(awsDevice);
        d.addDeviceUpdateListener(this);
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
        d.updateReportedShadow(DeviceStatus.OFF, true);
      }
      catch (Exception e)
      {
        throw new RuntimeException("Error setting all shadows to OFF", e);
      }
    });
  }

  @Override
  public synchronized void onPhysicalDeviceUpdate(GenericRfDevice inDevice)
  {
    AppLogger.debug(
      "Physical device update received: " + inDevice.getName() + ", Status=" + inDevice.getStatus());
    
    try
    {
      AwsDevice awsDevice = getAwsDeviceByThingName(inDevice.getId());
      awsDevice.setStatusNoNotification(inDevice.getStatus());
      awsDevice.updateReportedShadow(true);
    }
    catch(Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override
  public synchronized void onAwsDeviceShadowUpdate(AwsDevice inAwsDevice)
  {
    AppLogger.debug(
      "AWS device update received: " + inAwsDevice.getThingName() + ", Status=" + inAwsDevice.getStatus());
    
    if (physicalDeviceController == null)
    {
      throw new RuntimeException("AwsDeviceContoller was not properly initialized. "
        + "Make sure to add physical devices first");
    }
    try
    {
      GenericRfDevice device = physicalDeviceController.getDeviceById(inAwsDevice.getThingName());
      device.setStatusNoNotification(inAwsDevice.getStatus());
      physicalDeviceController.updatedPhysicalDeviceState(device);
      inAwsDevice.updateReportedShadow(false);
    }
    catch(Exception e)
    {
      throw new RuntimeException("Error handling AWS Update", e);
    }
  }
  
  private AwsDevice getAwsDeviceByThingName(String inThingName)
  {
    AwsDevice awsDevice = awsDeviceList.stream()
      .filter(ad -> ad.getThingName().equals(inThingName))
      .findFirst()
      .get();
    return awsDevice;
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
