package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.gson.Gson;

import de.illilli.opendata.service.wahlergebniskonverter.model.Ergebnis;
import de.illilli.opendata.service.wahlergebniskonverter.model.Stimmbezirk;
import de.illilli.opendata.service.wahlergebniskonverter.model.Wahldaten;

public class LandtagswahlPoiReader {

	private static final Logger logger = Logger.getLogger(LandtagswahlPoiReader.class);
	private Wahldaten wahldaten = new Wahldaten();;

	public LandtagswahlPoiReader(InputStream inputStream) throws IOException {
		Workbook wb = new HSSFWorkbook(inputStream);
		DataFormatter formatter = new DataFormatter();

		List<Stimmbezirk> stimmbezirkList = new ArrayList<>();
		int rows = 0;
		int cells = 0;
		for (Sheet sheet : wb) {
			Map<Integer, String> header = new HashMap<>();
			for (Row row : sheet) {
				if (rows == 1) {
					for (Cell cell : row) {

						String text = formatter.formatCellValue(cell);
						if (cells == 0) {
							header.put(cells, "nr");
						} else if (cells == 1) {
							header.put(cells, "wahlberechtigt");
						} else if (cells == 2) {
							header.put(cells, "wahlbeteiligung");
						} else if (cells == 3) {
							header.put(cells, "gueltig");
						} else {
							header.put(cells, text);
						}
						cells++;
					}
				} else if (rows > 106) {
					Map<String, String> data = new HashMap<>();
					double wahlberechtigt = 0;
					double wahlbeteiligung = 0.0;
					double gueltig = 0;
					List<Ergebnis> ergebnisList = new ArrayList<>();
					for (Cell cell : row) {
						String text = formatter.formatCellValue(cell);
						if (cells == 0) {
							data.put(header.get(cells), text);
						} else if (cells == 1) {
							wahlberechtigt = cell.getNumericCellValue();
							data.put(header.get(cells), text);
						} else if (cells == 2) {
							wahlbeteiligung = cell.getNumericCellValue();
							data.put(header.get(cells), text);
						} else if (cells == 3) {
							gueltig = cell.getNumericCellValue();
							data.put(header.get(cells), text);
						} else {
							if (header.get(cells).contains("%")) {
								data.put(header.get(cells), text);
							} else {
								Ergebnis ergebnis = new Ergebnis();
								ergebnis.partei = header.get(cells);
								ergebnis.stimmen = new Double(Math.ceil(cell.getNumericCellValue())).intValue();

								data.put(header.get(cells), text);
								ergebnisList.add(ergebnis);
							}
						}
						cells++;
					}
					int abgegeben = new Double(Math.ceil(wahlberechtigt * wahlbeteiligung * 0.01)).intValue();
					data.put("abgegeben", abgegeben + "");
					int ungueltig = new Double(abgegeben - gueltig).intValue();
					data.put("ungueltig", ungueltig + "");

					Stimmbezirk stimmbezirk = new Stimmbezirk();
					stimmbezirk.abgegeben = abgegeben;
					stimmbezirk.ergebnisse = ergebnisList.toArray(new Ergebnis[ergebnisList.size()]);
					stimmbezirk.gueltig = new Double(gueltig).intValue();
					stimmbezirk.nr = Integer.parseInt(data.get("nr"));
					stimmbezirk.ungueltig = ungueltig;
					stimmbezirk.wahlberechtigt = new Double(wahlberechtigt).intValue();
					stimmbezirkList.add(stimmbezirk);

				}
				// stop after 906 rows of data
				if (rows == 906) {
					break;
				}
				cells = 0;
				rows++;
			}

			wahldaten.art = "landtagswahl";
			wahldaten.datum = "13.05.2012";
			wahldaten.gemeinde = "05315000";
			wahldaten.bundesland = "05";
			wahldaten.stimmbezirke = stimmbezirkList.toArray(new Stimmbezirk[stimmbezirkList.size()]);

		}
		wb.close();
	}

	public String getJson() {
		return new Gson().toJson(wahldaten);
	}
}
