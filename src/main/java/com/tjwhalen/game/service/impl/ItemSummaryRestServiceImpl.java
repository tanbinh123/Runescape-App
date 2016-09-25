package com.tjwhalen.game.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.tjwhalen.game.service.ItemSummaryRestService;
import com.tjwhalen.game.service.dao.ItemSummaryMapper;
import com.tjwhalen.game.service.dao.ItemSummaryURLConsumer;
import com.tjwhalen.game.service.model.ItemSummary;

public class ItemSummaryRestServiceImpl implements ItemSummaryRestService{

	ItemSummaryURLConsumer itemSummaryURLConsumer;
	
	public ItemSummaryRestServiceImpl(ItemSummaryURLConsumer itemSummaryURLConsumer) {
		this.itemSummaryURLConsumer = itemSummaryURLConsumer;
	}
	
//	public List<ItemSummaryContainer> getItemsContainers() {
//		return itemSummaryURLConsumer.consumeItems();
//	}
	
	public Collection<ItemSummary> getItems() {
		Map<Integer, ItemSummary> containers = itemSummaryURLConsumer.consumeItems();
		List<ItemSummary> items = new ArrayList<ItemSummary>();
		
//		for(ItemSummaryContainer container : containers) {
//			items.add(container.getItemSummary());
//		}
		
		return containers.values();
	}
	
	
//	public ItemSummaryURLConsumer getItemSummaryURLConsumer() {
//		return itemSummaryURLConsumer;
//	}
//	public void setItemSummaryURLConsumer(ItemSummaryURLConsumer itemSummaryURLConsumer) {
//		this.itemSummaryURLConsumer = itemSummaryURLConsumer;
//	}	
}
