package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.illilli.opendata.service.Facade;

public class LandtagswahlergebnisFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetJsonErstimmen() throws IOException {
		String expected = IOUtils
				.toString(this.getClass().getResourceAsStream("/landtagswahl.2012.05.05315000.erststimmen.json"));
		Facade facade = new LandtagswahlergebnisFacade(Art.erststimmen);
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetJsonZweit() throws IOException {
		String expected = IOUtils
				.toString(this.getClass().getResourceAsStream("/landtagswahl.2012.05.05315000.zweitstimmen.json"));
		Facade facade = new LandtagswahlergebnisFacade(Art.zweitstimmen);
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

}
