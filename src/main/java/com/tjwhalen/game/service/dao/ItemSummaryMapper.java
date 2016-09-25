package com.tjwhalen.game.service.dao;

import java.util.List;

import com.tjwhalen.game.service.model.ItemSummary;

public interface ItemSummaryMapper {

	public void insertItemSummary(ItemSummary itemSummary);
	public List<ItemSummary> selectItemSummarys();
	public ItemSummary	selectItemSummaryWithId(int id);
}
