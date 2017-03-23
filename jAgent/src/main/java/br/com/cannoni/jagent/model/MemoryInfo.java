/**
 * 
 */
package br.com.cannoni.jagent.model;

/**
 * @author patrizio
 * 
 */
public class MemoryInfo {
	
	private String description;
	
	private String value;
	
	public MemoryInfo() {
		super();
	}
	
	public MemoryInfo(String description, String value) {
		this.description = description;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.description + ": " + this.value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
