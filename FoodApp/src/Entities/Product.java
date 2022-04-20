/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Aicha
 */
public class Product {

    private int id;
    private int category_id;
    private String name;
    private String description;
    private String price;
    private String image;
    private String quantite;
    private String discount;
    private String initial_price;

    public Product(int id, int category_id, String name, String description, String price, String image, String quantite, String discount, String initial_price) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.quantite = quantite;
        this.discount = discount;
        this.initial_price = initial_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getInitial_price() {
        return initial_price;
    }

    public void setInitial_price(String initial_price) {
        this.initial_price = initial_price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", category_id=" + category_id + ", name=" + name + ", description=" + description + ", price=" + price + ", image=" + image + ", quantite=" + quantite + ", discount=" + discount + ", initial_price=" + initial_price + '}';
    }

}
