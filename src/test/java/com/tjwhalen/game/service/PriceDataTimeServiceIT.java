package com.tjwhalen.game.service;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tjwhalen.game.service.model.PriceDataTime;

/**
 * Test only work on an empty table
 * 
 * @author tjwhalen
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceDataTimeServiceIT {

	private final static Logger logger = LoggerFactory.getLogger(PriceDataTimeServiceIT.class);
	@Autowired
	private PriceDataTimeService priceDataTimeService;
	
	@Test
	public void test() {
		List<PriceDataTime> datesIn = new ArrayList<PriceDataTime>();
		Timestamp mostRecentAnswer = null;
		
		try {
			datesIn.add(new PriceDataTime(new Timestamp(System.currentTimeMillis())));
			Thread.sleep(1000L);
			datesIn.add(new PriceDataTime(new Timestamp(System.currentTimeMillis())));
			Thread.sleep(1000L);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		priceDataTimeService.writeTimes(datesIn);
		
		try {
			priceDataTimeService.writeTime(new PriceDataTime(new Timestamp(System.currentTimeMillis())));
			Thread.sleep(5000L);
			priceDataTimeService.writeTime(new PriceDataTime((mostRecentAnswer = new Timestamp(System.currentTimeMillis()))));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		List<PriceDataTime> datesOut = priceDataTimeService.lookupTimes();
		assertTrue(4 == datesOut.size());
		
		PriceDataTime mostRecentOutput = priceDataTimeService.lookupMostRecentTime();
		logger.info("mostRecentOutput from table: " + mostRecentOutput);
		
		assertTrue(mostRecentOutput.getMarketCaptureTime().equals(mostRecentAnswer));
	}

}
