package com.wastemanagement.utils;

import com.wastemanagement.model.WasteItem;

import java.util.Comparator;
import java.util.List;

public class SortingUtils {

    public static final Comparator<WasteItem> BY_WEIGHT =
            (a, b) -> Double.compare(a.getWeight(), b.getWeight());

    public static final Comparator<WasteItem> BY_RECYCLING_COST =
            (a, b) -> Double.compare(a.calculateRecyclingCost(), b.calculateRecyclingCost());

    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
    }

    public static void sortByWeight(List<WasteItem> list) {
        sort(list, BY_WEIGHT);
    }

    public static List<WasteItem> filterRecyclable(List<WasteItem> list) {
        return list.stream().filter(WasteItem::isRecyclable).toList();
    }
}