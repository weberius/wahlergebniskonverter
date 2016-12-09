package de.illilli.opendata.service.wahlergebniskonverter.model;

/**
 * <pre>
 * "ergebnisse":[{
      "partei":"Partei",
      "stimmen":123
     }]
 * </pre>
 */
public class Ergebnis {

	public String partei;
	public int stimmen;

	@Override
	public String toString() {
		return "Ergebnis [partei=" + partei + ", stimmen=" + stimmen + "]";
	}

}
