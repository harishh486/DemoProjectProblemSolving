package com.interview;

public class Item {
    public String name;
	public int Sellin;
    public int Quality;
    
    public Item(String name, int sellIn, int quality) {
		this.setName(name);
		this.setSellIn(sellIn);
		this.setQuality(quality);
	}

    public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getSellIn() {

    	return Sellin;
	}
	public void setSellIn(int sellIn) {

    	this.Sellin = sellIn;
	}
	public int getQuality() {

    	return Quality;
	}
	public void setQuality(int quality) {

    	this.Quality = quality;
	}
}
