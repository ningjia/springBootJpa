package com.example.springBootJpa.service;

import com.example.springBootJpa.domain.UserAddress;
import com.example.springBootJpa.domain.UserAddressPK;
import com.example.springBootJpa.domain.UserCard;
import com.example.springBootJpa.domain.UserCardPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, UserAddressPK> {
    
}
