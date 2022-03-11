package com.maxtrain.bootcamp.product;

import javax.persistence.*;

@Entity
@Table(name = "Product",
        uniqueConstraints = {@UniqueConstraint(name = "uc_product_partnbr", columnNames = {"partNbr"})})
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(length = 30, nullable = false)
    private String partNbr; //Unique
    @Column(length = 30, nullable = false)
    private String name;
    @Column(columnDefinition = "decimal(7,2) NOT NULL DEFAULT 0.0")
    private double price;

    public Product() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPartNbr() {
        return partNbr;
    }
    public void setPartNbr(String partNbr) {
        this.partNbr = partNbr;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
