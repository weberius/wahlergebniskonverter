package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import de.illilli.opendata.service.Facade;

public class OberbuergermeisterWahlergebnisFacadeTest {

	private static final Logger logger = Logger.getLogger(OberbuergermeisterWahlergebnisFacadeTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		Facade facade = new OberbuergermeisterWahlergebnisFacade();
		logger.info(facade.getJson());
	}

}
