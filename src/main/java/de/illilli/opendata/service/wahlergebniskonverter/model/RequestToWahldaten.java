package de.illilli.opendata.service.wahlergebniskonverter.model;

/**
 * In dieser Klasse werden die Informationen aus dem Request zu einem unfertigen
 * Wahldaten-Objekt konstruiert. Es bietet darüber hinaus die Möglichkeit, die
 * übergebenen Werte auf Validität zu prüfen.
 */
public class RequestToWahldaten extends Wahldaten {

	public RequestToWahldaten(String wahl, String bundesland, String gemeinde, String datum) {
		super.wahl = wahl;
		super.bundesland = bundesland;
		super.gemeinde = gemeinde;
		super.datum = datum;
	}

	public Wahldaten getWahldaten() {
		return this;
	}

}
