/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author stoja
 */
@Embeddable
public class IncartPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUser")
    private int iDUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDArticle")
    private int iDArticle;

    public IncartPK() {
    }

    public IncartPK(int iDUser, int iDArticle) {
        this.iDUser = iDUser;
        this.iDArticle = iDArticle;
    }

    public int getIDUser() {
        return iDUser;
    }

    public void setIDUser(int iDUser) {
        this.iDUser = iDUser;
    }

    public int getIDArticle() {
        return iDArticle;
    }

    public void setIDArticle(int iDArticle) {
        this.iDArticle = iDArticle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDUser;
        hash += (int) iDArticle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncartPK)) {
            return false;
        }
        IncartPK other = (IncartPK) object;
        if (this.iDUser != other.iDUser) {
            return false;
        }
        if (this.iDArticle != other.iDArticle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.etf.is1.entities.IncartPK[ iDUser=" + iDUser + ", iDArticle=" + iDArticle + " ]";
    }
    
}
