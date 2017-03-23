/**
 * 
 */
package br.com.cannoni.jagent.service;

import java.util.List;

import br.com.cannoni.jagent.model.MemoryInfo;

/**
 * @author patrizio
 *
 */
public interface MemInfoService {

	List<MemoryInfo> getMemoryInfo();
	
	List<String> getDiskInfo();
	
}
