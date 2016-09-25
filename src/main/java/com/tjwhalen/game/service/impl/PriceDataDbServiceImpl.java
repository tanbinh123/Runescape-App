package com.tjwhalen.game.service.impl;

import java.util.List;

import com.tjwhalen.game.service.PriceDataDbService;
import com.tjwhalen.game.service.dao.PriceDataMapper;
import com.tjwhalen.game.service.model.PriceData;

public class PriceDataDbServiceImpl implements PriceDataDbService {

	PriceDataMapper priceDataMapper;
	
	public PriceDataDbServiceImpl(PriceDataMapper priceDataMapper) {
		this.priceDataMapper = priceDataMapper;
	}
	
	@Override
	public void writePrices(List<PriceData> prices) {
		for (PriceData price : prices) {
			priceDataMapper.insertPrice(price);
		}
	}

	@Override
	public List<PriceData> lookupPrices(int priceDataTimeId) {
		return priceDataMapper.selectPricesWithPriceDataTimeId(priceDataTimeId);
	}
	
	// TODO Make a function that returns a map, easier to do reports

}
