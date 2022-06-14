package com.netcracker.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "purchases")
public class Purchase {

    private long id;
    private Date datePurchases;
    private long idShop;
    private long idCustomer;
    private long idBook;
    private int count;
    private double cost;


    public Purchase() {
    }

    public Purchase(long id, Date datePurchases, long idShop, long idCustomer, long idBook, int count, double cost) {
        this.id = id;
        this.datePurchases = datePurchases;
        this.idShop = idShop;
        this.idCustomer = idCustomer;
        this.idBook = idBook;
        this.count = count;
        this.cost = cost;
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "date_purchase", nullable = false)
    public Date getDatePurchases() {
        return datePurchases;
    }

    public void setDatePurchases(Date datePurchases) {
        this.datePurchases = datePurchases;
    }
    @Column(name = "id_shop",nullable = false)
    public long getIdShop() {
        return idShop;
    }
    public void setIdShop(long idShop) {
        this.idShop = idShop;
    }
    @Column(name = "id_customer",nullable = false)
    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }
    @Column(name = "id_book", nullable = false)
    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    @Column(name = "count", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Column(name = "cost", nullable = false)
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
