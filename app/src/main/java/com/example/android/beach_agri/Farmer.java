package com.example.android.beach_agri;

import java.util.List;

public class Farmer {
   public String name ;
   public String location;
   public List<Product> Products;
   public int rating;
   public String contact;

    public Farmer() {
    }

    public Farmer(String name, String location, List<Product> products, int rating, String contact) {
        this.name = name;
        this.location = location;
        Products = products;
        this.rating = rating;
        this.contact = contact;
    }
}