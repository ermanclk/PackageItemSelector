package com;

import com.domain.Item;
import com.domain.PackageConfig;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PackageItemSelector {
    private PackageItemSelector() {
        throw new UnsupportedOperationException("Class cannot be instantiated");
    }

    /**
     * Calculates best set of items which has maximum value within the given package capacity.
     * <p>
     * Basically uses a brute force algorithm to select items,
     * calculates all available subsets of available items (by using Google Guava library for getting all combinations)
     * then just iterates over then selects set with maximum value.
     * <p>
     * As detecting all combinations of set has exponential complexity, it has exponential run time complexity.
     *
     * @param config Package capacity and available items configuration
     * @return Returns the set of items, which has maximum value for given package capacity.
     */
    public static Set<Item> fillPackage(PackageConfig config) {

        // If any given items weight is already bigger than capacity, then filter this item
        Set<Item> itemsUnderCapacity = config.getItems().stream()
                .filter(item -> item.getWeight() <= config.getCapacity())
                .collect(Collectors.toSet());

        /* Get all available combinations of given set.
         * Sets.combinations(n,r) method calculates C(n,r), subsets of N with element number r, so loop for all
         * possible 'r' values to get all combinations.
         */
        Set<Set<Item>> combinations = new HashSet<>();
        for (int r = 1; r < itemsUnderCapacity.size()+1; r++) {
            combinations.addAll(Sets.combinations(config.getItems(), r));
        }

        int maxValue = 0;
        Set<Item> selectedItems = new HashSet<>();
        double selectedItemsWeight = 0.0;

        for (Set<Item> combination : combinations) {
            double totalWeight = 0.0;

            int totalValue = 0;
            for (Item item : combination) {
                totalWeight += item.getWeight();
                totalValue += item.getPrice();
            }

            /**
             * if total weight exceed capacity or total value is smaller than max value, then continue on next loop.
             */
            if (totalWeight > config.getCapacity() || totalValue < maxValue) {
                continue;
            }

            /**
             * if total value is bigger than max_value or having smaller weight for equal value items,
             * then select new combination
             */
            if (totalValue > maxValue || totalWeight < selectedItemsWeight) {
                selectedItemsWeight = totalWeight;
                selectedItems = combination;
                maxValue = totalValue;
            }

        }
        return selectedItems;
    }

}
