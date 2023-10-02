package com.charter.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PurchaseEntity> purchases = new ArrayList<>();

    public CustomerEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CustomerEntity(String s) {
        this.name = s;
    }


    public CustomerEntity addToPurchases(PurchaseEntity purchase) {
        this.purchases.add(purchase);
        purchase.setCustomer(this);
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", purchases=" + purchases +
                '}';
    }
}
