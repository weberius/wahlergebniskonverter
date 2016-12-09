package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;

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
	 * Beispiel: <a href=
	 * "http://localhost:8080/wahlergebniskonverter/service/landtagswahl/05/05315000/erststimmen">
	 * /landtagswahl/05/05315000/erststimmen</a>
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/landtagswahl/05/05315000/erststimmen")
	public String getLandtagswahlergebnisse() throws IOException {

		logger.info("/landtagswahl/05/05315000/erststimmen called");
		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));

		Facade facade = new LandtagswahlergebnisFacade();
		return facade.getJson();
	}

}
