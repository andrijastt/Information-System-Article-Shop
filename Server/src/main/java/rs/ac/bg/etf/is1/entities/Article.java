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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stoja
 */
@Entity
@Table(name = "article")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByIDArticle", query = "SELECT a FROM Article a WHERE a.iDArticle = :iDArticle"),
    @NamedQuery(name = "Article.findByName", query = "SELECT a FROM Article a WHERE a.name = :name"),
    @NamedQuery(name = "Article.findByDescription", query = "SELECT a FROM Article a WHERE a.description = :description"),
    @NamedQuery(name = "Article.findByPrice", query = "SELECT a FROM Article a WHERE a.price = :price"),
    @NamedQuery(name = "Article.findByDiscount", query = "SELECT a FROM Article a WHERE a.discount = :discount")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDArticle")
    private Integer iDArticle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Discount")
    private int discount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDArticle")
    private List<Item> itemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDArticle")
    private List<Recension> recensionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private List<Incart> incartList;
    @JoinColumn(name = "IDCategory", referencedColumnName = "IDCategory")
    @ManyToOne(optional = false)
    private Category iDCategory;
    @JoinColumn(name = "IDUser", referencedColumnName = "IDUser")
    @ManyToOne(optional = false)
    private User iDUser;

    public Article() {
    }

    public Article(Integer iDArticle) {
        this.iDArticle = iDArticle;
    }

    public Article(Integer iDArticle, String name, String description, int price, int discount) {
        this.iDArticle = iDArticle;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }

    public Integer getIDArticle() {
        return iDArticle;
    }

    public void setIDArticle(Integer iDArticle) {
        this.iDArticle = iDArticle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @XmlTransient
    public List<Recension> getRecensionList() {
        return recensionList;
    }

    public void setRecensionList(List<Recension> recensionList) {
        this.recensionList = recensionList;
    }

    @XmlTransient
    public List<Incart> getIncartList() {
        return incartList;
    }

    public void setIncartList(List<Incart> incartList) {
        this.incartList = incartList;
    }

    public Category getIDCategory() {
        return iDCategory;
    }

    public void setIDCategory(Category iDCategory) {
        this.iDCategory = iDCategory;
    }

    public User getIDUser() {
        return iDUser;
    }

    public void setIDUser(User iDUser) {
        this.iDUser = iDUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDArticle != null ? iDArticle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.iDArticle == null && other.iDArticle != null) || (this.iDArticle != null && !this.iDArticle.equals(other.iDArticle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.etf.is1.entities.Article[ iDArticle=" + iDArticle + " ]";
    }
    
}
