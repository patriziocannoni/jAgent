/**
 * 
 */
package br.com.cannoni.jagent.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cannoni.jagent.model.MemoryInfo;
import br.com.cannoni.jagent.service.MemInfoService;

/**
 * @author patrizio
 *
 */
@Service("memInfoService")
public class MemInfoServiceImpl implements MemInfoService {
	
	@Override
	public List<MemoryInfo> getMemoryInfo() {
		List<MemoryInfo> memLines = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(Paths.get("/proc/meminfo"));
			String[] arr = lines.get(0).split(": ");
			memLines.add(new MemoryInfo(arr[0], arr[1]));
			
			arr = lines.get(2).split(": ");
			memLines.add(new MemoryInfo(arr[0], arr[1]));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memLines;
	}
	
	@Override
	public List<String> getDiskInfo() {
		String s = executeCommand("df -k");
		return Arrays.asList(s.split("\n"));
	}
	
	private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {

            p = Runtime.getRuntime().exec(command);

            p.waitFor();
            BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";           
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

}
