/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "incart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incart.findAll", query = "SELECT i FROM Incart i"),
    @NamedQuery(name = "Incart.findByIDUser", query = "SELECT i FROM Incart i WHERE i.incartPK.iDUser = :iDUser"),
    @NamedQuery(name = "Incart.findByIDArticle", query = "SELECT i FROM Incart i WHERE i.incartPK.iDArticle = :iDArticle"),
    @NamedQuery(name = "Incart.findByAmount", query = "SELECT i FROM Incart i WHERE i.amount = :amount")})
public class Incart implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IncartPK incartPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Amount")
    private int amount;
    @JoinColumn(name = "IDArticle", referencedColumnName = "IDArticle", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "IDUser", referencedColumnName = "IDUser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cart cart;

    public Incart() {
    }

    public Incart(IncartPK incartPK) {
        this.incartPK = incartPK;
    }

    public Incart(IncartPK incartPK, int amount) {
        this.incartPK = incartPK;
        this.amount = amount;
    }

    public Incart(int iDUser, int iDArticle) {
        this.incartPK = new IncartPK(iDUser, iDArticle);
    }

    public IncartPK getIncartPK() {
        return incartPK;
    }

    public void setIncartPK(IncartPK incartPK) {
        this.incartPK = incartPK;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (incartPK != null ? incartPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incart)) {
            return false;
        }
        Incart other = (Incart) object;
        if ((this.incartPK == null && other.incartPK != null) || (this.incartPK != null && !this.incartPK.equals(other.incartPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.etf.is1.entities.Incart[ incartPK=" + incartPK + " ]";
    }
    
}
