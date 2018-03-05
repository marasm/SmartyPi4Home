/**
 * 
 */
package com.marasm.smartyPi4Home.aws;

/**
 * @author mkorotkovas
 *
 */
public interface AwsDeviceUpdateListener
{
  public void onAwsDeviceShadowUpdate(AwsDevice inAwsDevice);
}
