/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.Model;

/**
 *
 * @author PC-Yassine
 */
import java.util.Date;
import java.util.Objects;

public class Post {

    private int postid;
    private String titre;
    private String body;
    private Date date;
    private int userId;

    public Post() {
        this.postid = 0;
        this.titre = "";
        this.body = "";
        this.date = new Date();
        this.userId = 0;
    }

    public Post(int postid, String titre, String body, Date date, int userId) {
        this.postid = postid;
        this.titre = titre;
        this.body = body;
        this.date = date;
        this.userId = userId;
    }

    public int getPostid() {
        return postid;
    }

    public String getTitre() {
        return titre;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        final Post other = (Post) obj;
        if (this.postid != other.postid) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Post{" + "postid=" + postid + ", titre=" + titre + ", body=" + body + ", date=" + date + ", userId=" + userId + '}';
    }
    
}
