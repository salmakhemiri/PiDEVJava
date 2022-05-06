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
public class SponsoredUser {
  
    private int sponsorId ;
    private Date date;
    private Type type;
    private User user;
    private Status status;
    private Date endDate;

    @Override
    public String toString() {
        return "SponsoredUser{" + "sponsorId=" + sponsorId + ", date=" + date + ", type=" + type + ", user=" + user + ", status=" + status + ", endDate=" + endDate + '}';
    }
    
    

    public int getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(int sponsorId) {
        this.sponsorId = sponsorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



}
