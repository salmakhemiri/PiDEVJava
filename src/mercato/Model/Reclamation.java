/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.Model;

import java.sql.Date;
import java.util.Objects;


/**
 *
 * @author ASUS
 */
public class Reclamation {
    private int recId;
    private String title;
    private String body;
    private String subject;
    private int userId;
    private Date date;
    private String etatProbleme;
    // private int valide;

    public Reclamation() {
    }

    
    public Reclamation(int recId, String title, String body, String subject, int userId, Date date, String etatProbleme) {
        this.recId = recId;
        this.title = title;
        this.body = body;
        this.subject = subject;
        this.userId = userId;
        this.date = date;
        this.etatProbleme = etatProbleme;
    }

    public Reclamation(String title, String body, String subject, int userId, Date date, String etatProbleme) {
        this.title = title;
        this.body = body;
        this.subject = subject;
        this.userId = userId;
        this.date = date;
        this.etatProbleme = etatProbleme;
    }

    
    
    public Reclamation(String title, String body, String subject, int userId, Date date) {
        this.title = title;
        this.body = body;
        this.subject = subject;
        this.userId = userId;
        this.date = date;
    }

    public Reclamation(int recId, String title, String body, String subject, int userId, Date date) {
        this.recId = recId;
        this.title = title;
        this.body = body;
        this.subject = subject;
        this.userId = userId;
        this.date = date;
    }

    public Reclamation(int recId) {
        this.recId = recId;
    }

    public Reclamation(int recId, int userId) {
        this.recId = recId;
        this.userId = userId;
    }

    

    

    public int getRecId() {
        return recId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }
    
    public String getEtatProbleme() {
        return etatProbleme;
    }    
    

    /*public int getValide() {
        return valide;
    } */

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setEtatProbleme(String etatProbleme) {
        this.etatProbleme = etatProbleme;
    }

   /* public void setValide(int valide) {
        this.valide = valide;
    } */

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.recId != other.recId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "recId=" + recId + ", title=" + title + ", body=" + body + ", subject=" + subject + ", userId=" + userId + ", date=" + date + ", etatProbleme=" + etatProbleme + '}';
    }
    
}
