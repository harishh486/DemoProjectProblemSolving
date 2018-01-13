package com.interview;


public class AppUtils {

   public static boolean Sulfuras(Item item) {
        return "Sulfuras, Hand of Ragnaros".equals(item.getName());
    }

    public static boolean Conjured(Item item) {
        return item.getName().contains("Conjured");
    }

    public static boolean Special(Item item) {
        return agedBrie(item) || backStagePasses(item);
    }

    public static boolean agedBrie(Item item) {
        return "Aged Brie".equals(item.getName());
    }

    public static boolean afterSellDate(Item item) {
        return item.getSellIn() < 0;

    }
    public static boolean backStagePasses(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item
                .getName());
    }

    public static boolean doesNotHaveMaximumQuality(Item item) {
        return item.getQuality() < 50;

    }
    public static boolean stillHasQuality(Item item) {
        return item.getQuality() > 0;
    }


}
