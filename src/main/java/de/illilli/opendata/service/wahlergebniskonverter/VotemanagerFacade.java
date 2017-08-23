package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebniskonverter.model.Stimmbezirk;
import de.illilli.opendata.service.wahlergebniskonverter.model.Wahldaten;

public class VotemanagerFacade implements Facade {

	private Wahldaten wahldaten;

	public VotemanagerFacade(Wahldaten wahldaten, String urlString) throws MalformedURLException, IOException {
		this(wahldaten, new URL(urlString).openStream());
	}

	public VotemanagerFacade(Wahldaten wahldaten, InputStream inputStream) throws MalformedURLException, IOException {
		this.wahldaten = wahldaten;
		Reader reader = new InputStreamReader(inputStream, Config.getProperty("encoding"));
		List<Stimmbezirk> data = new CSVReader12(reader).getData();
		this.wahldaten.stimmbezirke = data.toArray(new Stimmbezirk[data.size()]);
	}

	@Override
	public String getJson() {
		return new Gson().toJson(this.wahldaten);
	}

}
