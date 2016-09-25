package com.tjwhalen.game.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemSummary {

	private Integer id;
	private String name;
	private boolean members;
	
	public ItemSummary() {
		
	}
	public ItemSummary(Integer id, String name, Boolean members) {
		super();
		this.id = id;
		this.name = name;
		this.members = members;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getMembers() {
		return members;
	}
	public void setMembers(boolean members) {
		this.members = members;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemSummary other = (ItemSummary) obj;
		if (id != other.id)
			return false;
		if (members != other.members)
			return false;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}	
}
