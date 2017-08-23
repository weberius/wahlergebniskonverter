package de.illilli.opendata.service;

public enum Land {

	nrw("05", "Nordrhein-Westfalen", "nrw");

	private Land(String key, String fullName, String name) {
		this.key = key;
		this.fullName = fullName;
		this.name = name;
	}

	public String name;
	public String fullName;
	public String key;
}
