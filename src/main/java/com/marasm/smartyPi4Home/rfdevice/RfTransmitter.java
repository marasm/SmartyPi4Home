/**
 * 
 */
package com.marasm.smartyPi4Home.rfdevice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.marasm.logger.AppLogger;

/**
 * @author mkorotkovas
 *
 */
public class RfTransmitter
{
  
  private final Protocol protocol;
  private final Runtime runtime;
  
  
  
  public RfTransmitter(Protocol inProtocol)
  {
    super();
    protocol = inProtocol;
    runtime = Runtime.getRuntime();
  }

  public void sendCode(int inCode) throws IOException, InterruptedException
  {
    Process process = runtime.exec("rfoutlet/codesend -l " + protocol.getPulseLength() + " " + inCode);
    String output = new BufferedReader(new InputStreamReader(process.getInputStream()))
      .lines().collect(Collectors.joining("\n"));
    
    int result = process.waitFor();
    
    AppLogger.debug("Process executed with return code of: " + result);
    AppLogger.debug("Process output: " + output);
  }
  
}
