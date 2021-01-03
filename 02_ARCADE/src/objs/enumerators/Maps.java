package objs.enumerators;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public enum Maps {

	// ------------------------------------------------------------
	// Enumerator (Maps)
	// ------------------------------------------------------------

	MAP1("map1", "MAP_map1"), MAP2("map2", "MAP_map1"), MAP3("map3", "MAP_map1");

	// ------------------------------------------------------------
	// Members - Constructor
	// ------------------------------------------------------------

	private String xml;
	private String spriteFile;

	private Maps(String xml, String spriteFile) {
		this.xml = xml;
		this.spriteFile = spriteFile;
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public String getXml() { return xml; }

	public void setXml(String xml) { this.xml = xml; }

	public String getSpriteFile() { return spriteFile; }

	public void setSpriteFile(String spriteFile) { this.spriteFile = spriteFile; }
}
