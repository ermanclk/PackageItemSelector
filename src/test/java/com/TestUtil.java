package com;

import com.domain.Item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestUtil {

    public static Set<Item> getOptions(Item ...items) {
        Set<Item> options = new HashSet<>();
        Arrays.asList(items).forEach(options::add);
        return options;
    }



}
