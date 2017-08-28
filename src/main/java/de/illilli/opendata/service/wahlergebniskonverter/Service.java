package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.DefaultFacade;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebniskonverter.model.RequestToWahldaten;
import de.illilli.opendata.service.wahlergebniskonverter.model.Wahldaten;

@Path("/")
public class Service {

	private final static Logger logger = Logger.getLogger(Service.class);
	public static final String ENCODING = Config.getProperty("encoding");

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	/**
	 * Method for checking the state of the service.
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/ping")
	public String getPing() {
		logger.info("called /ping");
		return "{alive}";
	}

	/**
	 * Für die Landtagswahlen 2017 werden die Wahlergebnisse vom Votemanager
	 * geliefert. Die Daten liegen kommasepariert zum Download bereit. Es ist
	 * durch Übergabe des Ortes möglich, die Daten in ein json-Format zu ändern.
	 * <p>
	 * Beispiele:
	 * <ul>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebniskonverter/service/landtagswahl/05/05315000/2017-05-14?url=http://www.stadt-koeln.de/wahlen/landtagswahl/05-2017/Landtagswahl_NRW376.csv">
	 * /landtagswahl/05/05315000/2017-05-14?url=http://www.stadt-koeln.de/wahlen
	 * /landtagswahl/05-2017/Landtagswahl_NRW376.csv</a></li>
	 * </ul>
	 * <ul>
	 * <p>
	 * Für die Landtagswahl 2012 in NRW liegen für Köln Excel Dateien zur
	 * Verfügung. Die Wahlergebnisse werden nach erststimmen und zweitstimmen
	 * unterschieden. Diese müssen durch den Übergabeparameter 'art' explizit
	 * angefordert werden. Wird keine Stimmart angegeben, werden automatisch die
	 * zweitstimmen zurückgeliefert.
	 * </p>
	 * <p>
	 * Beispiele:
	 * </p>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebniskonverter/service/landtagswahl/05/05315000/2012-05-13?art=erststimmen">
	 * /landtagswahl/05/05315000/2012-05-13?art=erststimmen</a></li>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebniskonverter/service/landtagswahl/05/05315000/2012-05-13?art=zweitstimmen">
	 * /landtagswahl/05/05315000/2012-05-13?art=zweitstimmen</a></li>
	 * </ul>
	 * 
	 * </p>
	 * 
	 * @param wahl
	 * @param land
	 * @param gemeinde
	 * @param art
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{wahl}/{bundesland}/{gemeinde}/{datum}")
	public String getWahlergebnisse(@PathParam("wahl") String wahl, @PathParam("bundesland") String bundesland,
			@PathParam("gemeinde") String gemeinde, @PathParam("datum") String datum)
			throws MalformedURLException, IOException {

		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));

		String urlString = request.getParameter("url");

		Facade facade;
		if (wahl != null || bundesland != null || gemeinde != null || datum != null || urlString != null) {
			if ("landtagswahl".equals(wahl) && "05".equals(bundesland) && "05315000".equals(gemeinde)
					&& "2012-05-13".equals(datum)) {
				String art = request.getParameter("art");
				Art stimmart = Art.erststimmen.name().equals(art) ? Art.erststimmen : Art.zweitstimmen;
				facade = new LandtagswahlergebnisFacade(stimmart);
			} else if ("landtagswahl".equals(wahl) && "05".equals(bundesland) && "05315000".equals(gemeinde)
					&& "2017-05-14".equals(datum)) {
				Wahldaten wahldaten = new RequestToWahldaten(wahl, bundesland, gemeinde, datum);
				facade = new VotemanagerFacade(wahldaten, urlString);
			} else {
				facade = new DefaultFacade(DefaultFacade.INFO, "not supported");
			}
		} else {
			facade = new DefaultFacade(DefaultFacade.ERROR, "no data found");
		}

		return facade.getJson();
	}
}
