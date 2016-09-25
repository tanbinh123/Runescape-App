package com.tjwhalen.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tjwhalen.game.service.PriceDataRestService;
import com.tjwhalen.game.service.dao.PriceDataURLConsumer;
import com.tjwhalen.game.service.model.ItemSummary;
import com.tjwhalen.game.service.model.PriceData;

//TODO Multithread price URL consuming???, took ~10 minutes to grab all prices
public class PriceDataRestServiceImpl implements PriceDataRestService {

	private PriceDataURLConsumer priceDataURLConsumer;
	
	public PriceDataRestServiceImpl(PriceDataURLConsumer priceDataURLConsumer) {
		this.priceDataURLConsumer = priceDataURLConsumer;
	}
	
	@Override
	public List<PriceData> getPrices(List<ItemSummary> itemSummarys, int timeId) {
		List<PriceData> prices = new ArrayList<PriceData>();
		PriceData priceData;
		
		for (ItemSummary itemSummary : itemSummarys) {
			priceData = priceDataURLConsumer.consumePrice(itemSummary.getId());
			priceData.setGeItemListId(itemSummary.getId());
			priceData.setPriceDataTimeId(timeId);
			prices.add(priceData);
		}
		
		return prices;
	}

}
