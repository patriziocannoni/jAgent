package br.com.cannoni.jagent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

/**
 * @author patrizio
 * @since 03/01/2017
 */
public class Main {
	
	public static void main(String[] args) {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					List<String> memLines = Files.readAllLines(Paths.get("/proc/meminfo"));
					System.out.println(memLines.get(1));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		executorService.scheduleWithFixedDelay(runnable, 0, 1, TimeUnit.SECONDS);
		
		SerialPort[] ports = SerialPort.getCommPorts();
		System.out.println("\nAvailable Ports:\n");
		for (int i = 0; i < ports.length; ++i)
			System.out.println("   [" + i + "] " + ports[i].getSystemPortName() + ": " + ports[i].getDescriptivePortName());
		System.out.println("");
		
		SerialPort comPort = SerialPort.getCommPorts()[1];
		comPort.setBaudRate(57600);
		comPort.openPort();
		comPort.addDataListener(new SerialPortDataListener() {
		   @Override
		   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
		   @Override
		   public void serialEvent(SerialPortEvent event)
		   {
		      if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
		         return;
		      byte[] newData = new byte[comPort.bytesAvailable()];
		      int numRead = comPort.readBytes(newData, newData.length);
		      System.out.println("Read " + numRead + " bytes.");
		      System.out.println(new String(newData));
		   }
		});
	}

}
