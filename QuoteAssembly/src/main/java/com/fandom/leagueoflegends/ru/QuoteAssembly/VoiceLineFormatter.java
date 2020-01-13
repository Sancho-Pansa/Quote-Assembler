package com.fandom.leagueoflegends.ru.QuoteAssembly;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class VoiceLineFormatter {
	
	private Reader in;
	
	private String currentHeader = "";
	private String currentSubHeader = "Нет";
	
	public VoiceLineFormatter() {
		
	}
	
	public void openCSV(String path) throws FileNotFoundException {
		in = new FileReader(path);
	}
	
	public String formatCSV() {
		StringBuilder result = new StringBuilder("");
		
		try {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().parse(in);
			for(CSVRecord x : records) {
				result.append(processLine(x));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		return result.toString();
	}
	
	private String processLine(CSVRecord CSVLine) {
		StringBuilder block = new StringBuilder("");
		if(CSVLine.get("Раздел").equals(currentHeader)) {
			if(CSVLine.get("Подраздел").equals(currentSubHeader)) {
				block.append("* {{фч|Афелий.Классический")
				.append(CSVLine.get("Файл"))
				.append(".ogg|")
				.append(CSVLine.get("Транскрипция"))
				.append("}}\r\n");
			} else {
				this.currentSubHeader = CSVLine.get("Подраздел");
				block.append("\r\n;")
				.append(CSVLine.get("Подраздел"))
				.append("\r\n")
				.append(processLine(CSVLine));
			}
		} else {
			this.currentHeader = CSVLine.get("Раздел");
			this.currentSubHeader = "Нет";
			block.append("\r\n== ")
			.append(CSVLine.get("Раздел"))
			.append(" ==\r\n")
			.append(processLine(CSVLine));
		}
			
		return block.toString();
	}
}
