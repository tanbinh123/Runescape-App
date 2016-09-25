package com.tjwhalen.game.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceData {
	
	private Integer geItemListId;
	private Integer priceDataTimeId;
	private Integer overall;
	private Integer buying;
	private Integer buyingQuantity;
	private Integer selling;
	private Integer sellingQuantity;
	
	public PriceData() {
		
	}
	
	public PriceData(Integer geItemListId, Integer priceDataTimeId, Integer overall, Integer buying, Integer buyingQuantity, Integer selling,
			Integer sellingQuantity) {
		super();
		this.geItemListId = geItemListId;
		this.priceDataTimeId = priceDataTimeId;
		this.overall = overall;
		this.buying = buying;
		this.buyingQuantity = buyingQuantity;
		this.selling = selling;
		this.sellingQuantity = sellingQuantity;
	}
	
	public Integer getGeItemListId() {
		return geItemListId;
	}
	public void setGeItemListId(Integer geItemListId) {
		this.geItemListId = geItemListId;
	}
	public Integer getPriceDataTimeId() {
		return priceDataTimeId;
	}
	public void setPriceDataTimeId(Integer priceDataTimeId) {
		this.priceDataTimeId = priceDataTimeId;
	}
	public Integer getOverall() {
		return overall;
	}
	public void setOverall(Integer overall) {
		this.overall = overall;
	}
	public Integer getBuying() {
		return buying;
	}
	public void setBuying(Integer buying) {
		this.buying = buying;
	}
	public Integer getBuyingQuantity() {
		return buyingQuantity;
	}
	public void setBuyingQuantity(Integer buyingQuantity) {
		this.buyingQuantity = buyingQuantity;
	}
	public int getSelling() {
		return selling;
	}
	public void setSelling(Integer selling) {
		this.selling = selling;
	}
	public Integer getSellingQuantity() {
		return sellingQuantity;
	}
	public void setSellingQuantity(Integer sellingQuantity) {
		this.sellingQuantity = sellingQuantity;
	}

	@Override
	public String toString() {
		return "PriceData [geItemListId=" + geItemListId + ", priceDataTimeId=" + priceDataTimeId + ", overall="
				+ overall + ", buyIng=" + buying + ", buyingQuantity=" + buyingQuantity + ", selling=" + selling
				+ ", sellingQuantity=" + sellingQuantity + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PriceData other = (PriceData) obj;
		if (buying == null) {
			if (other.buying != null) {
				return false;
			}
		} else if (!buying.equals(other.buying)) {
			return false;
		}
		if (buyingQuantity == null) {
			if (other.buyingQuantity != null) {
				return false;
			}
		} else if (!buyingQuantity.equals(other.buyingQuantity)) {
			return false;
		}
		if (geItemListId == null) {
			if (other.geItemListId != null) {
				return false;
			}
		} else if (!geItemListId.equals(other.geItemListId)) {
			return false;
		}
		if (overall == null) {
			if (other.overall != null) {
				return false;
			}
		} else if (!overall.equals(other.overall))
			return false;
		if (priceDataTimeId == null) {
			if (other.priceDataTimeId != null) {
				return false;
			}
		} else if (!priceDataTimeId.equals(other.priceDataTimeId)) {
			return false;
		}
		if (selling == null) {
			if (other.selling != null) {
				return false;
			}
		} else if (!selling.equals(other.selling)) {
			return false;
		}
		if (sellingQuantity == null) {
			if (other.sellingQuantity != null) {
				return false;
			}
		} else if (!sellingQuantity.equals(other.sellingQuantity)) {
			return false;
		}
		return true;
	}
	
	
	
}
