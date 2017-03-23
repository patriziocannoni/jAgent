package br.com.cannoni.jagent.service;

import com.fazecast.jSerialComm.SerialPort;

/**
 * @author patrizio
 * @since 22/03/2017
 */
public interface SerialPortService {

	SerialPort[] getSerialPorts();
	
	String readSerialPort(SerialPort serialPort);
	
}
