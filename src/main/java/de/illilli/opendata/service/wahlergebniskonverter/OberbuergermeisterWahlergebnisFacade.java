package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebniskonverter.model.Stimmbezirk;

public class OberbuergermeisterWahlergebnisFacade implements Facade {

	String url = Config.getProperty("oberbuergermeister.05315000.url");
	private List<Stimmbezirk> data = new ArrayList<>();

	public OberbuergermeisterWahlergebnisFacade() throws MalformedURLException, IOException {

		InputStream inputStream = new URL(url).openStream();
		Reader reader = new InputStreamReader(inputStream, Config.getProperty("encoding"));

		data = new CSVReader(reader).getData();

	}

	@Override
	public String getJson() {
		return new Gson().toJson(data);
	}
}
