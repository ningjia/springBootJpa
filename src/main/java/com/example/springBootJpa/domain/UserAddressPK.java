package com.example.springBootJpa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class UserAddressPK implements Serializable {

    private Long addressId;

    private Long userId;

}
