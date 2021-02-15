package com.domain;

import java.util.Set;

public class PackageConfig {
    private int capacity;
    private Set<Item> items;

    public PackageConfig(int capacity, Set<Item> items) {
        this.capacity = capacity;
        this.items = items;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
