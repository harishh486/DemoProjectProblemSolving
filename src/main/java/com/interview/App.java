package com.interview;


import java.util.ArrayList;
import java.util.List;

public class App {

    private static List<Item> items = null;

    public static void main(String[] args) {


        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        updateQuality();
    }

    public static void updateQuality() {
        for (Item item : items) {
            updateQuality(item);
        }
    }


    private static void updateQuality(Item item) {

        if (AppUtils.Sulfuras(item))
            return;

        updateQualityBeforeSellDate(item);

        decreaseDaysToSellDate(item);

        updateQualityAfterSellDate(item);
    }
    // Aged Brie actually increases in Quality as it ages else decrease the quality of regular items
    private static void updateQualityBeforeSellDate(Item item) {
        if (AppUtils.Special(item)) {
            updateQualityOfSpecial(item);
        } else {
            decreaseQualityOfRegular(item);
        }
    }

    private static void decreaseDaysToSellDate(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private static void updateQualityAfterSellDate(Item item) {
        if (!AppUtils.afterSellDate(item))
            return;

        if (AppUtils.agedBrie(item)) {
            increaseQualityByOne(item);
        } else {
            decreaseQuality(item);

        }
    }

    // Aged Brie actually increases in Quality as it ages.
    private static void updateQualityOfSpecial(Item item) {
        increaseQualityByOne(item);

        if (AppUtils.backStagePasses(item)) {
            updateBackstagePassesQuality(item);
        }
    }

   /* Backstage passeslike aged brie, increases in Quality as its Sell-In value approaches; Quality increases by 2
    when there are 10 days or less and by 3 when there are 5 days or less but Quality drops to 0 after the
    concert.*/

    private static void updateBackstagePassesQuality(Item item) {
        if (item.getSellIn() < 11) {
            increaseQualityByOne(item);
        }

        if (item.getSellIn() < 6) {
            increaseQualityByOne(item);
        }
    }
   //  The Quality of an item is never more than 50.
    private static void increaseQualityByOne(Item item) {
        if (AppUtils.doesNotHaveMaximumQuality(item)) {
            item.setQuality(item.getQuality() + 1);
        }
    }
    //Conjured items degrade in Quality twice as fast as normal items

    //Once the Sell-In date has passed, Quality degrades twice as fast.

    private static void decreaseQualityOfRegular(Item item) {
        decreaseQualityByOne(item);

        if (AppUtils.Conjured(item)) {
            decreaseQualityByOne(item);
        }
    }
     // Once the Sell-In date has passed, Quality degrades twice as fast.
    private static void decreaseQuality(Item item) {
        if (AppUtils.backStagePasses(item)) {
            decreaseQualityToZero(item);
        } else {
            decreaseQualityOfRegular(item);
        }
    }

    private static void decreaseQualityToZero(Item item) {
        item.setQuality(item.getQuality() - item.getQuality());
    }


   // The Quality of an item is never negative.
    private static void decreaseQualityByOne(Item item) {
        if (AppUtils.stillHasQuality(item)) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    public static void addItem(Item item) {
        if (items == null)
            items = new ArrayList<Item>();
        items.add(item);
    }

    public static int itemsSize() {
        return items.size();
    }



}