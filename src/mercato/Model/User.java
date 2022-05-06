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
import java.sql.Date;
import java.util.Objects;

public class User {

    int id ; 
    String username ; 
    String roles ; 
    String password ; 
    String email ; 
    String name ; 
    String last_name; 
    Date birth_date ; 
    String speciality ; 
    String status ; 
    String experience ; 
    int hight ; 
    int weight ; 
    String cv ;
    String media ; 
    String company ;
    String position ; 
    String pays_natals; 
    String sexe ; 
    String lien_profil_pic ;
    String sponsorship ; 
    int solde ; 

    public User(int id) {
        this.id = id;
    }

    public User(int id, String username, String roles, String password, String email, String name, String last_name, Date birth_date, String speciality, String status, String experience, int hight, int weight, String cv, String media, String company, String position, String pays_natals, String sexe, String lien_profil_pic, String sponsorship, int solde) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.email = email;
        this.name = name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.speciality = speciality;
        this.status = status;
        this.experience = experience;
        this.hight = hight;
        this.weight = weight;
        this.cv = cv;
        this.media = media;
        this.company = company;
        this.position = position;
        this.pays_natals = pays_natals;
        this.sexe = sexe;
        this.lien_profil_pic = lien_profil_pic;
        this.sponsorship = sponsorship;
        this.solde = solde;
    }

    public User(int id, String username, String roles, String password, String email, String name, String last_name, Date birth_date, String speciality, String status, String experience, int hight, int weight, String cv, String media, String company, String position, String pays_natals, String sexe, String lien_profil_pic/*, String sponsorship, int solde*/) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.email = email;
        this.name = name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.speciality = speciality;
        this.status = status;
        this.experience = experience;
        this.hight = hight;
        this.weight = weight;
        this.cv = cv;
        this.media = media;
        this.company = company;
        this.position = position;
        this.pays_natals = pays_natals;
        this.sexe = sexe;
        this.lien_profil_pic = lien_profil_pic;
        /*this.sponsorship = sponsorship;
        this.solde = solde;*/
    }

    public User(){}
    public User(String username, String roles, String password, String email, String name, String last_name, Date bitdh_date, String speciality, String status, String experience, int hight, int weight, String cv, String media, String company, String position, String pays_natals, String sexe, String lien_profil_pic) {
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.email = email;
        this.name = name;
        this.last_name = last_name;
        this.birth_date = (Date)birth_date;
        this.speciality = speciality;
        this.status = status;
        this.experience = experience;
        this.hight = hight;
        this.weight = weight;
        this.cv = cv;
        this.media = media;
        this.company = company;
        this.position = position;
        this.pays_natals = pays_natals;
        this.sexe = sexe;
        this.lien_profil_pic = lien_profil_pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date bitdh_date) {
        this.birth_date = bitdh_date;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPays_natals() {
        return pays_natals;
    }

    public void setPays_natals(String pays_natals) {
        this.pays_natals = pays_natals;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getLien_profil_pic() {
        return lien_profil_pic;
    }

    public void setLien_profil_pic(String lien_profil_pic) {
        this.lien_profil_pic = lien_profil_pic;
    }

    public String getSponsorship() {
        return sponsorship;
    }

    public void setSponsorship(String sponsorship) {
        this.sponsorship = sponsorship;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", roles=" + roles + ", password=" + password + ", email=" + email + ", name=" + name + ", last_name=" + last_name + ", bitdh_date=" + birth_date + ", speciality=" + speciality + ", status=" + status + ", experience=" + experience + ", hight=" + hight + ", weight=" + weight + ", cv=" + cv + ", media=" + media + ", company=" + company + ", position=" + position + ", pays_natals=" + pays_natals + ", sexe=" + sexe + ", lien_profil_pic=" + lien_profil_pic + ", sponsorship=" + sponsorship + ", solde=" + solde + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.roles);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.last_name);
        hash = 29 * hash + Objects.hashCode(this.birth_date);
        hash = 29 * hash + Objects.hashCode(this.speciality);
        hash = 29 * hash + Objects.hashCode(this.status);
        hash = 29 * hash + Objects.hashCode(this.experience);
        hash = 29 * hash + this.hight;
        hash = 29 * hash + this.weight;
        hash = 29 * hash + Objects.hashCode(this.cv);
        hash = 29 * hash + Objects.hashCode(this.media);
        hash = 29 * hash + Objects.hashCode(this.company);
        hash = 29 * hash + Objects.hashCode(this.position);
        hash = 29 * hash + Objects.hashCode(this.pays_natals);
        hash = 29 * hash + Objects.hashCode(this.sexe);
        hash = 29 * hash + Objects.hashCode(this.lien_profil_pic);
        hash = 29 * hash + Objects.hashCode(this.sponsorship);
        hash = 29 * hash + this.solde;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.hight != other.hight) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        if (this.solde != other.solde) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.last_name, other.last_name)) {
            return false;
        }
        if (!Objects.equals(this.speciality, other.speciality)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.experience, other.experience)) {
            return false;
        }
        if (!Objects.equals(this.cv, other.cv)) {
            return false;
        }
        if (!Objects.equals(this.media, other.media)) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (!Objects.equals(this.pays_natals, other.pays_natals)) {
            return false;
        }
        if (!Objects.equals(this.sexe, other.sexe)) {
            return false;
        }
        if (!Objects.equals(this.lien_profil_pic, other.lien_profil_pic)) {
            return false;
        }
        if (!Objects.equals(this.sponsorship, other.sponsorship)) {
            return false;
        }
        if (!Objects.equals(this.birth_date, other.birth_date)) {
            return false;
        }
        return true;
    }

   

}
