package com.domain;

public class Item {

    private String id;
    private double weight;
    private int price;

    public Item(String id, double weight, int price) {
        this.id = id;
        this.weight = weight;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}
