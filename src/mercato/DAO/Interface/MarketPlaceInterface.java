/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.DAO.Interface;

import java.util.List;
import mercato.Model.Product;
import mercato.Model.SponsoredUser;

/**
 *
 * @author PC-Yassine
 */
public interface MarketPlaceInterface {
    
    public boolean addProduct(Product p);
    public boolean  UpdateProduct(Product p);
    public List<Product> ListAll();
    public Product getById(int id);
    public List<Product> getBySponsoredUser(SponsoredUser sp);
    public boolean deleteProduct(int id);
    public List<Product> findMyProdByName(int i ,String nom);
    public List<Product> findProdByName(String nom);
}
