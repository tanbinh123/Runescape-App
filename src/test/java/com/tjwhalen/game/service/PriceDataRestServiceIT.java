package com.tjwhalen.game.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.tjwhalen.game.service.model.ItemSummary;
import com.tjwhalen.game.service.model.PriceData;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("load-price-data")
public class PriceDataRestServiceIT {
	
	@Autowired 
	PriceDataRestService service;
	
	/**
	 * Give service list of know items and make sure items with fields come back > 0 (which means they grabbed something)
	 */
	@Test
	public void test() {
		
		List<ItemSummary> summaries = new ArrayList<ItemSummary>();
		
		summaries.add(new ItemSummary(2, "two", false));
		summaries.add(new ItemSummary(6, "six", false));
		summaries.add(new ItemSummary(10, "ten", false));
		summaries.add(new ItemSummary(4151, "4K one five one", false));
		
		
		List<PriceData> prices = service.getPrices(summaries, 4);
		
		assertEquals(summaries.size(), prices.size());
		
		for (PriceData price : prices) {
			assertTrue(price.getBuying() > 0);
			assertTrue(price.getBuyingQuantity() > 0);
			assertTrue(price.getSelling() > 0);
			assertTrue(price.getSellingQuantity() > 0);
			assertTrue(price.getOverall() > 0);
			System.out.println(price);
		}
	}

}
