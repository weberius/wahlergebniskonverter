package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;

public class LandtagswahlergebnisFacade implements Facade {

	private String json = new String();

	public LandtagswahlergebnisFacade(Art art) throws IOException {
		String url = Config.getProperty("landtagswahl.05.05315000." + art.name() + ".url");
		InputStream inputStream = new URL(url).openStream();
		LandtagswahlPoiReader poiReader = new LandtagswahlPoiReader(inputStream);
		json = poiReader.getJson();
	}

	@Override
	public String getJson() {
		return json;
	}

}
