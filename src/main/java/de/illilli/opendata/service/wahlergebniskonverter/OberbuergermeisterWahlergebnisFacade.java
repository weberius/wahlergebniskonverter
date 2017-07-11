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

	private List<Stimmbezirk> data = new ArrayList<>();

	public OberbuergermeisterWahlergebnisFacade(InputStream inputStream) throws MalformedURLException, IOException {
		Reader reader = new InputStreamReader(inputStream, Config.getProperty("encoding"));
		data = new CSVReader(reader).getData();
	}

	public OberbuergermeisterWahlergebnisFacade() throws MalformedURLException, IOException {
		this(new URL(Config.getProperty("oberbuergermeister.05315000.url")).openStream());
	}

	public OberbuergermeisterWahlergebnisFacade(String urlString) throws MalformedURLException, IOException {
		this(new URL(urlString).openStream());
	}

	@Override
	public String getJson() {
		return new Gson().toJson(data);
	}
}
