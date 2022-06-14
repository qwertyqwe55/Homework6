package com.netcracker.models;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    public Book() {
    }

    public Book(long idBook, String name, int cost, String storage, int count) {
        this.idBook = idBook;
        this.name = name;
        this.cost = cost;
        this.storage = storage;
        this.count = count;
    }



    private long idBook;
    private String name;
    private int cost;
    private String storage;
    private int count;

    @Id
    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "cost", nullable = false)
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    @Column(name = "storage", nullable = false)
    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
    @Column(name = "count", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id_book=" + idBook +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", storage='" + storage + '\'' +
                ", count=" + count +
                '}';
    }
}
