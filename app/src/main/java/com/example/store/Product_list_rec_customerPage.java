package com.example.store;

public class Product_list_rec_customerPage {
    String name;
    int price;
    String imagePath;
    int ID;

    public Product_list_rec_customerPage(String name, int price, String imagePath, int ID) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getID() {
        return ID;
    }
}
