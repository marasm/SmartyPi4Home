/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.marasm.logger.AppLogger;

/**
 * @author mkorotkovas
 *
 */
public class RfTransmitter
{
  
  private static final int NUM_OF_TIMES_TO_SEND_CODE = 5;
  
  private final Runtime runtime;
  
  
  
  public RfTransmitter()
  {
    super();
    runtime = Runtime.getRuntime();
  }

  public void sendCode(Protocol inProtocol, int inCode) 
  {
    IntStream.range(0, NUM_OF_TIMES_TO_SEND_CODE).forEach(i ->
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
        Thread.sleep(50);
      }
      catch(Exception e)
      {
        throw new RuntimeException(e);
      }
    });
  }
  
}
