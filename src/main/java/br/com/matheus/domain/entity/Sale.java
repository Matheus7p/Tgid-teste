package br.com.matheus.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User _user;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime _dateSale;

    @OneToMany(mappedBy = "_sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleItem> items = new ArrayList<>();


    public Sale(){}

    public Sale(User _user){
        this._user = _user;
        this._dateSale = LocalDateTime.now();
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User _user) {
        this._user = _user;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public LocalDateTime get_dateSale() {
        return _dateSale;
    }

    public void set_dateSale(LocalDateTime _dateSale) {
        this._dateSale = _dateSale;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(item -> item.get_unit_price().multiply(new BigDecimal(item.get_quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
