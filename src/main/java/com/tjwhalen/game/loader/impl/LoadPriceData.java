package com.tjwhalen.game.loader.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjwhalen.game.loader.AbstractLoader;
import com.tjwhalen.game.service.PriceDataDbService;
import com.tjwhalen.game.service.PriceDataRestService;
import com.tjwhalen.game.service.PriceDataTimeService;
import com.tjwhalen.game.service.model.ItemSummary;
import com.tjwhalen.game.service.model.PriceData;
import com.tjwhalen.game.service.model.PriceDataTime;

public class LoadPriceData extends AbstractLoader {

	private final static Logger logger = LoggerFactory.getLogger(LoadPriceData.class);
	
	private PriceDataRestService priceDataRestService;
	private PriceDataDbService priceDataDbService;
	private PriceDataTimeService priceDataTimeService;
	
	@Override
	public void loadData() {
		logger.info("Starting loader LoadPriceData");
		
		logger.info("Lookup item list from database");
		List<ItemSummary> items = itemSummaryDbService.lookupItemSummarys();
		if (items.size() == 0) {
			logger.error("No items stored in database, run profile load-items ... exiting)");
			System.exit(1);
		}
		
		logger.info("Store current snapshot time in database, and retrieve it's id");
		int timeId = getCurrentTimeId();
		
		logger.info("Get price data for each item from the URL API");
		List<PriceData> prices = priceDataRestService.getPrices(items, timeId);
		
		logger.info("Write price data to database");
		priceDataDbService.writePrices(prices);
		
		logger.info("Finished loader LoadPriceData");
	}

	private int getCurrentTimeId() {
		PriceDataTime currentTime = new PriceDataTime(new Timestamp(System.currentTimeMillis()));
		priceDataTimeService.writeTime(currentTime);
		
		return priceDataTimeService.lookupMostRecentTime().getId();
	}
	
	
	public PriceDataRestService getPriceDataRestService() {
		return priceDataRestService;
	}
	public void setPriceDataRestService(PriceDataRestService priceDataRestService) {
		this.priceDataRestService = priceDataRestService;
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
	
}
