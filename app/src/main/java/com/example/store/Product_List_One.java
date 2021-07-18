package com.example.store;

public class Product_List_One {
    String name;
    String price;
    int ID;


    public Product_List_One(String name, String price,int ID) {
        this.name = name;
        this.price = price;
        this.ID=ID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public int getID() { return ID;}


}
