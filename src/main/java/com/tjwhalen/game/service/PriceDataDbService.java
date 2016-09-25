package com.tjwhalen.game.service;

import java.util.List;

import com.tjwhalen.game.service.model.PriceData;

public interface PriceDataDbService {

	public void writePrices(List<PriceData> prices);
	public List<PriceData> lookupPrices(int priceDataTimeId);
}
