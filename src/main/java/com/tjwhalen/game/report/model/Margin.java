package com.tjwhalen.game.report.model;

import java.sql.Timestamp;

import com.tjwhalen.game.service.model.PriceData;

public class Margin implements Comparable<Margin> {
	
	private Timestamp timeStamp;
	private String name;
	private double marginPercent;
	private int marginGp;
	private PriceData priceData;

	public Margin(Timestamp timeStamp, String name, PriceData priceData) {
		super();
		this.timeStamp = timeStamp;
		this.name = name;
		this.priceData = priceData;
		this.marginPercent = calcMarginPercent();
		this.marginGp = calcMarginGp();
	}
	
	private double calcMarginPercent() {
		double margin;
		if (priceData.getBuying() == 0) {
			margin = 0.0;
		} else {
			margin = (priceData.getSelling() - priceData.getBuying())/priceData.getBuying() * 100;
		}
		
		return margin;
	}

	private int calcMarginGp() {
		//Can't buy it, so no margin possible
		if (priceData.getBuying() == 0) {
			return 0;
		}
		
		return priceData.getSelling() - priceData.getBuying();
	}
	
	private int totalQuantity() {
		return priceData.getBuyingQuantity() + priceData.getSellingQuantity();
	}
	
	
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMarginPercent() {
		return marginPercent;
	}
	public void setMarginPercent(double marginPercent) {
		this.marginPercent = marginPercent;
	}
	public int getMarginGp() {
		return marginGp;
	}
	public void setMarginGp(int marginGp) {
		this.marginGp = marginGp;
	}
	public PriceData getPriceData() {
		return priceData;
	}
	public void setPriceData(PriceData priceData) {
		this.priceData = priceData;
	}

	@Override
	public String toString() {
		return timeStamp + "," + priceData.getGeItemListId() +  "," + name + "," + priceData.getSelling() + "," + priceData.getBuying() + "," + marginPercent + ","
				+ marginGp + "," +  + totalQuantity() + "," + priceData.getBuyingQuantity() + "," + priceData.getSellingQuantity();
	}

	//Sorts descending
	@Override
	public int compareTo(Margin o) {
		if (this.marginPercent < o.getMarginPercent()) {
			return 1;
		} else if (this.marginPercent > o.getMarginPercent()) {
			return -1;
		} else { 
			return 0;
		}
	}
	
}
