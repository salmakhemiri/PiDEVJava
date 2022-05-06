/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.Model;

import java.sql.Date;

/**
 *
 * @author PC-Yassine
 */
public class Product {
    private int id;
    private String name;
    private float price;
    private String Description;
    private String Image1;
    private String Image2;
    private String Image3;
    private String Image4;
    private Date date;
    private String state;
    private String pt;
    private SponsoredUser sp;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", Description=" + Description + ", Image1=" + Image1 + ", Image2=" + Image2 + ", Image3=" + Image3 + ", Image4=" + Image4 + ", date=" + date + ", state=" + state + ", pt=" + pt + ", sp=" + sp.toString() + ", quantity=" + quantity + '}';
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String Image1) {
        this.Image1 = Image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String Image2) {
        this.Image2 = Image2;
    }

    public String getImage3() {
        return Image3;
    }

    public void setImage3(String Image3) {
        this.Image3 = Image3;
    }

    public String getImage4() {
        return Image4;
    }

    public void setImage4(String Image4) {
        this.Image4 = Image4;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public SponsoredUser getSp() {
        return sp;
    }

    public void setSp(SponsoredUser sp) {
        this.sp = sp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

    
}
