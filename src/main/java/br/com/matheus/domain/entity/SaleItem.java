package br.com.matheus.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "saleItems")
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale _sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product _product;

    @Column(name = "quantity", nullable = false)
    private int _quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal _unit_price;

    public SaleItem(){}

    public SaleItem(Sale _sale, Product _product, int _quantity, BigDecimal _unit_price) {
        this._sale = _sale;
        this._product = _product;
        this._quantity = _quantity;
        this._unit_price = _unit_price;
    }

    public Sale get_sale() {
        return _sale;
    }

    public void set_sale(Sale _sale) {
        this._sale = _sale;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Product get_product() {
        return _product;
    }

    public void set_product(Product _product) {
        this._product = _product;
    }

    public int get_quantity() {
        return _quantity;
    }

    public void set_quantity(int _quantity) {
        this._quantity = _quantity;
    }

    public BigDecimal get_unit_price() {
        return _unit_price;
    }

    public void set_unit_price(BigDecimal _unit_price) {
        this._unit_price = _unit_price;
    }
}
