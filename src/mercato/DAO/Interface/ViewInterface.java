/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.DAO.Interface;

import java.util.List;
import mercato.Model.SponsoredUser;
import mercato.Model.User;
import mercato.Model.View;

/**
 *
 * @author PC-Yassine
 */
public interface ViewInterface {
    public boolean add(View v);
    public boolean viewed(View v);
    public List<View> getallBySponsoredUser(SponsoredUser u);
    public View getById(int id);
 
    
}
