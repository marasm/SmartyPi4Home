/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.marasm.logger.AppLogger;

/**
 * @author mkorotkovas
 *
 */
public class RfTransmitter
{
  
  private final Runtime runtime;
  
  
  
  public RfTransmitter()
  {
    super();
    runtime = Runtime.getRuntime();
  }

  public void sendCode(Protocol inProtocol, int inCode) 
  {
    try
    {
      Process process = runtime.exec("rfoutlet/codesend -l " + inProtocol.getPulseLength() + " " + inCode);
      String stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()))
        .lines().collect(Collectors.joining("\n"));
      String errOutput = new BufferedReader(new InputStreamReader(process.getErrorStream()))
        .lines().collect(Collectors.joining("\n"));
      
      int retCode = process.waitFor();
      if (retCode != 0)
      {
        AppLogger.error("Process failed with return code of: " + retCode);
        AppLogger.error("Process output: " + errOutput);
      }
      else
      {
        AppLogger.debug("Process output: " + stdOutput);
      }
    }
    catch(Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
}
