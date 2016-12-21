package de.illilli.opendata.service.wahlergebniskonverter.model;

import java.util.Arrays;

/**
 * <pre>
 * { 
  "art":"erststimmen",
  "datum":"11.11.2016",
  "wahl":"landtagswahl",
  "bundesland":"05",
  "gemeinde":"05315000",
  "stimmbezirke":[{
    "nr":10101,
    "wahlberechtigt":1000,
    "abgegeben":500,
    "gueltig":450,
    "ungueltig":50,
    "ergebnisse":[{
      "partei":"Partei1",
      "stimmen":123
     }]
  }]
}
 * </pre>
 */
public class Wahldaten {

	public String art;
	public String bundesland;
	public String gemeinde;
	public String datum;
	public String wahl;
	public Stimmbezirk[] stimmbezirke;

	@Override
	public String toString() {
		return "Wahldaten [art=" + art + ", bundesland=" + bundesland + ", gemeinde=" + gemeinde + ", datum=" + datum
				+ ", stimmbezirke=" + Arrays.toString(stimmbezirke) + "]";
	}

}
