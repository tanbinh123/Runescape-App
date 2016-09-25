package com.tjwhalen.game.loader;

import com.tjwhalen.game.service.ItemSummaryDbService;

public abstract class AbstractLoader {
	
	//in this run method, get all loaders that need to be run from yaml, then make abstract funciton 'load' and call on all loaders in the profile
	public abstract void loadData();
	
	
	protected ItemSummaryDbService itemSummaryDbService;

	
	
	//////////////
	// Services
	////////////
	
	public ItemSummaryDbService getItemSummaryDbService() {
		return itemSummaryDbService;
	}
	public void setItemSummaryDbService(ItemSummaryDbService itemSummaryDbService) {
		this.itemSummaryDbService = itemSummaryDbService;
	}
}
