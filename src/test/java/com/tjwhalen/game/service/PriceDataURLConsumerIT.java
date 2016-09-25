package com.tjwhalen.game.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.tjwhalen.game.service.dao.PriceDataURLConsumer;
import com.tjwhalen.game.service.model.PriceData;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("load-price-data")
public class PriceDataURLConsumerIT {

	@Autowired
	PriceDataURLConsumer consumer;
	
	
	@Test
	public void test() {
		PriceData priceData = consumer.consumePrice(6);
		System.out.println("\n" + priceData);
		
		assertTrue(priceData.getOverall() > 0);
		assertTrue(priceData.getBuying() > 0);
		assertTrue(priceData.getBuyingQuantity() > 0);
		assertTrue(priceData.getSelling() > 0);
		assertTrue(priceData.getSellingQuantity() > 0);
	}

}
