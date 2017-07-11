package de.illilli.opendata.service.wahlergebniskonverter;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

import de.illilli.opendata.service.wahlergebniskonverter.model.Ergebnis;
import de.illilli.opendata.service.wahlergebniskonverter.model.Stimmbezirk;

public class CSVReader {

	private List<Stimmbezirk> data = new ArrayList<>();

	public CSVReader(Reader reader) throws IOException {

		CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter(';'));

		Map<Integer, String> headerMap = new HashMap<>();
		for (String key : parser.getHeaderMap().keySet()) {
			headerMap.put(parser.getHeaderMap().get(key), key);
		}

		for (final CSVRecord record : parser) {

			Iterator<String> iterator = record.iterator();
			Stimmbezirk stimmbezirk = new Stimmbezirk();
			List<Ergebnis> ergebnisList = new ArrayList<>();

			for (int i = 0; iterator.hasNext(); i++) {
				String str = iterator.next();
				// System.out.println(i + ": " + str);
				if (i == 0) {
					stimmbezirk.nr = NumberUtils.createInteger(str);
				} else if (i == 4) {
					stimmbezirk.wahlberechtigt = NumberUtils.createInteger(str);
				} else if (i == 5) {
					stimmbezirk.abgegeben = NumberUtils.createInteger(str);
				} else if (i == 8) {
					stimmbezirk.gueltig = NumberUtils.createInteger(str);
				} else if (i == 10) {
					stimmbezirk.ungueltig = NumberUtils.createInteger(str);
				} else if (i > 10) {
					String partei = headerMap.get(i);
					if (partei != null && !partei.contains("Proz") && partei.length() > 0) {
						Ergebnis ergebnis = new Ergebnis();
						ergebnis.partei = partei;
						ergebnis.stimmen = NumberUtils.createInteger(str);
						ergebnisList.add(ergebnis);
					}
				}
			}
			stimmbezirk.ergebnisse = ergebnisList.toArray(new Ergebnis[ergebnisList.size()]);
			data.add(stimmbezirk);
		}
		parser.close();
	}

	public List<Stimmbezirk> getData() {
		return data;
	}
}
