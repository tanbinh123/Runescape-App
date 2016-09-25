package com.tjwhalen.game.service.model;

import java.sql.Timestamp;

public class PriceDataTime implements Comparable<PriceDataTime> {

	private Integer id;
	private Timestamp marketCaptureTime;
	
	public PriceDataTime(Timestamp marketCaptureTime) {
		this.marketCaptureTime = marketCaptureTime;
		this.id = null;
	}
	public PriceDataTime(Integer id, Timestamp marketCaptureTime) {
		super();
		this.id = id;
		this.marketCaptureTime = marketCaptureTime;
	}
	
	@Override
	public int compareTo(PriceDataTime o) {
		return this.marketCaptureTime.compareTo(o.getMarketCaptureTime());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getMarketCaptureTime() {
		return marketCaptureTime;
	}
	public void setMarketCaptureTime(Timestamp marketCaptureTime) {
		this.marketCaptureTime = marketCaptureTime;
	}

	@Override
	public String toString() {
		return "PriceDataTime [id=" + id + ", marketCaptureTime=" + marketCaptureTime + "]";
	}
}
