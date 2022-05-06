/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.Model;

import java.util.Date;

/**
 *
 * @author PC-Yassine
 */
public class View {
    private int viewId;
    private Date date;
    private boolean viewed;
    private User user;
    private SponsoredUser sp;

    
    
    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public SponsoredUser getSp() {
        return sp;
    }

    public void setSp(SponsoredUser sp) {
        this.sp = sp;
    }

  
    
    
}
