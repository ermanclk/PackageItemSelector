package com.util;

import com.domain.Item;
import com.domain.PackageConfig;
import com.exception.PackagerException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.constants.Constraints.*;

public class LineParser {

    private LineParser() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    private static final String ITEM_SEPARATOR = ",";
    private static final String LINE_SEPARATOR = " ";
    private static final String EURO_SYMBOL = "€";
    private static final String EMPTY_STRING = "";
    private static final String PARENTHESES_OPEN = "(";
    private static final String PARENTHESES_CLOSE = ")";

    private static final int ITEM_NUMBER_INDEX = 0;
    private static final int WEIGHT_INDEX = 1;
    private static final int PRICE_INDEX = 2;

    public static List<PackageConfig> parseFileContent(String filePath) {

        List<PackageConfig> configs = new ArrayList<>();

        for (String line : FileUtil.readLines(filePath)) {

            Set<Item> items = new HashSet<>();
            String[] elements = line.split(LINE_SEPARATOR);

            PackageConfig packageConfig;

            try {
                for (int i = 2; i < elements.length; i++) {
                    items.add(parseToItem(elements[i]));
                }
                packageConfig = new PackageConfig(Integer.valueOf(elements[0]), items);
            } catch (NumberFormatException e) {
                throw new PackagerException("Cannot parse element, check file format.", e);
            }

            validateConstraints(packageConfig);
            configs.add(packageConfig);
        }

        return configs;
    }

    private static Item parseToItem(String element) {
        String[] values = element.replace(PARENTHESES_OPEN,EMPTY_STRING)
                .replace(PARENTHESES_CLOSE,EMPTY_STRING)
                .split(ITEM_SEPARATOR);

        return new Item(values[ITEM_NUMBER_INDEX],
                Double.valueOf(values[WEIGHT_INDEX]),
                Integer.valueOf(values[PRICE_INDEX].replace(EURO_SYMBOL, EMPTY_STRING)));
    }

    static void validateConstraints(PackageConfig line) {

        if (line.getCapacity() > MAX_PACKAGE_CAPACITY) {
            throw new PackagerException("The maximum weight that a package can hold must be <=" + MAX_PACKAGE_CAPACITY);
        }

        if (line.getItems().size() > MAX_ELEMENT_COUNT) {
            throw new PackagerException("Number of elements exceeds the defined maximum limit of " + MAX_ELEMENT_COUNT);
        }

        for (Item item : line.getItems()) {

            if (item.getPrice() > MAX_ITEM_PRICE) {
                throw new PackagerException("Invalid item price value. The maximum cost of an item should be <= €" + MAX_ITEM_PRICE);
            }

            if (item.getWeight() > MAX_ITEM_WEIGHT) {
                throw new PackagerException("Invalid item weight value. The maximum weight of an item should be <= " + MAX_ITEM_WEIGHT);
            }
        }

    }


}
