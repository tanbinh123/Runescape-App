package com.tjwhalen.game.service.dao;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tjwhalen.game.service.model.ItemSummary;

public class ItemSummaryURLConsumer {

	private RestTemplate restTemplate;
	private String url;

	public ItemSummaryURLConsumer(RestTemplate restTemplate, String url) {
		this.restTemplate = restTemplate;
		this.url = url;
	}
	
	public Map<Integer, ItemSummary> consumeItems() {
		
//		ResponseEntity<List<ItemSummaryContainer>> itemSummaryResponse =
//				restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ItemSummaryContainer>>() {
//					
//				});
		ResponseEntity<Map<Integer, ItemSummary>> itemSummaryResponse =
				restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<Integer, ItemSummary>>() {
					
				});
		
		return itemSummaryResponse.getBody();
	}
}




//ResponseEntity<List<Rate>> rateResponse =
//restTemplate.exchange("https://bitpay.com/api/rates",
//            HttpMethod.GET, null, new ParameterizedTypeReference<List<Rate>>() {
//    });
//List<Rate> rates = rateResponse.getBody();