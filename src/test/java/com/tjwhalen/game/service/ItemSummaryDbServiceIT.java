package com.tjwhalen.game.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tjwhalen.game.service.model.ItemSummary;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemSummaryDbServiceIT {
	
	@Autowired
	ItemSummaryDbService itemSummaryDbService;

	/*
	 * Inserts fake items into table and then selects them all out and makes sure the lists are the same
	 * Will only pass if the table is empty
	 */
	@Test
	public void testInsertAndSelect() {
		//mapper = sqlSession.getMapper(ItemSummaryMapper.class);
		
		ArrayList<ItemSummary> input = new ArrayList<ItemSummary>();
		input.add(new ItemSummary(1, "one", false));
		input.add(new ItemSummary(2, "two", true));
		input.add(new ItemSummary(3, "three", false));
		input.add(new ItemSummary(4, "four", true));
		input.add(new ItemSummary(5, "five", false));
		input.add(new ItemSummary(6, "six", true));
		
		itemSummaryDbService.writeItemSummarys(input);
		
		ArrayList<ItemSummary> output = new ArrayList<ItemSummary>(itemSummaryDbService.lookupItemSummarys());
		
		
		/*
		 * Make sure lists are equal
		 */
		//make sure they are same size
		assertTrue(input.size() == output.size());
		
		//make sure all elements in input list are in output list
		for (ItemSummary itemSummary : input) {
			assertTrue(output.contains(itemSummary));
		}
		
		String name = itemSummaryDbService.lookupItemNameWithId(1);
		assertEquals("one", name);
		
	}

}
