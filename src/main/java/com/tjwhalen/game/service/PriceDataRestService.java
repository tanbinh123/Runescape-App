package com.tjwhalen.game.service;

import java.util.List;

import com.tjwhalen.game.service.model.ItemSummary;
import com.tjwhalen.game.service.model.PriceData;

public interface PriceDataRestService {
	public List<PriceData> getPrices(List<ItemSummary> itemSummarys, int timeId);
}
