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
	 * Es sind zwei verschiedene Arten von Stimmen möglich:
	 * <ul>
	 * <li>erststimmen</li>
	 * <li>zweitstimmen</li>
	 * </ul>
	 * Beispiele:
	 * <ul>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebniskonverter/service/landtagswahl/05/05315000/erststimmen">
	 * /landtagswahl/05/05315000/erststimmen</a></li>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebniskonverter/service/landtagswahl/05/05315000/zweitstimmen">
	 * /landtagswahl/05/05315000/zweitstimmen</a></li>
	 * </ul>
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/landtagswahl/05/05315000/{art}")
	public String getLandtagswahlergebnisse(@PathParam("art") String art) throws IOException {

		logger.info("/landtagswahl/05/05315000/" + art + " called");
		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));
		Art stimmart = Art.erststimmen.name().equals(art) ? Art.erststimmen : Art.zweitstimmen;
		Facade facade = new LandtagswahlergebnisFacade(stimmart);
		return facade.getJson();
	}

	/**
	 * <p>
	 * Beispiel: <a href=
	 * "http://localhost:8080/wahlergebniskonverter/service/landtagswahl/05/05315000/2017-05-14">
	 * /landtagswahl/05/05315000/2017-05-14</a>
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
			Wahldaten wahldaten = new RequestToWahldaten(wahl, bundesland, gemeinde, datum);
			facade = new VotemanagerFacade(wahldaten, urlString);
		} else {
			facade = new DefaultFacade(DefaultFacade.ERROR, "no data found");
		}

		return facade.getJson();
	}
}
