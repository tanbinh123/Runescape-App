package com.tjwhalen.game.loader.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tjwhalen.game.loader.AbstractLoader;
import com.tjwhalen.game.service.ItemSummaryRestService;
import com.tjwhalen.game.service.model.ItemSummary;

@Component
public class LoadItems extends AbstractLoader {
	
	private final static Logger logger = LoggerFactory.getLogger(LoadItems.class);
	private ItemSummaryRestService itemSummaryRestService;
	
	public LoadItems() {
		
	}

	@Override
	public void loadData() {
		logger.info("Loading ge item list");
		
		List<ItemSummary> items = new ArrayList<ItemSummary>(itemSummaryRestService.getItems());
		
		logger.info("number of items is " + items.size());
		
		itemSummaryDbService.writeItemSummarys(items);
	}

	public ItemSummaryRestService itemSummaryService() {
		return itemSummaryRestService;
	}
	public void setItemSummaryRestService(ItemSummaryRestService itemSummaryRestService) {
		this.itemSummaryRestService = itemSummaryRestService;
	}
}
