package com.tjwhalen.game.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tjwhalen.game.service.model.PriceData;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceDataDbServiceIT {

	@Autowired
	PriceDataDbService service;
	/**
	 * Test that PriceDataDbService inserts and selects data correctly
	 */
	@Test
	public void test() {
		List<PriceData> input = new ArrayList<PriceData>();
		
		input.add(new PriceData(1, 1, 1, 1, 1, 1, 1));
		input.add(new PriceData(2, 2, 2, 2, 2, 2, 2));
		input.add(new PriceData(3, 2, 3, 3, 3, 3, 3));
		input.add(new PriceData(4, 2, 4, 4, 4, 4, 4));
		input.add(new PriceData(5, 1, 5, 5, 5, 5, 5));
		
		service.writePrices(input);
		
		List<PriceData> output = service.lookupPrices(1);
		assertTrue(2 == output.size());
		assertTrue(output.contains(input.get(0)));
		assertTrue(output.contains(input.get(4)));
		
		output = service.lookupPrices(2);
		assertTrue(3 == output.size());
		assertTrue(output.contains(input.get(1)));
		assertTrue(output.contains(input.get(2)));
		assertTrue(output.contains(input.get(3)));
	}

}
