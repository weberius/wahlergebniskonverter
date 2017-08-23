package de.illilli.opendata.service;

public enum Wahl {

	landtagswahl("landtagswahl"), bundestagswahl("bundestagswahl");

	private Wahl(String name) {
		this.name = name;
	}

	public String name;

}
