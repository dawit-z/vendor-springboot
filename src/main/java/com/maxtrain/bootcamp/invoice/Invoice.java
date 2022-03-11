package com.maxtrain.bootcamp.invoice;

import com.maxtrain.bootcamp.vendor.Vendor;

import javax.persistence.*;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 40)
    private String description;

    @Column(length = 20, nullable = false)
    private String status;

    @Column(columnDefinition = "decimal(8,2) NOT NULL DEFAULT 0.0")
    private double total;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendor_id", columnDefinition ="int")
    private Vendor vendor;

    public Invoice() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Vendor getVendor() {
        return vendor;
    }
}
