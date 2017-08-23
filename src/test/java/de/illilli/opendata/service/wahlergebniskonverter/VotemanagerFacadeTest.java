package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.Gemeinde;
import de.illilli.opendata.service.Land;
import de.illilli.opendata.service.Wahl;
import de.illilli.opendata.service.wahlergebniskonverter.model.RequestToWahldaten;
import de.illilli.opendata.service.wahlergebniskonverter.model.Wahldaten;

public class VotemanagerFacadeTest {

	private Facade facade;

	/**
	 * 
	 * Daten entsprechen dem Datensatz von <a href=
	 * "http://www.stadt-koeln.de/wahlen/landtagswahl/05-2017/Landtagswahl_NRW376.csv">
	 * Landtagswahl_NRW376.csv</a>
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		Wahldaten wahldaten = new RequestToWahldaten(Wahl.landtagswahl.name(), Land.nrw.key, Gemeinde.koeln.key,
				"2017-05-14");
		String csvFile = "/Landtagswahl_NRW376.csv";
		this.facade = new VotemanagerFacade(wahldaten, this.getClass().getResourceAsStream(csvFile));

	}

	/**
	 * Prüft, ob überhaupt Daten vorliegen. Dabei wird darauf geprüft, die Länge
	 * des durch die Facade zurückgelieferten json-Strings größer als 0 ist.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testHasData() throws IOException {

		String actual = this.facade.getJson();
		Assert.assertTrue(actual.length() > 0);

	}

	/**
	 * Testet auf das Feld Stimmbezirk.gueltig im ersten Datensatz.
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@Test
	public void test10101Gueltig() throws MalformedURLException, IOException {

		Wahldaten wahldaten = new Gson().fromJson(this.facade.getJson(), Wahldaten.class);
		int actual = wahldaten.stimmbezirke[0].gueltig;
		int expected = 679;
		Assert.assertEquals(expected, actual);

	}

	/**
	 * Testet auf die Anzahl der Stimmen im ersten Datensatz.
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@Test
	public void test10101FirstDataset() throws MalformedURLException, IOException {

		Wahldaten wahldaten = new Gson().fromJson(this.facade.getJson(), Wahldaten.class);
		int actual = wahldaten.stimmbezirke[0].ergebnisse[0].stimmen;
		int expected = 198;
		Assert.assertEquals(expected, actual);

	}
}
