package com.example.springBootJpa.service;

import com.example.springBootJpa.domain.User;
import com.example.springBootJpa.domain.UserCard;
import com.example.springBootJpa.domain.UserCardPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserCardRepository extends JpaRepository<UserCard, UserCardPK> {

}
