/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stoja
 */
@Entity
@Table(name = "recension")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recension.findAll", query = "SELECT r FROM Recension r"),
    @NamedQuery(name = "Recension.findByIDRecension", query = "SELECT r FROM Recension r WHERE r.iDRecension = :iDRecension"),
    @NamedQuery(name = "Recension.findByDecription", query = "SELECT r FROM Recension r WHERE r.decription = :decription"),
    @NamedQuery(name = "Recension.findByGrade", query = "SELECT r FROM Recension r WHERE r.grade = :grade")})
public class Recension implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRecension")
    private Integer iDRecension;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Decription")
    private String decription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Grade")
    private int grade;
    @JoinColumn(name = "IDArticle", referencedColumnName = "IDArticle")
    @ManyToOne(optional = false)
    private Article iDArticle;

    public Recension() {
    }

    public Recension(Integer iDRecension) {
        this.iDRecension = iDRecension;
    }

    public Recension(Integer iDRecension, String decription, int grade) {
        this.iDRecension = iDRecension;
        this.decription = decription;
        this.grade = grade;
    }

    public Integer getIDRecension() {
        return iDRecension;
    }

    public void setIDRecension(Integer iDRecension) {
        this.iDRecension = iDRecension;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Article getIDArticle() {
        return iDArticle;
    }

    public void setIDArticle(Article iDArticle) {
        this.iDArticle = iDArticle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDRecension != null ? iDRecension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recension)) {
            return false;
        }
        Recension other = (Recension) object;
        if ((this.iDRecension == null && other.iDRecension != null) || (this.iDRecension != null && !this.iDRecension.equals(other.iDRecension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.etf.is1.entities.Recension[ iDRecension=" + iDRecension + " ]";
    }
    
}
