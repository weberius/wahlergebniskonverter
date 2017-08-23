package de.illilli.opendata.service;

/**
 * Diese Enumeration löst die Zweisung von Gemeinde zu Gemeindeschlüssel auf.
 * 
 */
public enum Gemeinde {

	koeln("05315000", "Köln", "koeln");

	/**
	 * 
	 * @param key
	 *            der Gemeindeschlüssel
	 * @param name
	 *            der Name kleingeschrieben ohne Umlaute
	 */
	private Gemeinde(String key, String fullName, String name) {
		this.key = key;
		this.fullName = fullName;
		this.name = name;
	}

	public String key;
	public String name;
	public String fullName;
}
