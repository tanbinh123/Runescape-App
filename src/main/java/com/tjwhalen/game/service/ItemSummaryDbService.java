package com.tjwhalen.game.service;

import java.util.List;

import com.tjwhalen.game.service.model.ItemSummary;

public interface ItemSummaryDbService {
	public void writeItemSummarys(List<ItemSummary> itemSummarys);
	public List<ItemSummary> lookupItemSummarys();
	public String lookupItemNameWithId(int id);
}
