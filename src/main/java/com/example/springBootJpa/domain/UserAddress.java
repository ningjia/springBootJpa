package com.example.springBootJpa.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "UserAddress")
@IdClass(UserAddressPK.class)
public class UserAddress implements Serializable {

    @Id
    private Long addressId;

    @Id
    private Long userId;

    @Column
    private String address;

    @Column
    private Integer zip;

    public UserAddress(Long addressId, Long userId, String address, Integer zip) {
        this.addressId = addressId;
        this.userId = userId;
        this.address = address;
        this.zip = zip;
    }
}
