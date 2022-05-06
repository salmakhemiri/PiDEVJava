/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.DAO.Interface;

import java.util.List;
import mercato.Model.SponsoredUser;
import mercato.Model.Type;
import mercato.Model.User;

/**
 *
 * @author PC-Yassine
 */
public interface SponsorInterface {
    public void AddSponsoredUser(SponsoredUser su);
    public Boolean DeletSponsoredUser(int id);
    public Boolean UpgradeSponsoredUser(SponsoredUser sp);
    public Boolean DowngradeSponsoredUser(SponsoredUser sp);
    public List <SponsoredUser> FindAllSponsoredUser();
    public SponsoredUser FindSponsoredUser(int id);
    public boolean isSponsoredUser(User u);
    
    
}
