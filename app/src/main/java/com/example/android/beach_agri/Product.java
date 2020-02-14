package com.example.android.beach_agri;

public class Product {

    public String ProductId;
    public String Productname;
    public int Stock;
    public int price;
    public String imageid;

    public Product() {
    }

    public Product(String ProductId,String Productname, int stock, int price, String imageid) {
        this.Productname=Productname;
        this.ProductId = ProductId;
        this.Stock = stock;
        this.price = price;
        this.imageid = imageid;
    }
}
