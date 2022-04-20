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
public class Category {

    private int id;
    private String name;
    private String text;
    private String color;

    public Category(int id, String name, String color, String descriptionCat) {
        this.id = id;
        this.name = name;
        this.text = descriptionCat;
        this.color = color;
    }

    public Category(String name, String color, String descriptionCat) {
        this.name = name;
        this.text = descriptionCat;
        this.color = color;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", text=" + text + ", color=" + color + '}';
    }

}
