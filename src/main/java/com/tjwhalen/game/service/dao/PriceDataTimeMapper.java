package com.tjwhalen.game.service.dao;

import java.util.List;

import com.tjwhalen.game.service.model.PriceDataTime;

public interface PriceDataTimeMapper {

	public void insertTime(PriceDataTime priceDataTime);
	public List<PriceDataTime> selectTimes();
}
