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
@Table(name = "city")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c"),
    @NamedQuery(name = "City.findByIDCity", query = "SELECT c FROM City c WHERE c.iDCity = :iDCity"),
    @NamedQuery(name = "City.findByName", query = "SELECT c FROM City c WHERE c.name = :name")})
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCity")
    private Integer iDCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDCity")
    private List<User> userList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDCity")
    private List<Order1> order1List;

    public City() {
    }

    public City(Integer iDCity) {
        this.iDCity = iDCity;
    }

    public City(Integer iDCity, String name) {
        this.iDCity = iDCity;
        this.name = name;
    }

    public Integer getIDCity() {
        return iDCity;
    }

    public void setIDCity(Integer iDCity) {
        this.iDCity = iDCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @XmlTransient
    public List<Order1> getOrder1List() {
        return order1List;
    }

    public void setOrder1List(List<Order1> order1List) {
        this.order1List = order1List;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCity != null ? iDCity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.iDCity == null && other.iDCity != null) || (this.iDCity != null && !this.iDCity.equals(other.iDCity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.etf.is1.entities.City[ iDCity=" + iDCity + " ]";
    }
    
}
