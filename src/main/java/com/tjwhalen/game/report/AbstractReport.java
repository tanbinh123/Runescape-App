package com.tjwhalen.game.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractReport {

	private final static Logger logger = LoggerFactory.getLogger(AbstractReport.class);
	
	protected String outputFileLocation;
	
	public abstract void generateReport();
	
	protected void writeSring(String output, String file) {

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(output);
		} catch (IOException e) {
			logger.error("Margin Finder writeStrings: " + e.getLocalizedMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				logger.error("Margin Finder writeStrings writer.close(): " + e.getLocalizedMessage());
			} catch(NullPointerException e) {
				logger.error("Margin Finder writeStrings writer.close(): " + e.getLocalizedMessage());
			}
		}
	}
	
	public void setOutputFileLocation(String path) {
		this.outputFileLocation = path;
	}
}
