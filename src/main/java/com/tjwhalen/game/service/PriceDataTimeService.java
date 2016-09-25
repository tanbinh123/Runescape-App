package com.tjwhalen.game.service;

import java.util.List;

import com.tjwhalen.game.service.model.PriceDataTime;

public interface PriceDataTimeService {
	public void writeTimes(List<PriceDataTime> times);
	public List<PriceDataTime> lookupTimes();
	public PriceDataTime lookupMostRecentTime();
	void writeTime(PriceDataTime time);
}
