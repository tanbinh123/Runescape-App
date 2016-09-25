package com.tjwhalen.game.service.dao;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tjwhalen.game.service.model.PriceData;

public class PriceDataURLConsumer {

	private RestTemplate restTemplate;
	private String url;
	
	public PriceDataURLConsumer(RestTemplate restTemplate, String url) {
		super();
		this.restTemplate = restTemplate;
		this.url = url;
	}
	
	public PriceData consumePrice(int id) {
		
		ResponseEntity<PriceData> priceDataResponse =
				restTemplate.exchange(url + id, HttpMethod.GET, null, new ParameterizedTypeReference<PriceData>() {
					
				});
		
		return priceDataResponse.getBody();
	}
	
}
