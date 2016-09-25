package com.tjwhalen.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjwhalen.game.service.ItemSummaryDbService;
import com.tjwhalen.game.service.dao.ItemSummaryMapper;
import com.tjwhalen.game.service.model.ItemSummary;

@Service("itemSummaryDbService")
public class ItemSummaryDbServiceImpl implements ItemSummaryDbService {

	private ItemSummaryMapper itemSummaryMapper;
	
	public ItemSummaryDbServiceImpl(ItemSummaryMapper itemSummaryMapper) {
		this.itemSummaryMapper = itemSummaryMapper;
	}
	
	
	public void writeItemSummarys(List<ItemSummary> itemSummarys) {
		
		for (ItemSummary itemSummary : itemSummarys) {
			itemSummaryMapper.insertItemSummary(itemSummary);
		}
	}
	
	public List<ItemSummary> lookupItemSummarys() {
		return itemSummaryMapper.selectItemSummarys();
	}


	@Override
	public String lookupItemNameWithId(int id) {
		ItemSummary item = itemSummaryMapper.selectItemSummaryWithId(id);
		return item.getName();
	}

}
