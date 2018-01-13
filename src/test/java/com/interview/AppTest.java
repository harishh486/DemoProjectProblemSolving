package com.interview;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {

	private void assertItemsAreEqual(Item item, Item otherItem) {
		assertTrue(item.name.equals(otherItem.name));
		assertTrue(item.Quality == otherItem.Quality);
		assertTrue(item.Sellin == otherItem.Sellin);
	}

	private void increaseDays(int numberOfDays) {
		for (int i = 0; i < numberOfDays; ++i) {
			App.updateQuality();
		}
	}

	private void assertItemsQuality(int quality, Item item)
    {
		assertEquals(quality, item.getQuality());
	}

	@Test
	public void sellInWorkings() {
		Item dummyitem = new Item("dummyitem", 2, 0);
		App.addItem(dummyitem);

        increaseDays(10);

		assertEquals(-8, dummyitem.getSellIn());

		assertItemsQuality(0, dummyitem);
	}

	@Test
	public void agedBrieQualityIncreasesByOneEachDayBeforeSellDateAndByTwoFromSellDateOn() {
		Item agedBrie = new Item("Aged Brie", 2, 0);
		App.addItem(agedBrie);

        increaseDays(4);

        assertEquals(-2, agedBrie.getSellIn());

		assertItemsQuality(6, agedBrie);
	}

	@Test
	public void qualityCannotBeMoreThanFifty() {
		Item agedBrie = new Item("Aged Brie", 2, 0);
		App.addItem(agedBrie);

        increaseDays(100);

		assertItemsQuality(50, agedBrie);
	}

	@Test
	public void sulfurasCannotChange() {
		Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
		App.addItem(sulfuras);

        increaseDays(10);

        assertEquals(0, sulfuras.getSellIn());

		assertItemsQuality(80, sulfuras);
	}

	@Test
	public void backstagePassesQualityIncreasesByOneEachDayBeforeTenDaysFromSellDate() {
		Item backstagePasses = new Item(
				"Backstage passes to a TAFKAL80ETC concert", 15, 20);
		App.addItem(backstagePasses);

        increaseDays(5);

		assertItemsQuality(25, backstagePasses);
	}

	@Test
	public void backstagePassesQualityIncreasesByTwoEachDayBetweenTenAndFiveDaysBeforeSellDate() {
		Item backstagePasses = new Item(
				"Backstage passes to a TAFKAL80ETC concert", 15, 20);
		App.addItem(backstagePasses);

        increaseDays(10);

		assertItemsQuality(35, backstagePasses);
	}

	@Test
	public void backstagePassesQualityIncreasesByThreeEachDayBetweenFiveDaysFromSellDateAndSellDate() {
		Item backstagePasses = new Item(
				"Backstage passes to a TAFKAL80ETC concert", 15, 20);
		App.addItem(backstagePasses);

        increaseDays(15);

		assertItemsQuality(50, backstagePasses);
	}


	@Test
	public void backstagePassesQualityIsZeroAfterTheSellDate() {
		Item backstagePasses = new Item(
				"Backstage passes to a TAFKAL80ETC concert", 15, 20);
		App.addItem(backstagePasses);

        increaseDays(16);

		assertItemsQuality(0, backstagePasses);
	}

	@Test
	public void regularItemsQualityDecreasesByOneEachDayBeforeSellDate() {
		Item regularItem = new Item("+5 Dexterity Vest", 10, 20);
		App.addItem(regularItem);

        increaseDays(10);

		assertItemsQuality(10, regularItem);
	}

	@Test
	public void regularItemsQualityDecreasesByTwoEachDayAfterSellDate() {
		Item regularItem = new Item("+5 Dexterity Vest", 10, 20);
		App.addItem(regularItem);

        increaseDays(15);

		assertItemsQuality(0, regularItem);
	}

	@Test
	public void regularItemsQualityCannotBeLessThanZero() {
		Item regularItem = new Item("+5 Dexterity Vest", 10, 20);
		App.addItem(regularItem);

        increaseDays(30);

		assertItemsQuality(0, regularItem);
	}

	@Test
	public void conjuredItemsQualityDecreasesByTwoEachDayBeforeSellDate() {
		Item conjuredItem = new Item("Conjured Mana Cake", 3, 6);
		App.addItem(conjuredItem);

        increaseDays(3);

		assertItemsQuality(0, conjuredItem);
	}

	@Test
	public void conjuredItemsQualityDecreasesByFourEachDayAfterSellDate() {
		Item conjuredItem = new Item("Conjured Mana Cake", 5, 18);
		App.addItem(conjuredItem);

        increaseDays(7);

		assertItemsQuality(0, conjuredItem);
	}

	@Test
	public void conjuredItemsQualityCannotBeLessThanZero() {
		Item conjuredItem = new Item("Conjured Mana Cake", 5, 18);
		App.addItem(conjuredItem);

        increaseDays(200);

		assertItemsQuality(0, conjuredItem);
	}
}
