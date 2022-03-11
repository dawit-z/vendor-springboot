package com.maxtrain.bootcamp.invoiceline;

import com.maxtrain.bootcamp.invoice.Invoice;
import com.maxtrain.bootcamp.product.Product;

import javax.persistence.*;

@Entity
@Table
public class Invoiceline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "invoice_id", columnDefinition = "int")
    private Invoice invoice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", columnDefinition = "int")
    private Product product;

    public Invoiceline() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }
    public Invoice getInvoice() {
        return invoice;
    }
}
