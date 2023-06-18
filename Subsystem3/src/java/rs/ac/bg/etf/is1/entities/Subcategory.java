/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stoja
 */
@Entity
@Table(name = "subcategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subcategory.findAll", query = "SELECT s FROM Subcategory s"),
    @NamedQuery(name = "Subcategory.findByIDCategory", query = "SELECT s FROM Subcategory s WHERE s.iDCategory = :iDCategory")})
public class Subcategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCategory")
    private Integer iDCategory;
    @JoinColumn(name = "IDCategory", referencedColumnName = "IDCategory", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Category category;
    @JoinColumn(name = "IDSubcategory", referencedColumnName = "IDCategory")
    @ManyToOne(optional = false)
    private Category iDSubcategory;

    public Subcategory() {
    }

    public Subcategory(Integer iDCategory) {
        this.iDCategory = iDCategory;
    }

    public Integer getIDCategory() {
        return iDCategory;
    }

    public void setIDCategory(Integer iDCategory) {
        this.iDCategory = iDCategory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getIDSubcategory() {
        return iDSubcategory;
    }

    public void setIDSubcategory(Category iDSubcategory) {
        this.iDSubcategory = iDSubcategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCategory != null ? iDCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcategory)) {
            return false;
        }
        Subcategory other = (Subcategory) object;
        if ((this.iDCategory == null && other.iDCategory != null) || (this.iDCategory != null && !this.iDCategory.equals(other.iDCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.etf.is1.entities.Subcategory[ iDCategory=" + iDCategory + " ]";
    }
    
}
