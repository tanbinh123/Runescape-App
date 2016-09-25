package com.tjwhalen.game.service;

import java.util.Collection;
import java.util.List;

import com.tjwhalen.game.service.model.ItemSummary;

public interface ItemSummaryRestService {

	//public List<ItemSummaryContainer> getItemsContainers();
	public Collection<ItemSummary> getItems();
}
