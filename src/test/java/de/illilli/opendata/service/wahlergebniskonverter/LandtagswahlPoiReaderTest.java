package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import de.illilli.opendata.service.wahlergebniskonverter.model.Wahldaten;

public class LandtagswahlPoiReaderTest {

	private LandtagswahlPoiReader poiReader;

	@Before
	public void setUp() throws Exception {
		Art stimmart = Art.erststimmen;
		InputStream inputStream = LandtagswahlPoiReaderTest.class
				.getResourceAsStream("/Landtagswahl 2012, Erststimmen.xls");
		poiReader = new LandtagswahlPoiReader(inputStream, stimmart);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNumberErgebnisse() throws IOException {
		Wahldaten wahldaten = new Gson().fromJson(poiReader.getJson(), Wahldaten.class);
		int expected = 800;
		int actual = wahldaten.stimmbezirke.length;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testFirstNumber() throws IOException {
		Wahldaten wahldaten = new Gson().fromJson(poiReader.getJson(), Wahldaten.class);
		int expected = 10101;
		int actual = wahldaten.stimmbezirke[0].nr;
		Assert.assertEquals(expected, actual);
	}

}
