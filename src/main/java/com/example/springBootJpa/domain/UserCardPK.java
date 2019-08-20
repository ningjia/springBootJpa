package com.example.springBootJpa.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: jianing
 * @Description:
 * @Date: 2019-08-20 18:07
 * @Version: 1.0
 */
@Embeddable
@NoArgsConstructor
public class UserCardPK implements Serializable {

    @Column
    private Long userId;

    @Column
    private String cardType;

    public UserCardPK(Long userId, String cardType) {
        this.userId = userId;
        this.cardType = cardType;
    }
}
