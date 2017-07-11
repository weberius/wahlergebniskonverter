package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebniskonverter.model.Stimmbezirk;

public class OberbuergermeisterWahlergebnisFacadeTest {

	private static final Logger logger = Logger.getLogger(OberbuergermeisterWahlergebnisFacadeTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Dieser Test prüft, ob Daten vorliegen.
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@Test
	public void testForDataFromCsv() throws MalformedURLException, IOException {

		InputStream inputStream = this.getClass().getResourceAsStream("/Oberbuergermeisterwahl6.csv");
		Facade facade = new OberbuergermeisterWahlergebnisFacade(inputStream);
		String json = facade.getJson();

		Assert.assertTrue(json.length() > 0);
	}

	@Test
	public void testSizeOfList() throws MalformedURLException, IOException {

		InputStream inputStream = this.getClass().getResourceAsStream("/Oberbuergermeisterwahl6.csv");
		Facade facade = new OberbuergermeisterWahlergebnisFacade(inputStream);
		String json = facade.getJson();

		List<Stimmbezirk> stimmbezirkList = new Gson().fromJson(json, new ArrayList<Stimmbezirk>().getClass());

		int expected = 1024;
		int actual = stimmbezirkList.size();

		Assert.assertEquals(expected, actual);
	}

	/**
	 * Diese Klasse gibt das Ergebnis in die Console aus.
	 * 
	 * @param args
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws MalformedURLException, IOException {
		Facade facade = new OberbuergermeisterWahlergebnisFacade();
		logger.info(facade.getJson());
	}

}
