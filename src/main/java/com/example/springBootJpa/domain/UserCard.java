package com.example.springBootJpa.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * describe:
 *
 * @author xxx
 * @date 2018/08/05
 */
@Data
@Entity
@Table(name = "userCard")
public class UserCard {

    //复合主键
    @EmbeddedId
    private UserCardPK pk;

    @Column
    private String cardNo;

    @Column
    private String remark;

    public UserCard(UserCardPK pk, String cardNo, String remark) {
        this.pk = pk;
        this.cardNo = cardNo;
        this.remark = remark;
    }
}
