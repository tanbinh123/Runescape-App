package com.tjwhalen.game.service.impl;

import java.util.Collections;
import java.util.List;

import com.tjwhalen.game.service.PriceDataTimeService;
import com.tjwhalen.game.service.dao.PriceDataTimeMapper;
import com.tjwhalen.game.service.model.PriceDataTime;

public class PriceDataTimeServiceImpl implements PriceDataTimeService {

	private PriceDataTimeMapper priceDataTimeMapper;
	private List<PriceDataTime> times = null;
	
	public PriceDataTimeServiceImpl(PriceDataTimeMapper priceDataTimeMapper) {
		this.priceDataTimeMapper = priceDataTimeMapper;
	}
	
	@Override
	public void writeTimes(List<PriceDataTime> times) {
		for (PriceDataTime time : times) {
			priceDataTimeMapper.insertTime(time);
		}
			
		times = null;
	}
	
	@Override
	public void writeTime(PriceDataTime time) {
		priceDataTimeMapper.insertTime(time);
		
		times = null;
	}

	@Override
	public List<PriceDataTime> lookupTimes() {
		if (times == null) {
			times = priceDataTimeMapper.selectTimes();
		}
		
		return times;
	}
	
	@Override
	public PriceDataTime lookupMostRecentTime() {
		if (times == null) {
			times = priceDataTimeMapper.selectTimes();
		}
		
		Collections.sort(times);
		
		return times.get(times.size()-1);
	}

}
