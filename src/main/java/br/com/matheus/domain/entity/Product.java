package br.com.matheus.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 150)
    private String _product_name;

    @Column(name = "description", nullable = false, length = 500)
    private String _description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal _price;

    @Column(name = "stock_quantity", nullable = false)
    private int _stockQuantity;

    public Product(){}

    public Product(String _product_name, String _description, BigDecimal _price, int _stockQuantity){
        this._product_name = _product_name;
        this._description = _description;
        this._price = _price;
        this._stockQuantity = _stockQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String get_product_name() {
        return _product_name;
    }

    public void set_product_name(String _product_name) {
        this._product_name = _product_name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public BigDecimal get_price() {
        return _price;
    }

    public void set_price(BigDecimal _price) {
        this._price = _price;
    }

    public int get_stockQuantity() {
        return _stockQuantity;
    }

    public void set_stockQuantity(int _stockQuantity) {
        this._stockQuantity = _stockQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", _product_name='" + _product_name + '\'' +
                ", _description='" + _description + '\'' +
                ", _price=" + _price +
                ", _stockQuantity=" + _stockQuantity +
                '}';
    }
}
