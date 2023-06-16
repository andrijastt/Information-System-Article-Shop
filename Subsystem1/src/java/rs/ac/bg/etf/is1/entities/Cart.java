/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stoja
 */
@Entity
@Table(name = "cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findByIDUser", query = "SELECT c FROM Cart c WHERE c.iDUser = :iDUser"),
    @NamedQuery(name = "Cart.findByTotalPrice", query = "SELECT c FROM Cart c WHERE c.totalPrice = :totalPrice")})
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUser")
    private Integer iDUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalPrice")
    private int totalPrice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<Incart> incartList;
    @JoinColumn(name = "IDUser", referencedColumnName = "IDUser", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Cart() {
    }

    public Cart(Integer iDUser) {
        this.iDUser = iDUser;
    }

    public Cart(Integer iDUser, int totalPrice) {
        this.iDUser = iDUser;
        this.totalPrice = totalPrice;
    }

    public Integer getIDUser() {
        return iDUser;
    }

    public void setIDUser(Integer iDUser) {
        this.iDUser = iDUser;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @XmlTransient
    public List<Incart> getIncartList() {
        return incartList;
    }

    public void setIncartList(List<Incart> incartList) {
        this.incartList = incartList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDUser != null ? iDUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.iDUser == null && other.iDUser != null) || (this.iDUser != null && !this.iDUser.equals(other.iDUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.etf.is1.entities.Cart[ iDUser=" + iDUser + " ]";
    }
    
}
