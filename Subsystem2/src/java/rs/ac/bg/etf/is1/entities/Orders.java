/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stoja
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByIDOrder", query = "SELECT o FROM Orders o WHERE o.iDOrder = :iDOrder"),
    @NamedQuery(name = "Orders.findByTotalPrice", query = "SELECT o FROM Orders o WHERE o.totalPrice = :totalPrice"),
    @NamedQuery(name = "Orders.findByTimeCreated", query = "SELECT o FROM Orders o WHERE o.timeCreated = :timeCreated"),
    @NamedQuery(name = "Orders.findByAddress", query = "SELECT o FROM Orders o WHERE o.address = :address")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDOrder")
    private Integer iDOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalPrice")
    private int totalPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TimeCreated")
    @Temporal(TemporalType.DATE)
    private Date timeCreated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Address")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDOrder")
    private List<Item> itemList;
    @JoinColumn(name = "IDCity", referencedColumnName = "IDCity")
    @ManyToOne(optional = false)
    private City iDCity;
    @JoinColumn(name = "IDUser", referencedColumnName = "IDUser")
    @ManyToOne(optional = false)
    private User iDUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDOrder")
    private List<Transaction> transactionList;

    public Orders() {
    }

    public Orders(Integer iDOrder) {
        this.iDOrder = iDOrder;
    }

    public Orders(Integer iDOrder, int totalPrice, Date timeCreated, String address) {
        this.iDOrder = iDOrder;
        this.totalPrice = totalPrice;
        this.timeCreated = timeCreated;
        this.address = address;
    }

    public Integer getIDOrder() {
        return iDOrder;
    }

    public void setIDOrder(Integer iDOrder) {
        this.iDOrder = iDOrder;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public City getIDCity() {
        return iDCity;
    }

    public void setIDCity(City iDCity) {
        this.iDCity = iDCity;
    }

    public User getIDUser() {
        return iDUser;
    }

    public void setIDUser(User iDUser) {
        this.iDUser = iDUser;
    }

    @XmlTransient
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDOrder != null ? iDOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.iDOrder == null && other.iDOrder != null) || (this.iDOrder != null && !this.iDOrder.equals(other.iDOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.etf.is1.entities.Orders[ iDOrder=" + iDOrder + " ]";
    }
    
}
