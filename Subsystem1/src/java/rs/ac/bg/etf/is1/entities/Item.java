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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stoja
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByIDItem", query = "SELECT i FROM Item i WHERE i.iDItem = :iDItem"),
    @NamedQuery(name = "Item.findByAmount", query = "SELECT i FROM Item i WHERE i.amount = :amount"),
    @NamedQuery(name = "Item.findByTotalArticlePrice", query = "SELECT i FROM Item i WHERE i.totalArticlePrice = :totalArticlePrice")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDItem")
    private Integer iDItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Amount")
    private int amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalArticlePrice")
    private int totalArticlePrice;
    @JoinColumn(name = "IDArticle", referencedColumnName = "IDArticle")
    @ManyToOne(optional = false)
    private Article iDArticle;
    @JoinColumn(name = "IDOrder", referencedColumnName = "IDOrder")
    @ManyToOne(optional = false)
    private Orders iDOrder;

    public Item() {
    }

    public Item(Integer iDItem) {
        this.iDItem = iDItem;
    }

    public Item(Integer iDItem, int amount, int totalArticlePrice) {
        this.iDItem = iDItem;
        this.amount = amount;
        this.totalArticlePrice = totalArticlePrice;
    }

    public Integer getIDItem() {
        return iDItem;
    }

    public void setIDItem(Integer iDItem) {
        this.iDItem = iDItem;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalArticlePrice() {
        return totalArticlePrice;
    }

    public void setTotalArticlePrice(int totalArticlePrice) {
        this.totalArticlePrice = totalArticlePrice;
    }

    public Article getIDArticle() {
        return iDArticle;
    }

    public void setIDArticle(Article iDArticle) {
        this.iDArticle = iDArticle;
    }

    public Orders getIDOrder() {
        return iDOrder;
    }

    public void setIDOrder(Orders iDOrder) {
        this.iDOrder = iDOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDItem != null ? iDItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.iDItem == null && other.iDItem != null) || (this.iDItem != null && !this.iDItem.equals(other.iDItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.etf.is1.entities.Item[ iDItem=" + iDItem + " ]";
    }
    
}
