package com.tjwhalen.game.report.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjwhalen.game.report.AbstractReport;
import com.tjwhalen.game.report.model.Margin;
import com.tjwhalen.game.service.ItemSummaryDbService;
import com.tjwhalen.game.service.PriceDataDbService;
import com.tjwhalen.game.service.PriceDataTimeService;
import com.tjwhalen.game.service.model.PriceData;
import com.tjwhalen.game.service.model.PriceDataTime;

public class MarginFinder extends AbstractReport {

	private final static Logger logger = LoggerFactory.getLogger(MarginFinder.class);
	
	private SimpleDateFormat df = new  SimpleDateFormat("YYYY-MM-dd hh-mm");
	
	private PriceDataDbService priceDataDbService;
	private PriceDataTimeService priceDataTimeService;
	private ItemSummaryDbService itemSummaryDbService;
	

	@Override
	public void generateReport() {
		logger.info("Start MarginFinder generateReport()");
		
		PriceDataTime mostRecentPriceTime = priceDataTimeService.lookupMostRecentTime();
		
		logger.info("Getting results");
		List<PriceData> prices = priceDataDbService.lookupPrices(mostRecentPriceTime.getId());
		List<Margin> margins = getSortedListMargins(prices, mostRecentPriceTime.getMarketCaptureTime());
		
		
		String file = outputFileLocation + "MarginFinder_" + df.format(mostRecentPriceTime.getMarketCaptureTime()) + ".csv";
		logger.info("MarginFinder: Writing results to file " + file);
		writeMargins(margins, file);
		
	}
	
	private List<Margin> getSortedListMargins(List<PriceData> prices, Timestamp time) {
		
		List<Margin> margins = new ArrayList<Margin>();
		String name;
		
		for (PriceData price : prices) {
			name = itemSummaryDbService.lookupItemNameWithId(price.getGeItemListId());
			margins.add(new Margin(time, name, price));
		}
		
		Collections.sort(margins);
		
		return margins;
	}
	
	private void writeMargins(List<Margin> margins, String file) {
		StringBuffer output = new StringBuffer();
		
		output.append("Time,Id,Name,Selling(gp),Buying(gp),Margin(%),Margin(gp),Quantity,Buy Quantity,Sell Quantity").append("\n");
		for (Margin margin : margins) {
			output.append(margin);
			output.append("\n");
		}
		
		writeSring(output.toString(), file);
	}
	
	public PriceDataDbService getPriceDataDbService() {
		return priceDataDbService;
	}
	public void setPriceDataDbService(PriceDataDbService priceDataDbService) {
		this.priceDataDbService = priceDataDbService;
	}

	public PriceDataTimeService getPriceDataTimeService() {
		return priceDataTimeService;
	}
	public void setPriceDataTimeService(PriceDataTimeService priceDataTimeService) {
		this.priceDataTimeService = priceDataTimeService;
	}
	public ItemSummaryDbService getItemSummaryDbService() {
		return itemSummaryDbService;
	}
	public void setItemSummaryDbService(ItemSummaryDbService itemSummaryDbService) {
		this.itemSummaryDbService = itemSummaryDbService;
	}
	
}

