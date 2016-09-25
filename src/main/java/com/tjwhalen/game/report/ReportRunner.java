package com.tjwhalen.game.report;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportRunner {

	private final static Logger logger = LoggerFactory.getLogger(ReportRunner.class);
	private List<AbstractReport> reports;
	
	public void setReports(List<AbstractReport> reports) {
		this.reports = reports;
	}
	
	public void execute() {
		if (reports == null) {
			logger.warn("No reports specified");
		} else {
			for (AbstractReport report : reports) {
				report.generateReport();
			}
		}
		
	}
}
