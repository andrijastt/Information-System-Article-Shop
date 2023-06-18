/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stoja
 */
@Entity
@Table(name = "transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findByIDTransaction", query = "SELECT t FROM Transaction t WHERE t.iDTransaction = :iDTransaction"),
    @NamedQuery(name = "Transaction.findByPaymentTime", query = "SELECT t FROM Transaction t WHERE t.paymentTime = :paymentTime"),
    @NamedQuery(name = "Transaction.findByTotalPrice", query = "SELECT t FROM Transaction t WHERE t.totalPrice = :totalPrice")})
public class Transaction implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PaymentTime")
    @Temporal(TemporalType.DATE)
    private Date paymentTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalPrice")
    private int totalPrice;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTransaction")
    private Integer iDTransaction;
    @JoinColumn(name = "IDOrder", referencedColumnName = "IDOrder")
    @ManyToOne(optional = false)
    private Orders iDOrder;

    public Transaction() {
    }

    public Transaction(Integer iDTransaction) {
        this.iDTransaction = iDTransaction;
    }

    public Transaction(Integer iDTransaction, Date paymentTime, int totalPrice) {
        this.iDTransaction = iDTransaction;
        this.paymentTime = paymentTime;
        this.totalPrice = totalPrice;
    }

    public Integer getIDTransaction() {
        return iDTransaction;
    }

    public void setIDTransaction(Integer iDTransaction) {
        this.iDTransaction = iDTransaction;
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
        hash += (iDTransaction != null ? iDTransaction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.iDTransaction == null && other.iDTransaction != null) || (this.iDTransaction != null && !this.iDTransaction.equals(other.iDTransaction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.ac.bg.etf.is1.entities.Transaction[ iDTransaction=" + iDTransaction + " ]";
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
