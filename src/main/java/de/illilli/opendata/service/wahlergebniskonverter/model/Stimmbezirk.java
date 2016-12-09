package de.illilli.opendata.service.wahlergebniskonverter.model;

import java.util.Arrays;

/**
 * <pre>
 * "stimmbezirke":{
    "nr":10101,
    "wahlberechtigt":1000,
    "abgegeben":500,
    "gueltig":450,
    "ungueltig":50,
    "ergebnisse":[{
      "partei":"SPD",
      "stimmen":123
     }]
  }
 * </pre>
 * 
 */
public class Stimmbezirk {

	public int nr;
	public int wahlberechtigt;
	public int abgegeben;
	public int gueltig;
	public int ungueltig;
	public Ergebnis[] ergebnisse;

	@Override
	public String toString() {
		return "Stimmbezirk [nr=" + nr + ", wahlberechtigt=" + wahlberechtigt + ", abgegeben=" + abgegeben
				+ ", gueltig=" + gueltig + ", ungueltig=" + ungueltig + ", ergebnisse=" + Arrays.toString(ergebnisse)
				+ "]";
	}

}
