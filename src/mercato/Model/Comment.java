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

public class Comment {
    private int commentId;
    private String body;
    private Date date;
    private int userId;
    private int likeId;

    public int getCommentId() {
        return commentId;
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

    public int getLikeId() {
        return likeId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

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
        final Comment other = (Comment) obj;
        if (this.commentId != other.commentId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.likeId != other.likeId) {
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
        return "comment{" + "commentId=" + commentId + ", body=" + body + ", date=" + date + ", userId=" + userId + ", likeId=" + likeId + '}';
    }
    
}
