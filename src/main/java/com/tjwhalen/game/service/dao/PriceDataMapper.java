package com.tjwhalen.game.service.dao;

import java.util.List;

import com.tjwhalen.game.service.model.PriceData;

public interface PriceDataMapper {

	public void insertPrice(PriceData priceData);
	public List<PriceData> selectPrices();
	public List<PriceData> selectPricesWithPriceDataTimeId(int priceDataTimeId);
}
